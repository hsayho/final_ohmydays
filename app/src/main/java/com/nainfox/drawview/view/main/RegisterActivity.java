package com.nainfox.drawview.view.main;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.nainfox.drawview.database.DbHelper;
import com.nainfox.drawview.database.SQLHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity { // 액티비티 시작시 처음으로 실행되는 생명주기!
    private EditText et_id, et_password, et_name, et_email;
    private Button btn_register, btn_idcheck;
    private AlertDialog dialog;

    SQLiteDatabase db;
    DbHelper dbHelper;

    private boolean validate = false;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
            dbHelper = new DbHelper(this);

            et_id = findViewById(R.id.et_id);
            et_password = findViewById(R.id.et_password);
            et_name = findViewById(R.id.et_name);
            et_email = findViewById(R.id.et_email);

            //아이디 체크
            btn_idcheck = findViewById(R.id.btn_idcheck);

            btn_idcheck.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                String userID = et_id.getText().toString();
                if(validate){
                    return;
                }

                if(userID.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("ID is empty").setPositiveButton("OK", null).create();
                    dialog.show();
                    return;

                }
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_LONG).show();

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("사용 가능한 ID입니다.").setPositiveButton("OK", null).create();

                                dialog.show();
                                et_id.setEnabled(false); // 아이디값을 바꿀 수 없도록 함
                                validate = true;
                                et_id.setBackgroundColor(getResources().getColor(R.color.colorGray));
                                btn_idcheck.setBackgroundColor(getResources().getColor(R.color.colorGray));

                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("이미 사용중인 ID입니다.").setNegativeButton("OK", null).create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                ValidateRequest validateRequest = new ValidateRequest(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(validateRequest);
            }
            });

            // 회원가입 버튼 클릭시 수행
            btn_register = findViewById(R.id.btn_register);
            btn_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                // EditText에 현재 입력되어있는 값을 get해온다.

                String userID = et_id.getText().toString();
                String userPassword = et_password.getText().toString();
                String userName = et_name.getText().toString();
                String userEmail = et_email.getText().toString();
                if(!validate){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("아이디를 체크해주세요!").setNegativeButton("OK", null).create();
                    dialog.show();
                    return;

                }

                if(userID.equals("") || userPassword.equals("") || userName.equals("") || userEmail.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog=builder.setMessage("입력되지 않은 사항이 있습니다.").setNegativeButton("OK", null).create();
                    dialog.show();
                    return;
                }

                /*
                try{
                    SQLHelper sqlHelper = new SQLHelper(RegisterActivity.this);
                    long result = sqlHelper.INSERT_USER(userID, userPassword, userName, userEmail);
                    if(result<0){
                        Toast.makeText(RegisterActivity.this, "회원가입에 실패하였습니다.", Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(RegisterActivity.this, "회원가입 완료.", Toast.LENGTH_SHORT).show();
                    finish();

                    }catch(Exception e){
                    Toast.makeText(RegisterActivity.this, "회원가입에 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                    }
                    */

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if(success){ // 회원가입에 성공한 경우
                                Toast.makeText(getApplicationContext(), "회원가입이 성공적으로 이루어졌습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "회원가입이 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                // 서버로 Volley를 이용해서 요청함
                RegisterRequest registerRequest = new RegisterRequest(userID, userPassword, userName, userEmail, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}
