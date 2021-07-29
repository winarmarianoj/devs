package edu.labIV.manager;

import edu.labIV.exception.MailException;
import edu.labIV.factory.entity.AccountFactory;
import edu.labIV.logger.Logger;
import edu.labIV.entity.Account;
import edu.labIV.exception.AccountException;
import edu.labIV.exception.InactiveAccount;
import edu.labIV.exception.WrongPasswordExcepcion;
import edu.labIV.mail.BlockedAccountMail;
import edu.labIV.mail.MailSender;
import edu.labIV.mapper.AccountMapper;
import edu.labIV.util.PasswordEncryptor;
import edu.labIV.validator.AccountValidator;

public class AccountManager {

    private final AccountValidator accountValidator;
    private final AccountMapper accountMapper;
    private final Logger logger;

    public AccountManager(AccountValidator accountValidator, AccountMapper accountMapper, Logger logger) {
        this.accountValidator = accountValidator;
        this.accountMapper = accountMapper;
        this.logger = logger;
    }

    public boolean login(String email, String encryptedPassword){
        boolean isConnected = false;
        Account account = accountMapper.get(email);
        if(account != null && account.getAttempts() > 0){
            try{
                accountValidator.validateAccount(account);
                accountValidator.validateIsActive(account);
                accountValidator.validateCorrectPassword(encryptedPassword, account.getPassword());
                account.setAttempts(Account.TRIES);
                isConnected = accountMapper.update(account);
            }catch (InactiveAccount e) {
                logger.logError(e.getError());
            }catch (WrongPasswordExcepcion e){
                account.setAttempts(account.getAttempts() - 1);
                if(account.getAttempts() == 0){
                    sendBlockedAccountMail(account);
                }
                accountMapper.update(account);
                logger.logError(e.getError());
            } catch (AccountException e) {
                e.printStackTrace();
            }
        }
        return isConnected;
    }

    private void sendBlockedAccountMail(Account account) {
        String subject = BlockedAccountMail.getSubject();
        String body = BlockedAccountMail.getBody();
        try {
            MailSender.getInstance().sendMail(account.getEmail(), subject, body);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    public boolean signIn(String email, String password) {
        boolean isSigned = false;
        AccountFactory factory = new AccountFactory();
        Account account = factory.createNewAccount(email, password);
        PasswordEncryptor encryptor = new PasswordEncryptor();
        try{
            accountValidator.validateAccount(account);
            accountValidator.validateExistingAccount(accountMapper.get(account.getEmail()));
            String securePassword = encryptor.generateSecurePassword(password);
            account.setPassword(securePassword);
            isSigned = accountMapper.save(account);
            //TODO mandar mail de activacion de cuenta
        } catch (AccountException e){
            logger.logError(e.getError());
        }
        return isSigned;
    }

    public boolean activateAccount(int id){
        Account account = accountMapper.get(id);
        boolean isActivated = false;
        try {
            accountValidator.validateAccount(account);
            if(isActivated = !account.isActive()){
                account.setActive(true);
                accountMapper.update(account);
            }
        } catch (AccountException e) {
            logger.logError(e.getError());
        }
        return isActivated;
    }

    public boolean changePassword(String email, String newPassword){
        Account account = accountMapper.get(email);
        PasswordEncryptor encryptor = new PasswordEncryptor();
        boolean changed = false;
        try {
            accountValidator.validatePass(newPassword);
            accountValidator.validateAccount(account);
            String securedPassword = encryptor.generateSecurePassword(newPassword);
            account.setPassword(securedPassword);
            account.setAttempts(Account.TRIES);
            changed = accountMapper.update(account);
        } catch (AccountException e) {
            logger.logError(e.getError());
        }
        return changed;
    }

    public boolean deleteAccount(String email){
        return accountMapper.delete(email);
    }

    public boolean deleteAccount(int id){
        return accountMapper.delete(id);
    }

    public Account getAccount(String email){
        return accountMapper.get(email);
    }

    public Account getAccount(int id){
        return accountMapper.get(id);
    }
}