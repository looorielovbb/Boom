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

public class Repository {
    private static Repository INSTANCE;

    @NonNull private LocalDataSource mLocalDataSource;
    @NonNull private RemoteDataSource mRemoteDataSource;

    private Repository(@NonNull LocalDataSource mLocalDataSource,
                       @NonNull RemoteDataSource mRemoteDataSource) {
        this.mLocalDataSource= mLocalDataSource;
        this.mRemoteDataSource =mRemoteDataSource;
    }

    public static Repository getInstance(@NonNull LocalDataSource mLocalDataSource,
                                         @NonNull RemoteDataSource mRemoteDataSource){
        if (null== INSTANCE){
            INSTANCE = new Repository(mLocalDataSource,mRemoteDataSource);
        }
        return INSTANCE;
    }
}
