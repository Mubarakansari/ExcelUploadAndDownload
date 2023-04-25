package com.excel.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class TimeZone {
    public String value;
    public String abbr;
    public double offset;
    public boolean isdst;
    public String text;
    public ArrayList<String> utc;
}
