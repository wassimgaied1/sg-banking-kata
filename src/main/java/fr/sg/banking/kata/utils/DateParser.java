package fr.sg.banking.kata.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    public Date getDateFromString(String d) throws ParseException {
        return sdf.parse(d);
    }

    public String getStringFromDate(Date d) {
        return sdf.format(d);
    }
}
