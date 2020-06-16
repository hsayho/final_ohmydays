package com.nainfox.drawview.view.main;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
------------------------------------------------------------------------------------
    Created by 황세호 : 아이디가 이미 존재하는지에 대해 검증하는 함수.
    php파일에 parameter 값으로 userID를 보낸다.
------------------------------------------------------------------------------------
 */
public class ValidateRequest extends StringRequest {
    final static private String URL ="http://seho4815.dothome.co.kr/UserValidate.php";
    private Map<String, String> parameters;

    public ValidateRequest(String userID, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null); // 해당 URL에 POST 방식으로 파라미터들을 전송함
        parameters = new HashMap<>();
        parameters.put("userID", userID);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError{
        return parameters;
    }
}
