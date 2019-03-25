package com.wind.springsession.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    private static final String YEAR = "yyyy";
    private static final String YEAR_TO_MONTH = "yyyy-MM";
    private static final String YEAR_TO_DAY = "yyyy-MM-dd";
    private static final String YEAR_TO_HOUR = "yyyy-MM-dd HH";
    private static final String YEAR_TO_MINUTE = "yyyy-MM-dd HH:mm";
    private static final String YEAR_TO_SECOND = "yyyy-MM-dd HH:mm:ss";

    public static String getYear(Date date){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YEAR);

        return simpleDateFormat.format(date);
    }

    public static String getYearToMonth(Date date){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YEAR_TO_MONTH);

        return simpleDateFormat.format(date);
    }

    public static String getYearToDay(Date date){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YEAR_TO_DAY);

        return simpleDateFormat.format(date);
    }

    public static String getYearToHour(Date date){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YEAR_TO_HOUR);

        return simpleDateFormat.format(date);
    }

    public static String getYearToMinute(Date date){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YEAR_TO_MINUTE);

        return simpleDateFormat.format(date);
    }

    public static String getYearToSecond(Date date){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YEAR_TO_SECOND);

        return simpleDateFormat.format(date);
    }

    @PostConstruct
    public void init(){

        logger.info("currentTime: {}", DateUtil.getYearToSecond(new Date()));
    }

}


