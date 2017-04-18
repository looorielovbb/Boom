package me.looorielovbb.boom.data.bean.zhihu;

/**
 * Created by Lulei on 2017/4/17.
 * time : 15:43
 * date : 2017/4/17
 * mail to lulei4461@gmail.com
 */
public class TopStoriesBean {
    private String image;
    private int type;
    private int id;
    private String ga_prefix;
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TopStoriesBean{" + "image='" + image + '\'' + ", type=" + type + ", id=" + id +
               ", ga_prefix='" + ga_prefix + '\'' + ", title='" + title + '\'' + '}';
    }
}
