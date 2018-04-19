package com.hoangtuan.moduletabhostpagesviews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoangtuan.moduletabhostpagesviews.R;
import com.hoangtuan.moduletabhostpagesviews.model.NaviModel;

import java.util.ArrayList;

/**
 * Created by atbic on 18/4/2018.
 */
public class NaviAdapter extends RecyclerView.Adapter<NaviAdapter.NaviHolder>{
    Context context;
    ArrayList<NaviModel> naviModels;

    public NaviAdapter(Context context, ArrayList<NaviModel> naviModels) {
        this.context = context;
        this.naviModels = naviModels;
    }

    @Override
    public NaviHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_navi,parent,false);
        return new NaviHolder(view);
    }

    @Override
    public void onBindViewHolder(NaviHolder holder, int position) {
        NaviModel naviModel=naviModels.get(position);
        holder.imgNavi.setImageResource(naviModel.getImg());
        holder.txtNavi.setText(naviModel.getName());
    }

    @Override
    public int getItemCount() {
        return naviModels.size();
    }

    public class NaviHolder extends RecyclerView.ViewHolder {
        TextView txtNavi;
        ImageView imgNavi;
        public NaviHolder(View itemView) {
            super(itemView);
            txtNavi=(TextView)itemView.findViewById(R.id.txtNavi);
            imgNavi=(ImageView)itemView.findViewById(R.id.imgNavi);
        }
    }
}
