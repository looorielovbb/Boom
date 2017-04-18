package me.looorielovbb.boom.data.bean.zhihu;

/**
 * Created by Lulei on 2017/4/17.
 * time : 16:37
 * date : 2017/4/17
 * mail to lulei4461@gmail.com
 */
public class RecentBean {
    private int news_id;
    private String url;
    private String thumbnail;
    private String title;
    private boolean readState;

    public boolean getReadState() {
        return readState;
    }

    public void setReadState(boolean readState) {
        this.readState = readState;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "RecentBean{" + "news_id=" + news_id + ", url='" + url + '\'' + ", thumbnail='" +
               thumbnail + '\'' + ", title='" + title + '\'' + ", readState=" + readState + '}';
    }
}
