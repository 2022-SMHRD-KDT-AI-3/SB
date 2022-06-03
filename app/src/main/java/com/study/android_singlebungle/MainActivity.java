package com.study.android_singlebungle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    Home fragment1;
    Feed fragment2;

    Notifications fragment4;
    Mypage fragment5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnv = findViewById(R.id.bnv);
        fragment1 = new Home();
        fragment2 = new Feed();
        fragment4 = new Notifications();
        fragment5 = new Mypage();


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment1)
                .commit();

        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                // 메뉴아이템의 id값 반환하는 메소드 사용
                int itemID = item.getItemId();

                if(itemID == R.id.itemHome){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, fragment1)
                            .commit();
                } else if(itemID == R.id.itemFeed){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, fragment2)
                            .commit();
                } else if(itemID == R.id.itemNoti){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, fragment4)
                            .commit();
                }else if(itemID == R.id.itemMypage){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, fragment5)
                            .commit();
                }
                return false;
            }
        });


    }
}









































