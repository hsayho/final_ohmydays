package com.nainfox.drawview.view.main;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
------------------------------------------------------------------------------------
    Created by 황세호 : 회원 Password를 찾기위한 함수. php파일에 parameter값으로
    userName과 userID를 보낸다.
------------------------------------------------------------------------------------

 */
public class FindPasswordRequest extends StringRequest {
    final static private String URL ="http://seho4815.dothome.co.kr/findpass.php";
    private Map<String, String> parameters;

    public FindPasswordRequest(String userName, String userID, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null); // 해당 URL에 POST 방식으로 파라미터들을 전송함
        parameters = new HashMap<>();
        parameters.put("userName", userName);
        parameters.put("userID", userID);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError{
        return parameters;
    }
}
