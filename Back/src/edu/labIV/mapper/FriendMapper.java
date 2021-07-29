package edu.labIV.mapper;


import edu.labIV.dao.FriendDao;
import edu.labIV.entity.Friend;
import edu.labIV.entity.FriendStatus;

import java.util.ArrayList;
import java.util.List;

public class FriendMapper {

    private final FriendDao friendDao;

    public FriendMapper() {
        this.friendDao = new FriendDao();
    }

    public boolean save(Friend friend){
        return friendDao.save(friend);
    }

    public boolean delete(int userId, int friendId){
        return friendDao.delete(userId, friendId);
    }

    public Friend get(int userId, int friendId){
        return friendDao.get(userId, friendId);
    }

    public List<Friend> getAllFriend(int userId){
        List<Friend> friendList = new ArrayList<>();
        for(Friend friend : friendDao.getAll(userId)){
            if(friend.getStatus().equals(FriendStatus.FRIEND)){
                 friendList.add(friend);
            }
        }
        return friendList;
    }

    public List<Friend> getAllRequests(int userId){
        List<Friend> friendList = new ArrayList<>();
        for(Friend friend : friendDao.getAll(userId)){
            if(friend.getStatus().equals(FriendStatus.RECEIVED)){
                friendList.add(friend);
            }
        }
        return friendList;
    }

    public List<Friend> getAll(int userId){
        return friendDao.getAll(userId);
    }

    public boolean update(Friend friend) {
        return friendDao.update(friend);
    }
}
