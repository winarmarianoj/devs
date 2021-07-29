package edu.labIV.manager;

import com.mauroPignatta.Base64Image;
import edu.labIV.entity.User;
import edu.labIV.exception.UserException;
import edu.labIV.logger.Logger;
import edu.labIV.mapper.UserMapper;
import edu.labIV.util.ImageHelper;
import edu.labIV.validator.UserValidator;

import java.io.IOException;
import java.util.List;

public class UserManager {

    private final UserValidator userValidator;
    private final UserMapper userMapper;
    private final Logger logger;

    public UserManager(UserValidator userValidator, UserMapper userMapper, Logger logger) {
        this.userValidator = userValidator;
        this.userMapper = userMapper;
        this.logger = logger;
    }

    public boolean saveUser(User user) {
        boolean isSaved = false;
        try {
            userValidator.validateUser(user);
            isSaved = userMapper.save(user);
        } catch (UserException ex) {
            logger.logError(ex.getError());
        }
        return isSaved;
    }

    public void updatePhoto(User user, String photo){
        Base64Image base64Image = new Base64Image(photo);
        user.setProfilePicturePath(ImageHelper.saveUserImage(user.getId(), base64Image));
        updateUser(user);
    }

    public User getUserNoPhoto(int id){
        return userMapper.get(id);
    }

    public User getUser(int id){
        User user = userMapper.get(id);

        if (!user.getProfilePicturePath().isEmpty()){
            try {
                user.setProfilePicturePath(ImageHelper.loadUserImage(user));
            } catch (IOException e) {
                logger.logError("Fallo al cargar la imagen usuario, User Id: " + user.getId());
            }
        }
        return user;
    }

    public void deleteUser(int id){
        userMapper.delete(id);
    }

    public void updateUser(User user) {
        userMapper.update(user);
    }

    public List<User> getUserList(){
        return userMapper.getAll();
    }
}
