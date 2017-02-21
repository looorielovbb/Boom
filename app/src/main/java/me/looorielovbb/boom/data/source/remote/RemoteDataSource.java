package me.looorielovbb.boom.data.source.remote;


/**
 * Created by Lulei on 2017/2/21.
 * time : 15:39
 * date : 2017/2/21
 * mail to lulei4461@gmail.com
 */

public class RemoteDataSource {
    private static RemoteDataSource INSTANCE;

    private RemoteDataSource() {

    }

    public RemoteDataSource getInstance(){
        if (INSTANCE==null){
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }
}
