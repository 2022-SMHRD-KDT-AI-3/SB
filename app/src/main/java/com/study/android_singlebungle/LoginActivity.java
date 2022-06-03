package com.study.android_singlebungle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    TextView tvFindId, tvFindPw, tvJoin;
    Button btnLogin;
    EditText editText, editText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //초기화
        tvFindId = findViewById(R.id.tvFindId);
        tvFindPw = findViewById(R.id.tvFindPw);
        tvJoin = findViewById(R.id.tvJoin);
        btnLogin = findViewById(R.id.btnLogin);
//
//        editText = findViewById(R.id.editText);
//        editText2 =findViewById(R.id.editText2);

        //아이디 찾기 페이지로 이동
        tvFindId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //비밀번호 찾기 페이지로 이동
        tvFindPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //회원가입 페이지로 이동
        tvJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), JoinActivity.class);
                startActivity(intent);


            }
        });

        //로그인 (일단 안됨 수정해야함)
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//회원가입창에서 받은 데이터를 여기에 넘겨줌
                Intent receiveIntent=getIntent();
                String id=receiveIntent.getStringExtra("joinId");
                String pw=receiveIntent.getStringExtra("joinPw");
                String nick=receiveIntent.getStringExtra("joinNick");

                String joinId=editText.getText().toString();
                String joinPw=editText2.getText().toString();

//아이디 비밀번호 일치일때는 메인 화면에 진입. 불일치시 토스트 메시지.
                if(joinId.equals(id)) {
                    if (joinPw.equals(pw)) {
                        Intent intent = new Intent(view.getContext(), MainActivity.class);

                        Toast.makeText(getApplicationContext(), nick + "님 안녕하세요.", Toast.LENGTH_SHORT).show();
                        //startActivityForResult(intent, REQUEST_CODE_MAIN);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "아이디 혹은 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}