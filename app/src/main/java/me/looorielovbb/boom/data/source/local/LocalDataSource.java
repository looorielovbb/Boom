package me.looorielovbb.boom.data.source.local;

/**
 * Created by Lulei on 2017/2/21.
 * time : 15:38
 * date : 2017/2/21
 * mail to lulei4461@gmail.com
 */

public class LocalDataSource {
    private static LocalDataSource INSTANCE;

    private LocalDataSource() {

    }

    public  LocalDataSource getInstance(){
        if (INSTANCE==null){
            INSTANCE = new LocalDataSource();
        }
        return INSTANCE;
    }
}
