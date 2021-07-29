package edu.labIV.factory.manager;

import edu.labIV.logger.Logger;
import edu.labIV.manager.AccountManager;
import edu.labIV.manager.FriendManager;
import edu.labIV.manager.PostManager;
import edu.labIV.manager.UserManager;
import edu.labIV.mapper.AccountMapper;
import edu.labIV.mapper.FriendMapper;
import edu.labIV.mapper.PostMapper;
import edu.labIV.mapper.UserMapper;
import edu.labIV.validator.AccountValidator;
import edu.labIV.validator.PostValidator;
import edu.labIV.validator.UserValidator;

public class ManagerFactory {

    public AccountManager createAccountManager(){
        AccountValidator validator = new AccountValidator();
        AccountMapper mapper = new AccountMapper();
        Logger logger = Logger.getInstance();

        return new AccountManager(validator, mapper, logger);
    }

    public UserManager createUserManager() {
        UserValidator validator = new UserValidator();
        UserMapper mapper = new UserMapper();
        Logger logger = Logger.getInstance();

        return new UserManager(validator, mapper, logger);
    }

    public FriendManager createFriendManager() {
        FriendMapper mapper = new FriendMapper();

        return new FriendManager(mapper);
    }

    public PostManager createPostManager() {
        PostValidator validator = new PostValidator();
        PostMapper mapper = new PostMapper();
        Logger logger = Logger.getInstance();

        return new PostManager(validator, mapper, logger);
    }
}
