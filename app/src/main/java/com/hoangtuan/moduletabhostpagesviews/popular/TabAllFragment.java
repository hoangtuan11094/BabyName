package com.hoangtuan.moduletabhostpagesviews.popular;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hoangtuan.moduletabhostpagesviews.R;
import com.hoangtuan.moduletabhostpagesviews.adapter.MainAdapter;
import com.hoangtuan.moduletabhostpagesviews.fragments.InforNameFragment;
import com.hoangtuan.moduletabhostpagesviews.helper.RecyclerItemClickListener;
import com.hoangtuan.moduletabhostpagesviews.model.NameMainModel;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabAllFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabAllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabAllFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //==========================
    private SQLiteDatabase database = null;
    private String DATABASE_NAME = "BabyNames.sqlite";
    private RecyclerView recyAll;
    private MainAdapter mainAdapter;
    private ArrayList<NameMainModel> nameMainModels;

    public TabAllFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment TabAllFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TabAllFragment newInstance(String param1) {
        TabAllFragment fragment = new TabAllFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            Log.d("ALL", mParam1+"onCreate: ");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tab_all, container, false);
        initALL(view);
        return view;
    }

    private void initALL(View view) {
        recyAll = (RecyclerView) view.findViewById(R.id.recyAll);
        recyAll.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyAll.setLayoutManager(manager);
        recyAll.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        nameMainModels = new ArrayList<>();
        //TODO: Lấy dữ liệu từ SQLite
        database = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("select ZRANKING,ZBABYNAME,ZGENDERTYPE,ZORIGINS,ZPOPULARBABYNAME FROM ZBABYNAME INNER JOIN ZBABYPOPULARITY ON ZBABYNAME.Z_PK=ZBABYPOPULARITY.ZPOPULARBABYNAME WHERE ZBABYPOPULARITY.ZRANKING<=100 AND ZBABYPOPULARITY.ZYEAR="+mParam1+" ORDER BY ZRANKING ASC", null);
        nameMainModels.clear();
        while (cursor.moveToNext()) {
            NameMainModel nameMainModel = new NameMainModel("#" + cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
            nameMainModels.add(nameMainModel);
        }
        cursor.close();

        mainAdapter = new MainAdapter(getActivity(), nameMainModels);
        recyAll.setAdapter(mainAdapter);


        //TODO: Set Click Recycleview
        recyAll.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyAll, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Bundle bundle=new Bundle();
                bundle.putSerializable("name",nameMainModels.get(position));

                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                InforNameFragment inforNameFragment=new InforNameFragment();
                inforNameFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.fragMain,inforNameFragment,"A");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
