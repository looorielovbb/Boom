package me.looorielovbb.boom.config;

/**
 * Created by Lulei on 2017/2/9.
 * time : 17:36
 * date : 2017/2/9
 * mail to lulei4461@gmail.com
 */

public class Constants {
    public static final String THEME_MODE = "theme_mode";
    //===================================================//
    public static final int PAGE_COUNT = 10;

    public static class ID {
        public static final String BUGLY = "3e48dd529f"; //腾讯Bugly
        public static final String APIKEY = "0b2bdeda43b5688921839c8ecb20399b"; //豆瓣APIKEY
        public static final String UDID = "e4d660759bb4c3a29b3045ce5dfbdedbb97ecf4c";
    }

    public static class KEY {
        public static final String ID = "id";
        public static final String CASTID = "cast_id";
        public static final String CAST_NAME = "cast_name";
        public static final String CAST_PHOTO = "cast_photo";
        public static final String CAST = "cast";
        public static final String PHOTOT_URL = "photo_url";
        public static final String POSITION = "position";
        public static final String REVIEW = "review";
        public static final String TITLE = "title";
        public static final String VIEW_INFO = "view_info";
        public static final String SEARCH_KEY = "search_key";
        public static final String MOVIE_URLS = "movie_urls";
        public static final String CURRENT_TIME = "current_time";
    }

    public static class VIEW {
        public static final String LEFT = "left";
        public static final String TOP = "top";
        public static final String WIDTH = "width";
        public static final String HEIGHT = "height";
    }

    public static class CONFIG {
        public static final int ORIENTATION_LANDSCAPE = 2;
        public static final int ORIENTATION_PORTRAIT = 1;
    }

    public static class SP {
        public static final String SHARED_PREFERENCES_NAME = "BIG_BOOM";
        public static final String THEME = "theme";
    }

    public static class TYPE {
        public static final int MOVIE = 1;
        public static final int CAST = 2;
    }
}
