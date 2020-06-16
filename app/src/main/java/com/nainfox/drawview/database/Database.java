package com.nainfox.drawview.database;

import android.provider.BaseColumns;



public class Database {
    private  Database() {}

    public static class Entry implements BaseColumns{ // 다이어리 전체 내용에 대한 data String
            public static final String TABLE_NAME = "diary";
            public static final String TIME = "time";
            public static final String WEATHER  = "weather";
            public static final String URL = "url";
            public static final String ALL_URL = "allurl";
            public static final String WRITE = "write";
            public static final String userID = "userID";


    }
    /*
    public static class User implements BaseColumns{
        public static final String TABLE_NAME = "user";
        public static final String UserID = "userID";
        public static final String userName = "userName";
        public static final String userPassword = "userPassword";
        public static final String userEmail = "userEmail";
    }
    */



    private static final String TEXT_TYPE = " TEXT"; //TEXT_TYPE에 대한 String
    private static final String BLOB_TYPE = " BLOB"; // BLOB_TYPE에 대한 String
    private static final String COMMA_SEP = ","; // 콤마에 대한 String

    public static final String SQL_CREATE_ENTRIES = // 테이블 생성문
            "CREATE TABLE " + Entry.TABLE_NAME + " (" +
                    Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Entry.TIME + TEXT_TYPE + COMMA_SEP +
                    Entry.WEATHER + TEXT_TYPE + COMMA_SEP +
                    Entry.URL + BLOB_TYPE + COMMA_SEP +
                    Entry.ALL_URL + BLOB_TYPE + COMMA_SEP +
                    Entry.WRITE + TEXT_TYPE + COMMA_SEP +
                    ");";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Entry.TABLE_NAME; // 테이블 삭제 쿼리문
    /*
    public static final String SQL_CREATE_USER=
            "CREATE TABLE" + User.TABLE_NAME + "(" +
                    User.UserID + TEXT_TYPE + "PRIMARY KEY" + COMMA_SEP +
                    User.userPassword + TEXT_TYPE + COMMA_SEP +
                    User.userName + TEXT_TYPE + COMMA_SEP +
                    User.userEmail + TEXT_TYPE + COMMA_SEP + ")";*/
}
