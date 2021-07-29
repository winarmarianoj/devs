package edu.labIV.factory.entity;

import edu.labIV.entity.Account;

public class AccountFactory {

    public Account createNewAccount(String mail, String password){
        return new Account(mail.toLowerCase(), password, false, Account.TRIES);
    }

}
