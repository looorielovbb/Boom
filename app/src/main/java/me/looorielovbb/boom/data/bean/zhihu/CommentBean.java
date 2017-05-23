package me.looorielovbb.boom.data.bean.zhihu;

import java.util.List;



public class CommentBean {

    /**
     * author : xiaowei
     * content : 正解啊，比如中午登机，早上不吃空腹上，到时候飞机餐就算是两个圆圆的小面包都吃得有滋有味！开始怀疑，到底是飞机旅行、真是高大上呀！??哈哈…我见不少人特意留着飞机上发的食物带回去，落地后送人吃，很拽的来一句：飞机食品哦！…笑得不行鸟
     * avatar : http://pic1.zhimg.com/da8e974dc_im.jpg
     * time : 1413603692
     * reply_to : {"content":"习惯就好了。。 国内的航空公司基本都刷过。。 掌握了\u201c让飞机餐变得非常好吃\u201d的秘诀 同学们准备好 秘诀就是〈饿半天肚子登机〉 等到你吃到飞机餐那刻会泪流满面\u2026\u2026 好吧说了那么多其实也就因为六个字 没钱\u2026没钱\u2026没钱 所以，努力当大爷自己买小飞机吧！","status":0,"id":545589,"author":"Samuelback"}
     * id : 545838
     * likes : 2
     */

    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
