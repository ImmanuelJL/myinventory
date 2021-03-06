package com.soft_gain.myinventory.Barang;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.soft_gain.myinventory.Api.Client;
import com.soft_gain.myinventory.Api.Interface;
import com.soft_gain.myinventory.Model.GetReponseSuccess;
import com.soft_gain.myinventory.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarangFragmentFormAdd extends Fragment {
    ProgressDialog progressDoalog;
    SharedPreferences myPrefs;
    EditText nama_barang,jumlah_barang,keterangan;

    public BarangFragmentFormAdd() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_barang_form, container, false);

        nama_barang = (EditText)rootView.findViewById(R.id.nama_barang);
        jumlah_barang = (EditText)rootView.findViewById(R.id.jumlah_barang);
        keterangan = (EditText)rootView.findViewById(R.id.keterangan);

        Button button = (Button) rootView.findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                progressDoalog = new ProgressDialog(getActivity());
                progressDoalog.setMessage("Loading....");
                progressDoalog.show();

                myPrefs = getActivity().getSharedPreferences("sg_shared_pref", getActivity().MODE_PRIVATE);
                String token = myPrefs.getString("token", "");

                Interface service = Client.getClient().create(Interface.class);
                Call<GetReponseSuccess> call = service.postBarangStore("Bearer "+token, nama_barang.getText().toString(), jumlah_barang.getText().toString(), keterangan.getText().toString());
                call.enqueue(new Callback<GetReponseSuccess>() {
                    @Override
                    public void onResponse(Call<GetReponseSuccess> call, Response<GetReponseSuccess> response) {
                        if(response.isSuccessful()){
                            progressDoalog.dismiss();
                            Toast.makeText(getActivity(), "Submit data berhasil!", Toast.LENGTH_SHORT).show();

                            BarangFragmentList nextFrag= new BarangFragmentList();
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_frame, nextFrag, "findThisFragment")
                                    .addToBackStack(null)
                                    .commit();
                        }else{
                            /*Log.v("log softgain : ", String.valueOf(response.errorBody()));*/
                            progressDoalog.dismiss();
                            Toast.makeText(getActivity() ,"Submit data gagal!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetReponseSuccess> call, Throwable t) {
                        /*Log.v("log softgain : ", String.valueOf(t));*/
                        progressDoalog.dismiss();
                        Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return rootView;
    }
}
