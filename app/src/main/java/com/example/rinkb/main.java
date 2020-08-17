package com.example.rinkb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class main extends AppCompatActivity {

    // FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    private FragmentManager fragmentManager = getSupportFragmentManager();
    // 4개의 메뉴에 들어갈 Fragment들
    private mainHomeFrag menu1Fragment = new mainHomeFrag();
    private mainSearchFrag menu2Fragment = new mainSearchFrag();
    private mainAlertFrag menu3Fragment = new mainAlertFrag();
    private mainTempFrag menu4Fragment = new mainTempFrag();

    DrawerLayout maindrawer;
    View drawerView;
    ImageButton close_drawer;
    ListView main_list;

    ArrayList<Main_SampleData> titleDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logined_main);

        maindrawer = findViewById(R.id.main_drawer_layout);
        drawerView = findViewById(R.id.main_nav_view);
        close_drawer = findViewById(R.id.btn_CloseDrawer);
        main_list = findViewById(R.id.main_nav_list);



        this.InitializeLayout();
        this.InitializeListData();
        final Main_list_Adapter myAdapter = new Main_list_Adapter(this, titleDataList);
        main_list.setAdapter(myAdapter);

        main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        myAdapter.getItem(position).getTitle(),
                        Toast.LENGTH_SHORT).show();
            }
        });


        close_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maindrawer.closeDrawer(drawerView);
            }
        });




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        // 첫 화면 지정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.logined_main_frame_layout, menu1Fragment).commitAllowingStateLoss();

        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.homeItem: {
                        transaction.replace(R.id.logined_main_frame_layout, menu1Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.searchItem: {
                        transaction.replace(R.id.logined_main_frame_layout, menu2Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.alertItem: {
                        transaction.replace(R.id.logined_main_frame_layout, menu3Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.tempItem: {
                        transaction.replace(R.id.logined_main_frame_layout, menu4Fragment).commitAllowingStateLoss();
                        break;
                    }
                }

                return true;
            }
        });
    }

    public void InitializeListData(){
        titleDataList = new ArrayList<Main_SampleData>();

        titleDataList.add(new Main_SampleData("행사목록", "다양한 행사들을 확인하세요"));
        titleDataList.add(new Main_SampleData("행사참여하기", "행사등록 및 참여확인"));

    }

    public void InitializeLayout(){
        Toolbar toolbar = findViewById(R.id.main_nav_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                maindrawer.openDrawer(drawerView);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

