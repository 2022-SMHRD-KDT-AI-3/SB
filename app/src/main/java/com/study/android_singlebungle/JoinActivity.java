package com.study.android_singlebungle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity {

    TextView joinId, joinPw, joinPwCheck, joinNick;
    Button btnJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        //기입항목
        joinId = findViewById(R.id.joinId);
        joinPw = findViewById(R.id.joinPw);
        joinPwCheck = findViewById(R.id.joinPwCheck);
        joinNick = findViewById(R.id.joinNick);
        btnJoin = findViewById(R.id.btnJoin);

        //회원가입 버튼 클릭 시
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = joinId.getText().toString(); //아이디
                String pw = joinPw.getText().toString(); //비밀번호
                String pwC = joinPwCheck.getText().toString(); //비밀번호확인
                String nick = joinNick.getText().toString(); //닉네임

                if (id.equals("")) {
                    Toast.makeText(getApplicationContext(), "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    //signid로 커서 이동
                    joinId.requestFocus();

                    //키보드 보이게 하는 부분
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

                } else if (pw.equals("")) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    //signpassword로 커서 이동
                    joinPw.requestFocus();

                    //키보드 보이게 하는 부분
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                } else if (pwC.equals("")) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    //signrepassword로 커서 이동
                    joinPwCheck.requestFocus();

                    //키보드 보이게 하는 부분
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

                } else if (nick.equals("")) {
                    Toast.makeText(getApplicationContext(), "닉네임을 확인해주세요.", Toast.LENGTH_SHORT).show();
                    //signrepassword로 커서 이동
                    joinNick.requestFocus();

                    //키보드 보이게 하는 부분
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);


                } else if (!pw.equals(pwC)) {
                    //비밀번호와 비밀번호 확인이 같지 않다면
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    //signpassword로 커서 이동
                    joinPw.requestFocus();

                    //키보드 보이게 하는 부분
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                } else {
                    //회원가입 정보를 모두 입력 했다면 회원가입 정보 쉐어드에 저장
                    //signCheck();

                    //버튼을 누를 때마다 기존 내용 초기화
                    joinId.setText("");
                    joinPw.setText("");
                    joinPwCheck.setText("");
                    joinNick.setText("");

                    Intent intent = new Intent(view.getContext(), LoginActivity.class);
                    startActivity(intent);

                }



            }
        });
    }
}