package co.yiiu.grassbbs.model;

// ServerInfo.java
public class ServerInfo {
    private int onlineCount;
    private String onlinePlayers;
    private String queryTime;

    // Getter 和 Setter
    public int getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }

    public String getOnlinePlayers() {
        return onlinePlayers;
    }

    public void setOnlinePlayers(String onlinePlayers) {
        this.onlinePlayers = onlinePlayers;
    }

    public String getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(String queryTime) {
        this.queryTime = queryTime;
    }
}
