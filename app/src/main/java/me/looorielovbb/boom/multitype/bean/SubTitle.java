package me.looorielovbb.boom.multitype.bean;

import java.util.Date;

import me.looorielovbb.boom.utils.DateUtils;

/**
 * Created by Lulei on 2017/4/18.
 * time : 14:54
 * date : 2017/4/18
 * mail to lulei4461@gmail.com
 */
public class SubTitle {
    public String subtitle = "今日要闻";
    public String strDate;

    public SubTitle(String strDate) {
        this.strDate = strDate;
        this.subtitle = getFormatDate(strDate);
    }

    private String getFormatDate(String strDate) {
        if (strDate == null) {
            return "未知日期";
        }
        Date now = new Date(System.currentTimeMillis());
        Date date = DateUtils.str2date(strDate, "yyyyMMdd");
        String nowstr = DateUtils.date2str(now);
        String datestr = DateUtils.date2str(date);
        return datestr.equals(nowstr)?"今日要闻":datestr;
    }

}