package com.soft_gain.myinventory.Barang;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.soft_gain.myinventory.Api.Client;
import com.soft_gain.myinventory.Api.Interface;
import com.soft_gain.myinventory.MainActivity;
import com.soft_gain.myinventory.Model.Barang.Barang;
import com.soft_gain.myinventory.Model.Barang.Datum;
import com.soft_gain.myinventory.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarangActivity extends AppCompatActivity {
    private BarangAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang_list);

        progressDoalog = new ProgressDialog(BarangActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        Interface service = Client.getClient().create(Interface.class);
        Call<List<Datum>> call = service.postBarang("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjVlZjM1N2JmYjZiNGJlOWNkZDIzZjE2NWQ0N2QwYjFhNDk4ZGRjZGE1Yzg3ODYxNDMzYmM3YzBhMzY4MTY3Yzc5MzA5NjhlNjM5MmE1YjNiIn0.eyJhdWQiOiIxIiwianRpIjoiNWVmMzU3YmZiNmI0YmU5Y2RkMjNmMTY1ZDQ3ZDBiMWE0OThkZGNkYTVjODc4NjE0MzNiYzdjMGEzNjgxNjdjNzkzMDk2OGU2MzkyYTViM2IiLCJpYXQiOjE1OTE5NDMxNTgsIm5iZiI6MTU5MTk0MzE1OCwiZXhwIjoxNjIzNDc5MTU4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.OCD3C08UMdAIcJhnHy6OyNg6FdzN24jY9tfTqDryOD6rNbwzX9J_TXLiOswhWEIoFqrbwtNDxDDh01HmPQoWrXI4GwwUFWXajkDgf8FwkIhfgh9EXw2soOOuJDQdd8T8hS0Hus_5sfN5jWnFK6i3FvIw29cPD7knsg8GAsVqqoc3q9jBGplzZYBA95SB58EGNjmOIH03j4ZchAOBhWwTSHFp3mGUkNaDo80bUJJwtpJ2dWgIsVp9B-YjhgbvcfdLHEAU6TQVbz857Buspy8EtX1dHEfpfxghosO7XjozSeYqMarQ-15Yla7fL8oYtUa9YOFyh_jYXBGJp4PFd7vVrvqCbIzejSPPnKrq0OdEUzqZAU9oP641B1hntIdpzTGpl1X_9b6wcvPLHGO1K2eLToYBLDoR4I7asS2FbYDjZ835TZMFMJMQT_fRYSP2NzJsDxJ05iWAn7REHwKWGxHsRV_hV5N46BY-8zPzY3fDIXTZv2fTG81Ty8h2F8SzhLxHXxoQt59gj7S6iMip7J7wjGeFUJaVEdkESJVnS_k4W9H1v3Kd2Gs3wqjYrz6F5kNRryiBkuOUTDEMCoer2JnOdJx8KbISottNPkUrIBD4M6kjTxenLXK7h_Db2bhC6gnecy8Z4s0y61TLT405Q7V4Jv51TYvwTC8zTA7MkGj75gw");
        call.enqueue(new Callback<List<Datum>>() {
            @Override
            public void onResponse(Call<List<Datum>> call, Response<List<Datum>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Datum>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(BarangActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Datum> dataList) {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new BarangAdapter(this,dataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BarangActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
