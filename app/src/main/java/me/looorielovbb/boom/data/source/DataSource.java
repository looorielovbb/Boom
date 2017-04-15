package me.looorielovbb.boom.data.source;

import java.util.List;

import me.looorielovbb.boom.data.bean.gank.Meizi;
import me.looorielovbb.boom.data.bean.others.ZhuangbiImage;
import rx.Observable;

/**
 * Created by Lulei on 2017/2/24.
 * time : 16:56
 * date : 2017/2/24
 * mail to lulei4461@gmail.com
 */

public interface DataSource {
    Observable<List<Meizi>> getMeizi(int page);

    Observable<List<ZhuangbiImage>> getEmoji(String keyword);
}
