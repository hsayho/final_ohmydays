package com.nainfox.drawview.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
------------------------------------------------------------------------------------
    Created by 황세호 : 쿼리문의 실행을 담당하며 버전에 대해 upgrade, downgrade 구현.
    upgrade와 downgrad는 sqliteHelper 관련 자료를 찾다가 첨부한 것일뿐 실제로 사용하
    지는 않음.
------------------------------------------------------------------------------------
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "diary.db";

    public DbHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //db.execSQL(Database.SQL_CREATE_USER);
            db.execSQL(Database.SQL_CREATE_ENTRIES); // 일기장 테이블을 만드는 SQL

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Database.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}
