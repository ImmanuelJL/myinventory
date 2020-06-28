package com.soft_gain.myinventory.Barang;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.soft_gain.myinventory.Api.Client;
import com.soft_gain.myinventory.Api.Interface;
import com.soft_gain.myinventory.Barang.BarangFragmentList;
import com.soft_gain.myinventory.MainActivity;
import com.soft_gain.myinventory.Model.Barang.Datum;
import com.soft_gain.myinventory.Model.GetReponseSuccess;
import com.soft_gain.myinventory.R;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.CustomViewHolder>{
    private List<Datum> dataList;
    private Context context;
    ProgressDialog progressDoalog;
    SharedPreferences myPrefs;

    public BarangAdapter(List<Datum> dataList){
        this.dataList = dataList;
    }

    public BarangAdapter(BarangFragmentList connectFragment, List<Datum> dataList) {}

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView txtTitle;
        EditText txtId;
        ImageView edit, delete;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtTitle = mView.findViewById(R.id.title);
            edit = (ImageView) mView.findViewById(R.id.edit);
            delete = (ImageView) mView.findViewById(R.id.delete);
            txtId = mView.findViewById(R.id.id);
            txtId.setVisibility(View.GONE);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter, parent, false);
        context = parent.getContext();

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.get(position).getNamaBarang());
        final String idData = String.valueOf(dataList.get(position).getId());
        final String titleData = String.valueOf(dataList.get(position).getNamaBarang());

        holder.edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                /*Log.v("log softgain : ", String.valueOf(idData));*/
                SharedPreferences sgSharedPref = context.getSharedPreferences("sg_shared_pref", context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sgSharedPref.edit();
                editor.putString("editedIdBarang", idData);
                editor.apply();

                BarangFragmentFormEdit nextFrag= new BarangFragmentFormEdit();
                ((MainActivity)context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try{
                    showDialog(String.valueOf(titleData), String.valueOf(idData));
                } catch (Exception e){
                    e.printStackTrace();
                }
                /*Log.v("log softgain : ", String.valueOf(idData));*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void showDialog(final String title, final String id) throws Exception {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("Hapus : " + title + "?");

        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();

                progressDoalog = new ProgressDialog(context);
                progressDoalog.setMessage("Loading....");
                progressDoalog.show();

                myPrefs = context.getSharedPreferences("sg_shared_pref", context.MODE_PRIVATE);
                String token = myPrefs.getString("token", "");

                Interface service = Client.getClient().create(Interface.class);
                Call<GetReponseSuccess> call = service.postBarangDelete("Bearer "+token, id);
                call.enqueue(new Callback<GetReponseSuccess>() {
                    @Override
                    public void onResponse(Call<GetReponseSuccess> call, Response<GetReponseSuccess> response) {
                        if(response.isSuccessful()){
                            progressDoalog.dismiss();
                            Toast.makeText(context, "Hapus data berhasil!", Toast.LENGTH_SHORT).show();

                            BarangFragmentList nextFrag= new BarangFragmentList();
                            ((MainActivity)context).getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_frame, nextFrag, "findThisFragment")
                                    .addToBackStack(null)
                                    .commit();
                        }else{
                            /*Log.v("log softgain : ", String.valueOf(response.errorBody()));*/
                            progressDoalog.dismiss();
                            Toast.makeText(context ,"Hapus data gagal!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetReponseSuccess> call, Throwable t) {
                        /*Log.v("log softgain : ", String.valueOf(t));*/
                        progressDoalog.dismiss();
                        Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
