package me.looorielovbb.boom.data.bean.gank;

import java.util.List;

/**
 * Created by Lulei on 2017/2/24.
 * time : 16:00
 * date : 2017/2/24
 * mail to lulei4461@gmail.com
 */

public class Girl {
    /**
     * _id : 5e958ff20bd5529b54e712a7
     * author : 鸢媛
     * category : Girl
     * createdAt : 2020-05-01 08:00:00
     * desc : 真的很喜欢那种
     没一会就问我在干嘛
     总和我分享有趣事的人
     每天粘着我
     让我感觉自己被需要 ​​
     * images : ["http://gank.io/images/d5c0dc1618194a65b928c932dd2de3a7"]
     * likeCounts : 0
     * publishedAt : 2020-05-01 08:00:00
     * stars : 1
     * title : 第72期
     * type : Girl
     * url : http://gank.io/images/d5c0dc1618194a65b928c932dd2de3a7
     * views : 177
     */

    private String _id;
    private String author;
    private String category;
    private String createdAt;
    private String desc;
    private int likeCounts;
    private String publishedAt;
    private int stars;
    private String title;
    private String type;
    private String url;
    private int views;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(int likeCounts) {
        this.likeCounts = likeCounts;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

}
