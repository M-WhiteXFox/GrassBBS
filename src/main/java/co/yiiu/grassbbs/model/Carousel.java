package co.yiiu.grassbbs.model;

import java.util.Date;

public class Carousel {
    private Integer id;
    private String title;
    private String description;
    private String imageUrl;
    private String webUrl;
    private Integer sort;
    private Boolean isActive;
    private Date createTime;

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isActive() {
        return isActive;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    // getters/setters
}