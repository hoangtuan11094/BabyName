package com.hoangtuan.moduletabhostpagesviews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hoangtuan.moduletabhostpagesviews.R;
import com.hoangtuan.moduletabhostpagesviews.model.InforMeanModel;

import java.util.ArrayList;

/**
 * Created by atbic on 18/4/2018.
 */

public class InforMeanAdapter extends RecyclerView.Adapter<InforMeanAdapter.InforMeanHolder> {
    Context context;
    ArrayList<InforMeanModel> inforMeanModels;

    public InforMeanAdapter(Context context, ArrayList<InforMeanModel> inforMeanModels) {
        this.context = context;
        this.inforMeanModels = inforMeanModels;
    }

    @Override
    public InforMeanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_infor_meaning,parent,false);

        return new InforMeanHolder(view);
    }

    @Override
    public void onBindViewHolder(InforMeanHolder holder, int position) {
        InforMeanModel inforMeanModel=inforMeanModels.get(position);
        holder.txtInforMean.setText(inforMeanModel.getMean());
        holder.txtInforOrigin.setText(inforMeanModel.getOrigin());
    }

    @Override
    public int getItemCount() {
        return inforMeanModels.size();
    }

    public class InforMeanHolder extends RecyclerView.ViewHolder {
        TextView txtInforMean,txtInforOrigin;
        public InforMeanHolder(View itemView) {
            super(itemView);
            txtInforMean=(TextView)itemView.findViewById(R.id.txtInforMeaning);
            txtInforOrigin=(TextView)itemView.findViewById(R.id.txtInforOrigin);

        }
    }
}
