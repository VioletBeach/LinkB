package com.example.linkb;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hellmund.viewpager2indicator.ViewPager2Indicator;
import com.viewpagerindicator.LinePageIndicator;
import com.warkiz.widget.Indicator;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;


public class mainHomeFrag extends Fragment { ;
    DrawerLayout maindrawer;
    View drawerView;
    ImageButton close_drawer;
    ListView main_list;
    ViewPager2 photoview;
    CircleIndicator3 indicator;
    boolean photo_isFirst=true;
    boolean recycle_isFirst=true;
    ArrayList<Main_SampleData> titleDataList;
    ArrayList<RecommendEventItem> mList = new ArrayList<RecommendEventItem>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_logined_main_frag1, container, false);

        photoview=view.findViewById(R.id.photoview);
        maindrawer = view.findViewById(R.id.main_drawer_layout);
        drawerView = view.findViewById(R.id.main_nav_view);
        close_drawer = view.findViewById(R.id.btn_CloseDrawer);
        main_list = view.findViewById(R.id.main_nav_list);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.main_nav_toolbar);
        indicator=view.findViewById(R.id.photoview_indicator);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        //추천 이벤트
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        //프래그를 누를때마다 계속 아이템이 추가되어 구현
        if(recycle_isFirst) {
            addItem(R.drawable.test_pink, "2020 부산 YOLO 라이프", "2020.00.00 ~ 2020.00.00");
            addItem(R.drawable.test_yellow, "2020 퍼스널 모빌리티쇼", "2020.00.00 ~ 2020.00.00");
            addItem(R.drawable.test_sky, "3번 행사의 행사명", "2020.00.00 ~ 2020.00.00");
            addItem(R.drawable.test_green, "4번 행사의 행사명", "2020.00.00 ~ 2020.00.00");
            addItem(R.drawable.test_red, "5번 행사의 행사명", "2020.00.00 ~ 2020.00.00");
            recycle_isFirst=false;
        }

        EventAdapter eventAdapter = new EventAdapter(mList);
        recyclerView.setAdapter(eventAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        //
        this.InitializeListData();
        final Main_list_Adapter myAdapter = new Main_list_Adapter(getActivity(), titleDataList);
        main_list.setAdapter(myAdapter);

        main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(),
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



        // 보류 // new RestAPITaskCover("http://101.101.161.189/api/index.php/linkb_cover/select_cover_list").execute();
        List<String> items=new ArrayList<String>();
        items.add("items1");
        items.add("items2");
        items.add("items3");
        items.add("items4");
        final PhotoViewAdapter adapter=new PhotoViewAdapter(getActivity().getApplicationContext(),items);
        photoview.setAdapter(adapter);
        photoview.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        photoview.setOffscreenPageLimit(4);
        if(photo_isFirst==true) {
            photoview.setCurrentItem(adapter.getItemCount()/2); // 아이템 개수의 중간지점에서 시작
            photo_isFirst=false;
        }
        final float pageMargin=getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        final float pageOffset=getResources().getDimensionPixelOffset(R.dimen.offset);
        photoview.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float myOffSet=position*-(2*pageOffset+pageMargin);
                if(position<-1){
                    page.setTranslationX(-myOffSet);
                }else if (position <= 1){
                    float scaleFactor = Math.max(0.7f,1-Math.abs(position-0.14285715f));
                    page.setTranslationX(myOffSet);
                    page.setScaleY(scaleFactor);
                    page.setAlpha(scaleFactor);
                }else{
                    page.setAlpha(0f);
                    page.setTranslationX(myOffSet);
                }
            }
        });



        //포토뷰 끝
        indicator.setViewPager(photoview);
        indicator.createIndicators(5,0);

        photoview.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                                                   @Override
                                                   public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                                       super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                                                       if (positionOffsetPixels == 0) {
                                                           photoview.setCurrentItem(position);
                                                       }
                                                   }
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                indicator.animatePageSelected(position%5);
            }

        });



        setHasOptionsMenu(true);
        return view;
    }

    /* 2020-08-26 이재헌. 메인커버리스트 추가 (보류)
    class RestAPITaskCover extends AsyncTask<Integer, Void, String> {
        protected String mURL;

        public RestAPITaskCover(String url) {
            this.mURL = url;
        }

        @Override
        protected String doInBackground(Integer... params) {
            try {
                URL url = new URL(mURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDefaultUseCaches(false);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                conn.addRequestProperty("apikey", "starthub");

                StringBuffer buffer = new StringBuffer();

                OutputStreamWriter outputStream = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");

                PrintWriter writer = new PrintWriter(outputStream);
                writer.write(buffer.toString());
                System.out.println(buffer.toString());
                writer.flush();
                writer.close();

                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuilder builder = new StringBuilder();
                String str;
                while ((str = reader.readLine()) != null) {
                    builder.append(str + "\n");
                }
                String result = builder.toString();
                System.out.println(result);

                String coverUrl="https://kr.object.ncloudstorage.com/starthub-statics/linkb/cover/cover_20200818.jpg";
                return coverUrl;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String coverUrl) {
            url1=coverUrl;
        }
    }

     */



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                maindrawer.openDrawer(drawerView);
                return true;
            }
            case R.id.action_refresh:
                Toast.makeText(getActivity().getApplicationContext(),"로그아웃 (비상 탈출)", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity().getApplicationContext(), login.class);
                startActivity(intent);
                getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.icon_refresh, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu();
    }

    public void InitializeListData(){
        titleDataList = new ArrayList<Main_SampleData>();

        titleDataList.add(new Main_SampleData("행사목록", "다양한 행사들을 확인하세요"));
        titleDataList.add(new Main_SampleData("행사참여하기", "행사등록 및 참여확인"));

    }
    public void addItem(int img, String title, String day) {
        RecommendEventItem item = new RecommendEventItem();

        item.setDay(day);
        item.setTitle(title);
        item.setImageResource(img);
        mList.add(item);
    }


}