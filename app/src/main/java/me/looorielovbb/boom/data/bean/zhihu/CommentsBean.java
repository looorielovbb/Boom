package me.looorielovbb.boom.data.bean.zhihu;

/**
 * Created by Lulei on 2017/4/17.
 * time : 16:35
 * date : 2017/4/17
 * mail to lulei4461@gmail.com
 */
public class CommentsBean {
    private String author;
    private String content;
    private String avatar;
    private int time;
    /**
     * content : 习惯就好了。。 国内的航空公司基本都刷过。。 掌握了“让飞机餐变得非常好吃”的秘诀 同学们准备好 秘诀就是〈饿半天肚子登机〉 等到你吃到飞机餐那刻会泪流满面…… 好吧说了那么多其实也就因为六个字 没钱…没钱…没钱 所以，努力当大爷自己买小飞机吧！
     * status : 0
     * id : 545589
     * author : Samuelback
     */

    private ReplyToBean reply_to;
    private int id;
    private int likes;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public ReplyToBean getReply_to() {
        return reply_to;
    }

    public void setReply_to(ReplyToBean reply_to) {
        this.reply_to = reply_to;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

}
