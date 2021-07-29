package edu.labIV.validator;

import edu.labIV.entity.Post;
import edu.labIV.exception.*;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;


public class PostValidator {

    public void validatePost(Post post) throws PostException {
        if (post == null)
            throw new NullPostException();
        validateText(post.getText());
        validateImagePath(post.getImagePath());
    }

    public void validateImagePath(String path) throws PostException {
        if (path != null && !path.trim().isEmpty()) {
            File f = new File(path);

            if(!f.exists())
                throw new InvalidImagePath(path);

            if(!Pattern.matches("(.)+\\.(jpg|png|jpeg)$", path))
                throw new NotAnImageException(path);
        }
    }

    public void validateText(String text) throws PostException {
        if (text == null)
            throw new NullTextException();
        if(text.isEmpty() || text.trim().isEmpty())
            throw new EmptyTextException();
        if(text.length() > Post.MAX_TEXT_LENGTH)
            throw new LongTextException(text.length());
    }
}
