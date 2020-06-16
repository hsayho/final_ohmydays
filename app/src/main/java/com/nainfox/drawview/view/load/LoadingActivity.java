package com.nainfox.drawview.view.load;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.nainfox.drawview.R;
import com.nainfox.drawview.util.SharedData;
import com.nainfox.drawview.view.common.BasicActivity;
import com.nainfox.drawview.view.main.LoginActivity;
import com.nainfox.drawview.view.main.MainActivity;

public class LoadingActivity extends BasicActivity{

    private AnimationDrawable animationDrawable; // 연속된 이미지를 사용하여 순서대로 표시하여 애니메이션을 만듬
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);


        init();

        checkIsFirst();
    }

    // 첫실행 여부
    private void checkIsFirst(){
        SharedData sharedData = new SharedData(this);
        if(sharedData.getIsFirst()){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(LoadingActivity.this, TutorialActivity.class); // 튜토리얼 화면으로 전환
                    startActivity(i);
                    finish();
                }
            },2000); // 2초 뒤에 실행되게끔 딜레이를 준다.
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(LoadingActivity.this, LoginActivity.class); // 첫 실행이 아니면 메인화면으로 전환
                    startActivity(i);
                    finish();
                }
            },2000);
        }
    }


    private void init(){
        imageView = (ImageView) findViewById(R.id.loadingView);
        imageView.setBackgroundResource(R.drawable.anim_loading); // 로딩화면

        animationDrawable = (AnimationDrawable)imageView.getBackground(); // 로딩화면에 애니메이션 부여
    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) { // 윈도우의 focus가 변경되었을 경우 호출!
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            animationDrawable.start();
        }else{
            animationDrawable.stop();
        }
    }
}
