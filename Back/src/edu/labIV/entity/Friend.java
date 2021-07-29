package edu.labIV.entity;

public class Friend {
    private int userId;
    private int friendId;
    private String status;

    public Friend(int userId, int friendId, String status) {
        setUserId(userId);
        setFriendId(friendId);
        setStatus(status);
    }

    public Friend() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
