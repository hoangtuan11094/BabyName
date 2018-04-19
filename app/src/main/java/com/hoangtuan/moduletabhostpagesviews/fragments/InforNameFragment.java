package com.hoangtuan.moduletabhostpagesviews.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoangtuan.moduletabhostpagesviews.R;
import com.hoangtuan.moduletabhostpagesviews.adapter.InforMeanAdapter;
import com.hoangtuan.moduletabhostpagesviews.adapter.InforPopularityAdapter;
import com.hoangtuan.moduletabhostpagesviews.helper.RecyclerItemClickListener;

import com.hoangtuan.moduletabhostpagesviews.model.InforMeanModel;
import com.hoangtuan.moduletabhostpagesviews.model.InforPopularityModel;
import com.hoangtuan.moduletabhostpagesviews.model.NameMainModel;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class InforNameFragment extends Fragment {

    private RecyclerView recyInforMean,recyInforGirl,recyInforBoy;
    private InforMeanAdapter inforMeanAdapter;
    private ArrayList<InforMeanModel> inforMeanModels;
    private ArrayList<InforPopularityModel> inforPopularityModels;

    private SQLiteDatabase database = null;
    private String DATABASE_NAME = "BabyNames.sqlite";

    private TextView txtInforName,txtInforGender;
    private NameMainModel nameMainModel;

    private LinearLayout llGirl,llBoy;
    private InforPopularityAdapter inforPopularityAdapter;

    public InforNameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view=inflater.inflate(R.layout.fragment_infor_name, container, false);




        init(view);
        return view;
    }

    private void init(View view) {

        final Bundle bundle=getArguments();
        nameMainModel= (NameMainModel) bundle.getSerializable("name");

        llGirl=(LinearLayout)view.findViewById(R.id.llGirl);
        llBoy=(LinearLayout)view.findViewById(R.id.llBoy);
        txtInforGender=(TextView)view.findViewById(R.id.txtInforGender);
        txtInforName=(TextView)view.findViewById(R.id.txtInforName);
        recyInforMean=(RecyclerView)view.findViewById(R.id.recyInforMeaning);
        recyInforGirl =(RecyclerView)view.findViewById(R.id.recyInforGirl);
        recyInforBoy=(RecyclerView)view.findViewById(R.id.recyInforBoy);

        txtInforName.setText(nameMainModel.getName());
        txtInforGender.setText(nameMainModel.getGender()+", [ "+nameMainModel.getOrigin()+" ]");

        //TODO: Recycleview Meaning
        inforMeanModels=new ArrayList<>();
        LinearLayoutManager manager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyInforMean.setLayoutManager(manager);
        recyInforMean.setNestedScrollingEnabled(false);

        //TODO: Lấy dữ liệu Meaning từ SQLite
        database = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("select ZMEANING,ZORIGINNAME,ZLATITUDE,ZLONGTITUDE FROM ZMEANING INNER JOIN ZORIGIN ON ZMEANING.ZMEANINGORIGIN=ZORIGIN.Z_PK WHERE ZMEANING.ZMEANINGBABYNAME="+nameMainModel.getPopularname(), null);
        inforMeanModels.clear();
        while (cursor.moveToNext()) {
            InforMeanModel inforMeanModel = new InforMeanModel(cursor.getString(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3));
            inforMeanModels.add(inforMeanModel);
        }
        cursor.close();
        inforMeanAdapter=new InforMeanAdapter(getActivity(),inforMeanModels);
        recyInforMean.setAdapter(inforMeanAdapter);

        //TODO: Set click item RecycleView Meaning
        recyInforMean.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyInforMean, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                Bundle bundle1=new Bundle();
                bundle1.putSerializable("Mean",inforMeanModels.get(position));
                bundle1.putString("NameMean",nameMainModel.getName());

                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                InforMeanFragment inforMeanFragment=new InforMeanFragment();
                inforMeanFragment.setArguments(bundle1);

                fragmentTransaction.replace(R.id.fragMain,inforMeanFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        //TODO: Recylceview Popularity GIRL
        if (nameMainModel.getGender().equals("Unisex") || nameMainModel.getGender().equals("Girl")){
            llGirl.setVisibility(View.VISIBLE);
            recyInforGirl.setNestedScrollingEnabled(false);
            recyInforGirl.setHasFixedSize(true);
            inforPopularityModels=new ArrayList<>();
            LinearLayoutManager manager1=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
            recyInforGirl.setLayoutManager(manager1);

            Cursor cursorBoy = database.rawQuery("select ZYEAR,ZUSEDCOUNT,ZRANKING FROM ZBABYPOPULARITY WHERE ZPOPULARBABYNAME="+nameMainModel.getPopularname()+" AND ZGENDER=2 ORDER BY ZYEAR DESC", null);
            inforPopularityModels.clear();
            while (cursorBoy.moveToNext()) {
                InforPopularityModel inforPopularityModel = new InforPopularityModel(""+cursorBoy.getInt(0),cursorBoy.getInt(1)+"births","#"+cursorBoy.getInt(2));
                inforPopularityModels.add(inforPopularityModel);
            }
            cursorBoy.close();
            inforPopularityAdapter=new InforPopularityAdapter(getActivity(),inforPopularityModels);
            recyInforGirl.setAdapter(inforPopularityAdapter);
            Log.d("GIRL",inforPopularityModels.size()+"");
        }
        //TODO: Recylceview Popularity BOY
        if (nameMainModel.getGender().equals("Unisex") || nameMainModel.getGender().equals("Boy")){
            llBoy.setVisibility(View.VISIBLE);
            recyInforBoy.setNestedScrollingEnabled(false);
            recyInforBoy.setHasFixedSize(true);
            inforPopularityModels=new ArrayList<>();
            LinearLayoutManager managerBoy=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
            recyInforBoy.setLayoutManager(managerBoy);

            Cursor cursorBoy = database.rawQuery("select ZYEAR,ZUSEDCOUNT,ZRANKING FROM ZBABYPOPULARITY WHERE ZPOPULARBABYNAME="+nameMainModel.getPopularname()+" AND ZGENDER=1 ORDER BY ZYEAR DESC", null);
            inforPopularityModels.clear();
            while (cursorBoy.moveToNext()) {
                InforPopularityModel inforPopularityModel = new InforPopularityModel(""+cursorBoy.getInt(0),cursorBoy.getInt(1)+"births","#"+cursorBoy.getInt(2));
                inforPopularityModels.add(inforPopularityModel);
            }
            cursorBoy.close();
            inforPopularityAdapter=new InforPopularityAdapter(getActivity(),inforPopularityModels);
            recyInforBoy.setAdapter(inforPopularityAdapter);
            Log.d("GIRL",inforPopularityModels.size()+"");
        }

    }


}



