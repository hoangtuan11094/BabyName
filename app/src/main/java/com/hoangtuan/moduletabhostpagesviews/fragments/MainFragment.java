package com.hoangtuan.moduletabhostpagesviews.fragments;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.hoangtuan.moduletabhostpagesviews.R;
import com.hoangtuan.moduletabhostpagesviews.adapter.MainAdapter;
import com.hoangtuan.moduletabhostpagesviews.adapter.PagesAdapter;
import com.hoangtuan.moduletabhostpagesviews.model.NameMainModel;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class MainFragment extends Fragment{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    //==========================
    private SQLiteDatabase database = null;
    private String DATABASE_NAME = "BabyNames.sqlite";
    private RecyclerView recyAll;
    private MainAdapter mainAdapter;
    private ArrayList<NameMainModel> nameMainModels;

    PagesAdapter pagesAdapter;
    Spinner spinner;
    private String year;
    TabLayout tabLayout;
    ViewPager viewPager;

    public MainFragment() {

    }


    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main, container, false);


        nameMainModels = new ArrayList<>();
        //TODO: Lấy dữ liệu từ SQLite
        database = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);


         tabLayout=(TabLayout)view.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("ALL"));
        tabLayout.addTab(tabLayout.newTab().setText("BOY"));
        tabLayout.addTab(tabLayout.newTab().setText("GIRL"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

          viewPager=(ViewPager)view.findViewById(R.id.viewPages);


         spinner=getActivity().findViewById(R.id.spinYear);
         spinner.setVisibility(View.VISIBLE);
        String[] list=getActivity().getResources().getStringArray(R.array.year);
        ArrayAdapter<String> stringArrayAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,list);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(stringArrayAdapter);



        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        year = (String) spinner.getSelectedItem();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year= parent.getItemAtPosition(position).toString();
              getListforYear(year);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                getListforYear(String.valueOf(spinner.getSelectedItem()));
            }
        });
        pagesAdapter=new PagesAdapter(getChildFragmentManager(),tabLayout.getTabCount(),year);
        viewPager.setAdapter(pagesAdapter);
    }

    private void init(View view) {


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.drawer,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void getListforYear(String year){
        Cursor cursor = database.rawQuery("select ZRANKING,ZBABYNAME,ZGENDERTYPE,ZORIGINS,ZPOPULARBABYNAME FROM ZBABYNAME INNER JOIN ZBABYPOPULARITY ON ZBABYNAME.Z_PK=ZBABYPOPULARITY.ZPOPULARBABYNAME WHERE ZBABYPOPULARITY.ZRANKING<=100 AND ZBABYPOPULARITY.ZYEAR="+year+" ORDER BY ZRANKING ASC", null);
        nameMainModels.clear();
        while (cursor.moveToNext()) {
            NameMainModel nameMainModel = new NameMainModel("#" + cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
            nameMainModels.add(nameMainModel);
        }
        cursor.close();
    }
}
