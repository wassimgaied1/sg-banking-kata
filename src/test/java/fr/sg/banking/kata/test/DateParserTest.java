package fr.sg.banking.kata.test;

import fr.sg.banking.kata.utils.DateParser;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.assertEquals;


public class DateParserTest {

    private DateParser dateParser;

    @Before
    public void setUp(){
        dateParser=new DateParser();
    }


    @Test
    public void shouldReturnDateFromString() throws ParseException {
     Date date=this.dateParser.getDateFromString("04/09/2022");
     assertEquals(date.toInstant()
             .atZone(ZoneId.systemDefault())
             .toLocalDate(), LocalDate.of(2022,9,4));
    }
    @Test(expected =ParseException.class )
    public void shouldThrowParseException() throws ParseException {
        Date date=this.dateParser.getDateFromString("test date");
    }

    @Test
    public void shouldReturnStringFromDate()  {
        Date date = Date.from(LocalDate.of(2022,9,4).atStartOfDay(ZoneId.systemDefault()).toInstant());

        String d=this.dateParser.getStringFromDate(date);
        assertEquals(d, "04/09/2022");
    }

}
