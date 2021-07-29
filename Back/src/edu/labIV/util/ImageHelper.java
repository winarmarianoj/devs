package edu.labIV.util;

import com.mauroPignatta.Base64Image;
import com.mauroPignatta.Base64ImageConvertor;
import edu.labIV.entity.Post;
import edu.labIV.entity.User;

import java.io.IOException;

public class ImageHelper {

    public static String saveUserImage(int userID, Base64Image image){
        String output = "";
        try {
            output = "res/user/user" + userID + "." + image.getExtension();
            Base64ImageConvertor.convertBase64ToImage(image, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public static String savePostImage(int userID, int postID, Base64Image image){
        String output = "";
        try {
            output = "res/post/post" + userID + "_" + postID +  "." + image.getExtension();
            Base64ImageConvertor.convertBase64ToImage(image, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public static String loadUserImage(User user) throws IOException {
        Base64Image base64Image = Base64ImageConvertor.convertImageToBase64(user.getProfilePicturePath());
        return base64Image.toString();
    }

    public static String loadPostImage(Post post) throws IOException {
        Base64Image base64Image = Base64ImageConvertor.convertImageToBase64(post.getImagePath());
        return base64Image.toString();
    }

}
