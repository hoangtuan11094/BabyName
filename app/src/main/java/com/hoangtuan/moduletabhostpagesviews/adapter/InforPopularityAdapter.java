package com.hoangtuan.moduletabhostpagesviews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hoangtuan.moduletabhostpagesviews.R;
import com.hoangtuan.moduletabhostpagesviews.model.InforPopularityModel;

import java.util.ArrayList;

/**
 * Created by atbic on 18/4/2018.
 */

public class InforPopularityAdapter extends RecyclerView.Adapter<InforPopularityAdapter.InforPopularHolder> {
    Context context;
    ArrayList<InforPopularityModel> inforPopularityModels;

    public InforPopularityAdapter(Context context, ArrayList<InforPopularityModel> inforPopularityModels) {
        this.context = context;
        this.inforPopularityModels = inforPopularityModels;
    }

    @Override
    public InforPopularHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_infor_popularity,parent,false);
        return new InforPopularHolder(view);
    }

    @Override
    public void onBindViewHolder(InforPopularHolder holder, int position) {
        InforPopularityModel inforPopularityModel=inforPopularityModels.get(position);
        holder.txtUser.setText(inforPopularityModel.getUser());
        holder.txtYear.setText(inforPopularityModel.getYear());
        holder.txtRank.setText(inforPopularityModel.getRank());
    }

    @Override
    public int getItemCount() {
        return inforPopularityModels.size();
    }

    public class InforPopularHolder extends RecyclerView.ViewHolder {
        TextView txtYear,txtUser,txtRank;
        public InforPopularHolder(View itemView) {
            super(itemView);
            txtRank=(TextView)itemView.findViewById(R.id.txtInforPGirlRank);
            txtYear=(TextView)itemView.findViewById(R.id.txtInforPGirlYear);
            txtUser=(TextView)itemView.findViewById(R.id.txtInforPGirlUser);
        }
    }
}
