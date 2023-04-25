package com.excel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum FileType {
    ContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

    private String contentType;
}
