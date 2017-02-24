package me.looorielovbb.boom.data.source.local;

import me.looorielovbb.boom.data.source.DataSource;
import me.looorielovbb.boom.data.source.ResponseListener;

/**
 * Created by Lulei on 2017/2/21.
 * time : 15:38
 * date : 2017/2/21
 * mail to lulei4461@gmail.com
 */

public class LocalDataSource implements DataSource{
    private static LocalDataSource INSTANCE;

    private LocalDataSource() {

    }

    public  static LocalDataSource getInstance(){
        if (INSTANCE==null){
            INSTANCE = new LocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getGirls(int page, ResponseListener listener) {

    }
}
