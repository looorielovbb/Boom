package me.looorielovbb.boom.data.source;

import android.support.annotation.NonNull;

import me.looorielovbb.boom.data.source.local.LocalDataSource;
import me.looorielovbb.boom.data.source.remote.RemoteDataSource;

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
    public void getGirls(int page, ResponseListener listener) {
        mRemoteDataSource.getGirls(page, listener);
    }
}
