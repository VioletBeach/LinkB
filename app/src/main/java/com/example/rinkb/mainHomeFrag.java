package com.example.rinkb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;


public class mainHomeFrag extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_logined_main_frag1, container, false);
        List<String> items=new ArrayList<String>();
        items.add("items1");
        items.add("items2");
        items.add("items3");
        items.add("items4");
        items.add("items5");
        PhotoViewAdapter adapter=new PhotoViewAdapter(getActivity().getApplicationContext(),items);
        ViewPager2 photoview=view.findViewById(R.id.photoview);
        photoview.setAdapter(adapter);
        photoview.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        photoview.setOffscreenPageLimit(4);
        photoview.setCurrentItem(1000);
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
        return view;
    }

}