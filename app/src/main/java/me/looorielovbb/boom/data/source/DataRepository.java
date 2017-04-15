package me.looorielovbb.boom.data.source;

import android.support.annotation.NonNull;

import java.util.List;

import me.looorielovbb.boom.data.bean.gank.Meizi;
import me.looorielovbb.boom.data.bean.others.ZhuangbiImage;
import me.looorielovbb.boom.data.source.local.LocalDataSource;
import me.looorielovbb.boom.data.source.remote.RemoteDataSource;
import rx.Observable;

/**
 * Created by Lulei on 2017/2/21.
 * time : 15:32
 * date : 2017/2/21
 * mail to lulei4461@gmail.com
 */

public class DataRepository implements DataSource {
    private static DataRepository INSTANCE;

    @NonNull private LocalDataSource mLocalDataSource;
    @NonNull private RemoteDataSource mRemoteDataSource;

    private DataRepository(@NonNull LocalDataSource mLocalDataSource,
                           @NonNull RemoteDataSource mRemoteDataSource) {
        this.mLocalDataSource = mLocalDataSource;
        this.mRemoteDataSource = mRemoteDataSource;
    }

    public static DataRepository getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new DataRepository(LocalDataSource.getInstance(),
                                          RemoteDataSource.getInstance());
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<Meizi>> getMeizi(int page) {
        return mRemoteDataSource.getMeizi(page);
    }

    @Override
    public Observable<List<ZhuangbiImage>> getEmoji(String keyword) {
        return null;
    }
}
