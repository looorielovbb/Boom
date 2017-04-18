package me.looorielovbb.boom.data.bean.zhihu;

import java.util.List;

/**
 * Created by Lulei on 2017/4/17.
 * time : 15:43
 * date : 2017/4/17
 * mail to lulei4461@gmail.com
 */
public class StoriesBean {
    private String ga_prefix;
    private String display_date;
    private String date;
    private boolean multipic;
    private int type;
    private int id;
    private String title;
    private List<String> images;
    private boolean readState;

    public boolean getReadState() {
        return readState;
    }

    public void setReadState(boolean readState) {
        this.readState = readState;
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "StoriesBean{" + "type=" + type + ", id=" + id + ", ga_prefix='" + ga_prefix + '\'' +
               ", title='" + title + '\'' + ", images=" + images + ", readState=" + readState + '}';
    }

    public String getDisplay_date() {
        return display_date;
    }

    public void setDisplay_date(String display_date) {
        this.display_date = display_date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isMultipic() {
        return multipic;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
    }
}
