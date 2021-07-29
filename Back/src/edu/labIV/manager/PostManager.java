package edu.labIV.manager;

import com.mauroPignatta.Base64Image;
import edu.labIV.entity.Post;
import edu.labIV.exception.PostException;
import edu.labIV.logger.Logger;
import edu.labIV.mapper.PostMapper;
import edu.labIV.util.ImageHelper;
import edu.labIV.validator.PostValidator;

import java.io.IOException;
import java.util.List;

public class PostManager {

    private final PostValidator postValidator;
    private final PostMapper postMapper;
    private final Logger logger;

    public PostManager(PostValidator postValidator, PostMapper postMapper, Logger logger) {
        this.postMapper = postMapper;
        this.logger = logger;
        this.postValidator = postValidator;
    }

    public boolean savePost(Post post){
        boolean isSaved = false;
        try{
            postValidator.validatePost(post);
            postMapper.save(post);
            isSaved = true;
        }catch (PostException e){
            logger.logError(e.getError());
        }
        return isSaved;
    }

    public void updatePostPhoto(Post post, String photo){
        Base64Image base64Image = new Base64Image(photo);
        post.setImagePath(ImageHelper.savePostImage(post.getUserId(), post.getPostId(), base64Image));
        updatePost(post);
    }

    public boolean deletePost(int userId, int postId){
        return postMapper.delete(userId, postId);
    }

    public Post getPost(int userId, int postId){
        Post post = postMapper.get(userId, postId);
        try{
            post.setImagePath(ImageHelper.loadPostImage(post));
        }catch (IOException e){
            logger.logError("Fallo al cargar la imagen del post, Post Id: " + post.getPostId());
        }
        return post;
    }

    public List<Post> getAllPost(int userId){
        List<Post> postList = postMapper.getAll(userId);
        for(Post post : postList){
            try{
                post.setImagePath(ImageHelper.loadPostImage(post));
            }catch (IOException e){
                logger.logError("Fallo al cargar la imagen del post, Post Id: " + post.getPostId());
            }
        }
        return postList;
    }

    public boolean updatePost(Post post){return postMapper.update(post);}
}
