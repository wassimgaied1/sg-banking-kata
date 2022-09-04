package fr.sg.banking.kata.test;

import fr.sg.banking.kata.domain.Account;
import fr.sg.banking.kata.utils.DateParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

    @Mock
    private  DateParser dateParser;

    @InjectMocks
    private Account account ;


    @Test
    public void shouldSaveTransactionWhenDepositAmountIsPositive() throws ParseException {
        account.deposit(100.00, "04/09/2022");
        assertTrue(100.0== account.getBalance());
    }
}
