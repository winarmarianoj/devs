package edu.labIV.mapper;

import edu.labIV.dao.PostDao;
import edu.labIV.entity.Post;

import java.util.List;

public class PostMapper {

    private final PostDao postDao;

    public PostMapper() {
        this.postDao = new PostDao();
    }

    public boolean save(Post post){
        return postDao.save(post);
    }

    public boolean delete(int userId, int postId){
        return postDao.delete(userId, postId);
    }

    public Post get(int userId, int postId){
        return postDao.get(userId, postId);
    }

    public List<Post> getAll(int userId){
        return postDao.getAll(userId);
    }

    public boolean update(Post post){return postDao.update(post);}
}
