package co.yiiu.grassbbs.model;

public class PlayerInfo {
    private int id;
    private String userName;
    private String qq;
    private String identity;
    private String lastOnlineTime;
    private String lastOfflineTime;
    private int gameTime;

    // Getter 和 Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(String lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }

    public String getLastOfflineTime() {
        return lastOfflineTime;
    }

    public void setLastOfflineTime(String lastOfflineTime) {
        this.lastOfflineTime = lastOfflineTime;
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }
}
