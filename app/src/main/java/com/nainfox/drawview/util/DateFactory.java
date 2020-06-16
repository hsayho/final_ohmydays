package com.nainfox.drawview.util;

import java.util.Calendar;
import java.util.Date;

public class DateFactory {
    private final Calendar c;
    private int year, month, day;
    private Date date;

    public DateFactory(){ // 현재 날짜 불러오기
        c = Calendar.getInstance();
        year = c.get(Calendar.YEAR); // current year
        month = c.get(Calendar.MONTH); // current month
        day = c.get(Calendar.DAY_OF_MONTH); // current day
    }

    public void setCalendar(int year, int month, int day){
        c.set(year, month, day);
    }

    public int getYear(){
        return year;
    }
    public int getMonth(){
        return month;
    }
    public int getDay(){
        return day;
    }
    public String getDayOfWeek(){
        int nWeek = c.get(Calendar.DAY_OF_WEEK);
        String strWeek = "-";
        switch (nWeek){
            case 1:
                strWeek = "일";
                break;
            case 2:
                strWeek = "월";
                break;
            case 3:
                strWeek = "화";
                break;
            case 4:
                strWeek = "수";
                break;
            case 5:
                strWeek = "목";
                break;
            case 6:
                strWeek = "금";
                break;
            case 7:
                strWeek = "토";
                break;
        }
        return strWeek;
    }

    public Long getTime(){
        long now = System.currentTimeMillis();

        return now;
    }

}
