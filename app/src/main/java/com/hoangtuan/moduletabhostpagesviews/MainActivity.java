package com.hoangtuan.moduletabhostpagesviews;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hoangtuan.moduletabhostpagesviews.adapter.NaviAdapter;
import com.hoangtuan.moduletabhostpagesviews.adapter.PagesAdapter;
import com.hoangtuan.moduletabhostpagesviews.fragments.MainFragment;
import com.hoangtuan.moduletabhostpagesviews.fragments.OriginFragment;
import com.hoangtuan.moduletabhostpagesviews.helper.RecyclerItemClickListener;
import com.hoangtuan.moduletabhostpagesviews.model.NaviModel;
import com.hoangtuan.moduletabhostpagesviews.popular.TabAllFragment;
import com.hoangtuan.moduletabhostpagesviews.popular.TabBoyFragment;
import com.hoangtuan.moduletabhostpagesviews.popular.TabGirlFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        TabAllFragment.OnFragmentInteractionListener,
        TabBoyFragment.OnFragmentInteractionListener,
        TabGirlFragment.OnFragmentInteractionListener {

    String DATABASE_NAME = "BabyNames.sqlite";
    String DB_PATH_SUFFIX = "/databases/";

    private RecyclerView recyNavi;
    private NaviAdapter naviAdapter;
    private ArrayList<NaviModel> naviModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xuLySaoChepDuDlieuVaoHeThong();
        Toolbar toolbar = (Toolbar) findViewById(R.id.tBar);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            // on first time display view for first nav item
            setupTheWindow(0);
        }

        recyNavi=(RecyclerView)findViewById(R.id.recyNavi);
        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyNavi.setLayoutManager(manager);
        recyNavi.setHasFixedSize(true);

        naviModels = new ArrayList<>();
        naviModels.add(new NaviModel("Popular",R.drawable.ic_location));
        naviModels.add(new NaviModel("Popular",R.drawable.ic_location));
        naviModels.add(new NaviModel("Popular",R.drawable.ic_location));
        naviModels.add(new NaviModel("Popular",R.drawable.ic_location));


        naviAdapter=new NaviAdapter(this,naviModels);
        recyNavi.setAdapter(naviAdapter);
        recyNavi.addOnItemTouchListener(new RecyclerItemClickListener(this, recyNavi, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
              setupTheWindow(position);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }



    private void xuLySaoChepDuDlieuVaoHeThong() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) { // nếu chưa tồn tại
            try {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying sucess from Assets folder", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void CopyDataBaseFromAsset() {
        try {
            InputStream inputStream;
            inputStream = getAssets().open(DATABASE_NAME);
            String outFileName = layDuongDanLuuTru();
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            //tao thu muc
            if (!f.exists()) {
                f.mkdir();
            }
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int lenght;
            //sao chep tu input vao buff
            while ((lenght = inputStream.read(buff)) > 0) {
                //ghi tu buff vao output
                outputStream.write(buff, 0, lenght);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            Log.e("Loi", e.toString());
        }
    }

    private String layDuongDanLuuTru() {
        //lấy đường dẫn dataDir trỏ vào đúng tên packer
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    protected void setupTheWindow(int position) {

        Fragment fragment = null;
        switch(position)
        {
            case 0:
                fragment = new MainFragment();
                break;
            case 1:
                fragment = new OriginFragment();
                break;
            case 2:
                fragment = new OriginFragment();
                break;
            case 3:
                fragment = new OriginFragment();
                break;
            default:
                break;
        }
        if(fragment != null)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragMain, fragment, "a")
                    .commit(); // Adding a tag here by nav_titles[pos]


        }
        else
        {
            Toast.makeText(getApplicationContext(), "Made by a developer who thinks he is cool. Follow me @advait1911. Icon made by Michael Flarup.",  Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {

        FragmentManager fm = getSupportFragmentManager(); if (fm.getBackStackEntryCount() > 0) { fm.popBackStack(); } else { super.onBackPressed(); }
    }
}
