package me.looorielovbb.boom.data.bean.zhihu;

/**
 * Created by Lulei on 2017/4/17.
 * time : 16:36
 * date : 2017/4/17
 * mail to lulei4461@gmail.com
 */
public class ReplyToBean {
    private String content;
    private int status;
    private int id;
    private String author;

    private int expandState = 0;

    public int getExpandState() {
        return expandState;
    }

    public void setExpandState(int expandState) {
        this.expandState = expandState;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
