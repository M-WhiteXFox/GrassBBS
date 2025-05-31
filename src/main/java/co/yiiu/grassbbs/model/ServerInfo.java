package co.yiiu.grassbbs.model;

import java.util.List;

public class ServerInfo {
    private int onlineCount;
    private String onlinePlayers;
    private String queryTime;

    private int serverCount;
    private int whiteListCount;
    private int notPassCount;
    private int applyCount;
    private int opCount;
    private int banCount;

    private List<PlayerInfo> topTen;

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

    public int getServerCount() {
        return serverCount;
    }

    public void setServerCount(int serverCount) {
        this.serverCount = serverCount;
    }

    public int getWhiteListCount() {
        return whiteListCount;
    }

    public void setWhiteListCount(int whiteListCount) {
        this.whiteListCount = whiteListCount;
    }

    public int getNotPassCount() {
        return notPassCount;
    }

    public void setNotPassCount(int notPassCount) {
        this.notPassCount = notPassCount;
    }

    public int getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(int applyCount) {
        this.applyCount = applyCount;
    }

    public int getOpCount() {
        return opCount;
    }

    public void setOpCount(int opCount) {
        this.opCount = opCount;
    }

    public int getBanCount() {
        return banCount;
    }

    public void setBanCount(int banCount) {
        this.banCount = banCount;
    }

    public List<PlayerInfo> getTopTen() {
        return topTen;
    }

    public void setTopTen(List<PlayerInfo> topTen) {
        this.topTen = topTen;
    }
}
