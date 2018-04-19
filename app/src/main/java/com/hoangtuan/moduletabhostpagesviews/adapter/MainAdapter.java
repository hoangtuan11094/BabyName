package com.hoangtuan.moduletabhostpagesviews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hoangtuan.moduletabhostpagesviews.R;
import com.hoangtuan.moduletabhostpagesviews.model.NameMainModel;

import java.util.ArrayList;

/**
 * Created by atbic on 18/4/2018.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {
    Context context;
    ArrayList<NameMainModel> nameMainModels;

    public MainAdapter(Context context, ArrayList<NameMainModel> nameMainModels) {
        this.context = context;
        this.nameMainModels = nameMainModels;
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recy_main,parent,false);

        return new MainHolder(view);
    }

    @Override
    public void onBindViewHolder(MainHolder holder, int position) {
        NameMainModel nameMainModel=nameMainModels.get(position);
        holder.txtRank.setText(nameMainModel.getRank());

        holder.txtGender.setText(nameMainModel.getGender());
        holder.txtOrigin.setText(nameMainModel.getOrigin());

        if (nameMainModel.getGender().equals("Unisex")){
            holder.txtName.setText(nameMainModel.getName());
            holder.txtName.setTextColor(context.getResources().getColor(R.color.unisex));
        }else if (nameMainModel.getGender().equals("Boy")){
            holder.txtName.setText(nameMainModel.getName());
            holder.txtName.setTextColor(context.getResources().getColor(R.color.boy));
        } else if (nameMainModel.getGender().equals("Girl")){
            holder.txtName.setText(nameMainModel.getName());
            holder.txtName.setTextColor(context.getResources().getColor(R.color.girl));
        }
    }

    @Override
    public int getItemCount() {
        return nameMainModels.size();
    }

    public class MainHolder extends RecyclerView.ViewHolder {
        TextView txtRank,txtName,txtGender,txtOrigin;
        public MainHolder(View itemView) {
            super(itemView);
            txtGender=(TextView)itemView.findViewById(R.id.txtGender);
            txtName=(TextView)itemView.findViewById(R.id.txtName);
            txtRank=(TextView)itemView.findViewById(R.id.txtRank);
            txtOrigin=(TextView)itemView.findViewById(R.id.txtOrigin);
        }
    }
}
