package edu.labIV.manager;

import edu.labIV.cfg.Config;
import edu.labIV.entity.Account;
import edu.labIV.entity.Friend;
import edu.labIV.entity.User;
import edu.labIV.entity.UserStatus;
import edu.labIV.exception.MailException;
import edu.labIV.factory.manager.ManagerFactory;
import edu.labIV.logger.Logger;
import edu.labIV.mail.ActivationMail;
import edu.labIV.mail.MailSender;
import edu.labIV.mail.RegisterMail;

import java.util.ArrayList;
import java.util.List;

public class BackEndManager {

    private final AccountManager accountManager;
    private final UserManager userManager;
    private final FriendManager friendManager;
    private final PostManager postManager;

    public BackEndManager() {
        ManagerFactory factory = new ManagerFactory();
        this.accountManager = factory.createAccountManager();
        this.userManager = factory.createUserManager();
        this.friendManager = factory.createFriendManager();
        this.postManager = factory.createPostManager();
    }

    public boolean logIn(String email, String encryptedPassword){
        boolean hasLoggedIn;
        if(hasLoggedIn = accountManager.login(email, encryptedPassword)){
            Account account = accountManager.getAccount(email);
            User user = userManager.getUserNoPhoto(account.getId());
            user.setStatus(UserStatus.ONLINE);
            userManager.updateUser(user);
        }
        return hasLoggedIn;
    }

    public boolean logOut(String email){
        Account account = accountManager.getAccount(email);
        boolean logOut = false;
        if (account != null) {
            User user = userManager.getUserNoPhoto(account.getId());
            user.setStatus(UserStatus.OFFLINE);
            userManager.updateUser(user);
            logOut = true;
        }
        return logOut;
    }

    public void delete(String email){
        accountManager.deleteAccount(email);
    }

    public void delete(int id){
        accountManager.deleteAccount(id);
    }

    public boolean signIn(String email, String password, User user){
        boolean hasSignIn;
        if (hasSignIn = accountManager.signIn(email, password)) {
            Account account = accountManager.getAccount(email);
            user.setId(account.getId());
            if (!userManager.saveUser(user)) {
                accountManager.deleteAccount(user.getId());
                hasSignIn = false;
            } else {
                String subject = RegisterMail.getSubject();
                String username = user.getName();
                String url = Config.getInstance().getTomcatURL() + "account/activate/" + user.getId();

                try {
                    MailSender.getInstance().sendMail(email, subject, RegisterMail.getBody(username, url));
                } catch (MailException e) {
                    Logger.getInstance().logError(e.getError());
                }
            }
        }

        return hasSignIn;
    }

    public boolean activate(int id) {
        boolean isActivated;
        if (isActivated = this.accountManager.activateAccount(id)) {
            Account account = this.accountManager.getAccount(id);
            String email = account.getEmail();
            String subject = ActivationMail.getSubject();

            try {
                MailSender.getInstance().sendMail(email, subject, ActivationMail.getBody());
            } catch (MailException e) {
                Logger.getInstance().logError(e.getError());
            }
        }

        return isActivated;
    }

    public List<User> getAddableUserList(int userId){
        List<User> addableUserList = new ArrayList<>();
        List<Friend> friendList = friendManager.getAll(userId);
        List<Integer> friendIdList = getFriendsIds(friendList);

        for( User currentUser : userManager.getUserList()){
            if( userId != currentUser.getId()){
                boolean isFriend = friendIdList.contains(currentUser.getId());
                if(!isFriend){
                    addableUserList.add(currentUser);
                }
            }
        }

        return addableUserList;
    }

    private List<Integer> getFriendsIds(List<Friend> friendList){
        List<Integer> friendIdList = new ArrayList<>();
        if (friendList != null)
            for(Friend friend : friendList){
                friendIdList.add(friend.getFriendId());
            }
        return friendIdList;
    }

    /* Agrego para poder usar las funciones de todos los managers */

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public FriendManager getFriendManager(){
        return friendManager;
    }

    public PostManager getPostManager(){return postManager;}
}
