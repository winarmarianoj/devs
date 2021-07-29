package edu.labIV.manager;

import edu.labIV.entity.Friend;
import edu.labIV.entity.FriendStatus;
import edu.labIV.mapper.FriendMapper;

import java.util.List;

public class FriendManager {

    private final FriendMapper friendMapper;

    public FriendManager(FriendMapper friendMapper) {
        this.friendMapper = friendMapper;
    }

    public boolean sendFriendRequest(Friend sender) {
        boolean sent;
        sender.setStatus(FriendStatus.PENDING);
        Friend receiver = new Friend(sender.getFriendId(), sender.getUserId(), FriendStatus.RECEIVED);
        sent = friendMapper.save(sender);
        sent &= friendMapper.save(receiver);
        return sent;
    }

    public boolean acceptRequest(Friend receiver){
        boolean accepted;
        receiver.setStatus(FriendStatus.FRIEND);
        Friend sender = new Friend(receiver.getFriendId(), receiver.getUserId(), FriendStatus.FRIEND);
        accepted = friendMapper.update(receiver);
        accepted &= friendMapper.update(sender);
        return accepted;
    }

    public boolean declineRequest(Friend receiver){
        return deleteFriend(receiver.getUserId(), receiver.getFriendId());
    }

    public Friend getFriend(int userId, int friendId){
        return friendMapper.get(userId, friendId);
    }

    public List<Friend> getFriendList(int userId){
        return friendMapper.getAllFriend(userId);
    }

    public List<Friend> getRequestList(int userId){
        return friendMapper.getAllRequests(userId);
    }

    public List<Friend> getAll(int userId){
        return friendMapper.getAll(userId);
    }

    public boolean deleteFriend(int userId, int friendId){
        boolean deleted;
        deleted = friendMapper.delete(userId, friendId);
        deleted &= friendMapper.delete(friendId, userId);
        return deleted;
    }

}
