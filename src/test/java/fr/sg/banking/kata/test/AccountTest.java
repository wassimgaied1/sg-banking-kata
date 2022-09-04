package fr.sg.banking.kata.test;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import fr.sg.banking.kata.domain.Account;
import fr.sg.banking.kata.utils.DateParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class AccountTest {

    private Account account ;

    @Before
    public void setUp(){
        account=new Account(new DateParser());
    }



    @Test
    public void shouldSaveTransactionWhenDepositAmountIsPositive() throws ParseException {
        account.deposit(100.00, "04/09/2022");
        assertTrue(100.0== account.getBalance());
        assertTrue(!account.getTransactions().isEmpty());
        assertEquals(account.getTransactions().size(),1);

    }


    @Test
    public void shouldSaveTransactionWhenWithdrawAmount() throws ParseException {
        account.setBalance(1000.00);
        account.withdraw(100.00, "04/09/2022");
        assertTrue(900.0== account.getBalance());
        assertTrue(!account.getTransactions().isEmpty());
        assertEquals(account.getTransactions().size(),1);
    }

    @Test
    public void shouldLogAllTransaction() throws  ParseException {

        ch.qos.logback.classic.Logger logger = ((ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Account.class));
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);


        account.deposit(2000.00, "04/09/2022");
        account.withdraw(500.00, "05/09/2022");
        account.deposit(1000.00, "06/09/2022");


        account.printAllTransaction();
       String excepted=" operation   |                date|              amount|             balance\r\n" +
               "   DEPOSIT   |          04/09/2022|              2000.0|              2000.0\r\n" +
               "WITHDRAWAL   |          05/09/2022|               500.0|              1500.0\r\n" +
               "   DEPOSIT   |          06/09/2022|              1000.0|              2500.0";

        assertEquals(listAppender.list.get(0).getMessage(),excepted);


    }
}
