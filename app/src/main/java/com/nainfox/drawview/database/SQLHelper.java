package com.nainfox.drawview.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;



public class SQLHelper {
    private final String TAG = "### SQLHelper";
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public SQLHelper(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase(); // DB를 읽어옴
    }
    /*
    public long INSERT_USER(String userID, String userPassword, String userName, String userEmail){
        ContentValues values = new ContentValues();

        values.put(Database.User.UserID, userID);
        values.put(Database.User.userPassword, userPassword);
        values.put(Database.User.userName, userName);
        values.put(Database.User.userEmail, userEmail);

        return db.insert(Database.User.TABLE_NAME, null, values);
    }
    */
    /*
        일기 내용에 해당하는 data값들 insert
     */
    public long INSERT(String time, String weather, byte[] url, byte[] all_url, String write, String userID){
        ContentValues values = new ContentValues(); // contentvalue를 이용해 Data = Key + Value의 형태로 Set 가능
        Log.d(TAG, "db time : " + time);
        values.put(Database.Entry.TIME, time);
        values.put(Database.Entry.WEATHER, weather);
        values.put(Database.Entry.URL, url);
        values.put(Database.Entry.ALL_URL, all_url);
        values.put(Database.Entry.WRITE, write);
        values.put(Database.Entry.userID, userID);
        //values.put(Database.Entry.userID, userID);

        return db.insert(Database.Entry.TABLE_NAME, null, values); // diary 테이블에 values를 삽입
    }

    public Cursor SELECTALL(String userID){
        String[] colume = {
                Database.Entry._ID,
                Database.Entry.TIME,
                Database.Entry.WEATHER,
                Database.Entry.URL,
                Database.Entry.ALL_URL,
                Database.Entry.WRITE,
                Database.Entry.userID
        };

        String sortOrder = Database.Entry._ID + " DESC"; // 입력된 순서의 내림차순으로 정렬
        String selection = Database.Entry.userID+" LIKE ?";
        Cursor c = db.query(
                Database.Entry.TABLE_NAME,
                colume,
                selection,
                new String[]{ userID },
                null,
                null,
                sortOrder
        );


        /*Cursor c = db.query(
                Database.Entry.TABLE_NAME,
                colume,
                Database.Entry.userID+"=?",
                new String[] {userID},
                null,
                null,
                sortOrder
        );*/
        /*
        Cursor c = db.query(
                Database.Entry.TABLE_NAME,
                colume,
                null,
                null,
                null,
                null,
                sortOrder
        );
        */


        return c;
    }

    /*public Cursor SELECT(String userID){
        String[] colume = {
                Database.Entry._ID,
                Database.Entry.TIME,
                Database.Entry.WEATHER,
                Database.Entry.URL,
                Database.Entry.ALL_URL,
                Database.Entry.WRITE
        };

        Cursor cursor = null;

        try{
            cursor = db.query(Database.Entry.TABLE_NAME, null, "userID"+"=?", new String[]{userID}, null
            , null, "DESC");
        }catch(){

        }
    }*/

    public void DELETE(String id){
        String selection = Database.Entry._ID + " LIKE ?";
        String[] selctionArgs = { id };

        db.delete(Database.Entry.TABLE_NAME, selection, selctionArgs);
    }

    public void DELETE_ALL(){
        db.delete(Database.Entry.TABLE_NAME, null, null);
    }

    public int UPDATE(String id, String time, String weather, byte[] url, byte[] all_url, String write) {
        ContentValues values = new ContentValues();
        values.put(Database.Entry.TIME, time);
        values.put(Database.Entry.WEATHER, weather);
        values.put(Database.Entry.URL, url);
        values.put(Database.Entry.ALL_URL, all_url);
        values.put(Database.Entry.WRITE, write);

        String selection = Database.Entry._ID + " LIKE ?";
        String[] selectionArgs = { id };

        int count = db.update(Database.Entry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }

}
