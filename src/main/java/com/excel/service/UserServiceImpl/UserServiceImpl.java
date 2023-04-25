package com.excel.service.UserServiceImpl;

import com.excel.dto.Country;
import com.excel.entity.User;
import com.excel.repository.UserRepository;
import com.excel.service.UserService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static ByteArrayInputStream downloadDate(ArrayList<User> aList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFSheet sheet = workbook.createSheet("Not insert data");
        int rowCount = 0;
        int cellCount = 0;
        Row row = sheet.createRow(rowCount);
        for (String headerName : new ArrayList<String>() {{
            add("firstName");
            add("lastName");
            add("emailAddress");
            add("mobileNumber");
            add("Reason");
        }}) {
            Cell cell = row.createCell(cellCount);
            cell.setCellValue(headerName);
            cellCount++;
        }
        for (User aUser : aList) {
            rowCount++;
            row = sheet.createRow(rowCount);
            row.createCell(0).setCellValue(Optional.ofNullable(aUser.getFirstName()).orElse(null));
            row.createCell(1).setCellValue(Optional.ofNullable(aUser.getLastName()).orElse(null));
            row.createCell(2).setCellValue(Optional.ofNullable(aUser.getEmailAddress()).orElse(null));
            row.createCell(3).setCellValue(Optional.ofNullable(aUser.getMobileNumber()).map(Object::toString).orElse(null));
            row.createCell(4).setCellValue(Optional.ofNullable(aUser.getReason()).map(Object::toString).orElse(null));
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public ByteArrayInputStream uploadAndDownload(MultipartFile file) {
        ArrayList<User> aList = new ArrayList<>();
        try {
            int rowNumber = 0;
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheets = workbook.getSheet("user");
            Iterator<Row> iterator = sheets.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();
                int cellId = 0;
                User aUser = new User();
                if (row.iterator().hasNext()) {
                    int field = 0;
                    boolean isEmpty = false;
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        if (cell == null || cell.toString().isEmpty() || cell.toString().isBlank()) {
                            isEmpty = true;
                        }
                        switch (cellId) {
                            case 0:
                                aUser.setFirstName(Objects.requireNonNull(cell).toString());
                                break;
                            case 1:
                                aUser.setLastName(Objects.requireNonNull(cell).toString());
                                break;
                            case 2:
                                aUser.setEmailAddress(Objects.requireNonNull(cell).toString());
                                break;
                            case 3:
                                aUser.setMobileNumber((long) Objects.requireNonNull(cell).getNumericCellValue());
                                break;
                            default:
                                break;
                        }
                        field++;
                        cellId++;
                    }
                    if (isEmpty || field != 4) {
                        System.out.println(">>>>Error in excel>>>>>>>>>>");
                        aUser.setReason("null");
                        aList.add(aUser);
                    } else {
                        System.out.println(">>>>>>Process for insert data>>>>>>>>");
                    }
                    System.out.println("final field>>" + field);
                    System.out.print("user>>" + aUser);
                    System.out.println();
                    System.out.println("===================================================================");
                }
            }
        } catch (Exception e) {
            System.out.println("error>>" + e.getMessage());
            e.printStackTrace();
        }
        return downloadDate(aList);
    }

    @Override
    public ResponseEntity uploadExcel(MultipartFile file) {
        try {
            int rowNumber = 0;
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheets = workbook.getSheet("user");
            Iterator<Row> iterator = sheets.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();
                int cellId = 0;
                User aUser = new User();
                if (row.iterator().hasNext()) {
                    int field = 0;
                    boolean isEmpty = false;
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        if (cell == null || cell.toString().isEmpty() || cell.toString().isBlank()) {
                            isEmpty = true;
                        }
                        switch (cellId) {
                            case 0:
                                aUser.setFirstName(Objects.requireNonNull(cell).toString());
                                break;
                            case 1:
                                aUser.setLastName(Objects.requireNonNull(cell).toString());
                                break;
                            case 2:
                                aUser.setEmailAddress(Objects.requireNonNull(cell).toString());
                                break;
                            case 3:
                                aUser.setMobileNumber((long) Objects.requireNonNull(cell).getNumericCellValue());
                                break;
                            default:
                                break;
                        }
                        field++;
                        cellId++;
                    }
                    if (isEmpty || field != 4) {
                        System.out.println(">>>>Error in excel>>>>>>>>>>");
                    } else {
                        System.out.println(">>>>>>Process for insert data>>>>>>>>");
                    }
                    System.out.println("final field>>" + field);
                    System.out.print("user>>" + aUser);
                    System.out.println();
                    System.out.println("===================================================================");
                }
            }
        } catch (Exception e) {
            System.out.println("error>>" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ByteArrayInputStream exportExcel() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFSheet sheet = workbook.createSheet("Error");

        Object[][] bookData = {
                {"Head First Java", "Kathy Serria", 79},
                {"Effective Java", "Joshua Bloch", 36},
                {"Clean Code", "Robert martin", 42},
                {"Thinking in Java", "Bruce Eckel", 35},
        };

        int rowCount = 0;

        for (Object[] aBook : bookData) {
            Row row = sheet.createRow(++rowCount);

            int columnCount = 0;

            for (Object field : aBook) {
                Cell cell = row.createCell(++columnCount);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }

        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public ResponseEntity fetchAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }
}
