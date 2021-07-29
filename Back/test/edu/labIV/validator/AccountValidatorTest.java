package edu.labIV.validator;

import edu.labIV.entity.Account;

import edu.labIV.exception.AccountException;
import edu.labIV.exception.ExistingAccountException;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class AccountValidatorTest {

    Account account = new Account("mauroj.pignatta@gmail.com", "aB12345678", false, Account.TRIES);
    Account invalidAccount = new Account("mailinvalido","1234",false, Account.TRIES);
    AccountValidator accountValidator = new AccountValidator();

    @Test
    void validateAccountTrue() {
        assertDoesNotThrow(() -> accountValidator.validateAccount(account));
    }

    @Test
    void validateAccountFalse() {
        assertThrows(AccountException.class, () -> accountValidator.validateAccount(invalidAccount));
    }

    @Test
    void validateNotExistingAccount(){
        assertThrows(ExistingAccountException.class, () -> accountValidator.validateExistingAccount(account));
    }

    @Test
    void validateEmail(){
        assertDoesNotThrow(() -> accountValidator.validateEmail(account.getEmail()));
    }

    @Test
    void validatePassword()  {
        assertDoesNotThrow(() -> accountValidator.validateEmail(account.getEmail()));
    }

}