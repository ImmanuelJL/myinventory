package com.soft_gain.myinventory.Barang;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.soft_gain.myinventory.Barang.BarangFragmentList;
import com.soft_gain.myinventory.Model.Barang.Datum;
import com.soft_gain.myinventory.R;

import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.CustomViewHolder>{
    private List<Datum> dataList;
    private Context context;

    public BarangAdapter(List<Datum> dataList){
        this.dataList = dataList;
    }

    public BarangAdapter(BarangFragmentList connectFragment, List<Datum> dataList) {}

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView txtTitle;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtTitle = mView.findViewById(R.id.title);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.get(position).getNamaBarang());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
