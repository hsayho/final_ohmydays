package com.nainfox.drawview.util;

import android.content.Context;
import android.content.SharedPreferences;



public class SharedData {
    private final String DB = "db";

    private final String ISFIRST = "first"; // first : true

    private final String NAME = "name";

    private SharedPreferences sharedPreferences; // 서버없이 간단한 데이터들을 저장하기 위한 매체
    private SharedPreferences.Editor editor; // editor를 통해 값을 저장한다


    public SharedData(Context context){
        sharedPreferences = context.getSharedPreferences(DB, Context.MODE_PRIVATE); // DB에 저장된 "db"라는 파일에 저장
        editor = sharedPreferences.edit();
    }


    // getter
    public String getName(){
        return sharedPreferences.getString(NAME , "null");
    }
    public boolean getIsFirst(){
        return sharedPreferences.getBoolean(ISFIRST , true);
    }


    // setter
    public void setName(String name) {
        editor.putString(NAME, name);
        editor.commit();
    }
    public void setIsFirst(boolean isFirst) {
        editor.putBoolean(ISFIRST, isFirst);
        editor.commit();
    }


    // reset
    public void reset(){
        editor.putString(NAME, "null");
        editor.putBoolean(ISFIRST, true);

        editor.commit();
    }


}
