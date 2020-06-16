package com.nainfox.drawview.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;



public class SizeFactory {

    public int getWindowWidth(Activity activity){ // 안드로이드 기기의 크기 호환성을 위해 스마트폰의 크기 정보 획득
        DisplayMetrics dm = activity.getApplicationContext().getResources().getDisplayMetrics();

        return dm.widthPixels; // 가로 화소 수
    }
}
