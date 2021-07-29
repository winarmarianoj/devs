package edu.labIV.validator;

import org.junit.jupiter.api.Test;

import edu.labIV.entity.Post;
import edu.labIV.exception.PostException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PostValidatorTest {

    Class<PostException> exceptionClass = PostException.class;
    PostValidator postValidator = new PostValidator();

    @Test
    public void validPost(){
        Post post = new Post(1, "Texto valido", null, LocalDateTime.now());
        assertDoesNotThrow(() -> postValidator.validatePost(post));
    }

    @Test void invalidPost(){
        Post post = new Post(1, "", "url invalido", LocalDateTime.now());
        assertThrows(exceptionClass, () -> postValidator.validatePost(post));
    }

    @Test
    public void validURL() {
        assertDoesNotThrow(() -> postValidator.validateImagePath("https://google.com"));
    }

    @Test
    public void invalidURLNoResponse() {
        assertThrows(exceptionClass, () -> postValidator.validateImagePath("https://NoExistente.com.ar"));
    }

    @Test
    public void invalidURLFormat() {
        assertThrows(exceptionClass, () -> postValidator.validateImagePath("google"));
    }

    @Test
    public void validText(){
        assertDoesNotThrow(() -> postValidator.validateText("Texto"));
    }

    @Test
    public void invalidEmptyText(){
        assertThrows(exceptionClass, () -> postValidator.validateText(""));
    }

    @Test
    public void invalidNullText(){
        assertThrows(exceptionClass, () -> postValidator.validateText(null));
    }

    @Test
    public void invalidLongText(){
        int length = Post.MAX_TEXT_LENGTH + 1;
        assertThrows(exceptionClass, () -> postValidator.validateText(repeat("a", length)));
    }

    private String repeat(String src, int times){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < times; ++i){
            builder.append(src);
        }
        return builder.toString();
    }
}