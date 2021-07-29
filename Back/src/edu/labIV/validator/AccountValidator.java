package edu.labIV.validator;

import edu.labIV.entity.Account;
import edu.labIV.exception.*;
import edu.labIV.util.PasswordEncryptor;

import java.util.regex.Pattern;

public class AccountValidator {

    public void validateAccount(Account account) throws AccountException {
        if(account == null)
            throw new NullAccountException();
        validatePass(account.getPassword());
        validateEmail(account.getEmail());
    }

    public void validateExistingAccount(Account account) throws ExistingAccountException {
        if (account != null)
            throw new ExistingAccountException(account.getEmail());
    }

    public void validatePass(String password) throws InvalidPasswordException {
        if (password == null)
            throw new InvalidPasswordException("null");

        if (!Pattern.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$", password))
            throw new InvalidPasswordException(password);
    }

    public void validateEmail(String email) throws InvalidEmailException{
        if (email == null)
            throw new InvalidEmailException("null");

        if (!Pattern.matches("^([a-zA-Z0-9-._ñ]+)@([a-zA-Z0-9-._ñ]+).([a-zA-Z]{2,5})$",email))
            throw new InvalidEmailException(email);
    }

    public void validateIsActive(Account account) throws InactiveAccount {
        if(!account.isActive()){
            throw new InactiveAccount();
        }
    }

    public void validateCorrectPassword(String encryptedPassword, String securedPassword) throws WrongPasswordExcepcion {
        PasswordEncryptor encryptor = new PasswordEncryptor();
        if(!encryptor.verifyPassword(encryptedPassword, securedPassword)){
            throw new WrongPasswordExcepcion();
        }
    }
}
