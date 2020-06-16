package com.nainfox.drawview.view.main;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.nainfox.drawview.R;
import com.nainfox.drawview.database.Database;

import org.json.JSONObject;
/**
------------------------------------------------------------------------------------
    Created by 양석진, 황세호 : 회원정보 찾기를 위한 함수.
    php와 서버 통신을 위해 volley를 사용.
------------------------------------------------------------------------------------
 */
public class FindActivity extends AppCompatActivity {
    private EditText et_name, et_email, et_id, et_name2;
    private Button btn_findid, btn_findpass;
    private AlertDialog dialog;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_id = findViewById(R.id.et_id);
        et_name2 = findViewById(R.id.et_name2);
        btn_findid = findViewById(R.id.btn_findid);
        btn_findpass = findViewById(R.id.btn_findpass);


        btn_findid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = et_name.getText().toString();
                String userEmail = et_email.getText().toString();




                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            Toast.makeText(FindActivity.this, response, Toast.LENGTH_LONG).show();

                            JSONObject jsonResponse = new JSONObject(response);
                            String userID = jsonResponse.getString("userID");
                            if(userID!=null){
                                AlertDialog.Builder builder = new AlertDialog.Builder(FindActivity.this);
                                dialog = builder.setMessage("회원님의 아이디는"+userID+"입니다.").setPositiveButton("OK", null).create();
                                dialog.show();
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(FindActivity.this);
                                dialog = builder.setMessage("데이터베이스 오류입니다.").setNegativeButton("OK", null).create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                FindIDRequest findIDRequest = new FindIDRequest(userName, userEmail, responseListener);
                RequestQueue queue = Volley.newRequestQueue(FindActivity.this);
                queue.add(findIDRequest);

            }
        });

        btn_findpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = et_name2.getText().toString();
                String userID = et_id.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            Toast.makeText(FindActivity.this, response, Toast.LENGTH_LONG).show();

                            JSONObject jsonResponse = new JSONObject(response);
                            String userPassword = jsonResponse.getString("userPassword");
                            if(userPassword!=null){
                                AlertDialog.Builder builder = new AlertDialog.Builder(FindActivity.this);
                                dialog = builder.setMessage("회원님의 비밀번호는"+userPassword+"입니다.").setPositiveButton("OK", null).create();
                                dialog.show();
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(FindActivity.this);
                                dialog = builder.setMessage("데이터베이스 오류입니다.").setNegativeButton("OK", null).create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                FindPasswordRequest findPasswordRequest = new FindPasswordRequest(userName, userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(FindActivity.this);
                queue.add(findPasswordRequest);
            }
        });



    }

    public void setData(Cursor cursor){

    }
}
