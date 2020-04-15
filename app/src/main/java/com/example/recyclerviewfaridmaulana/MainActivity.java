package com.example.recyclerviewfaridmaulana;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewfaridmaulana.Mahasiswa;
import com.example.recyclerviewfaridmaulana.MahasiswaAdapter;
import com.example.recyclerviewfaridmaulana.R;
import com.example.recyclerviewfaridmaulana.RecyclerTouchListener;
import com.example.recyclerviewfaridmaulana.mahasiswa_detail;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Mahasiswa> mahasiswaList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MahasiswaAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        prepareMahasiswaData();
        mAdapter = new  MahasiswaAdapter(mahasiswaList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick (View view,int position){
                Mahasiswa mhs = mahasiswaList.get(position);
                Toast.makeText(getApplicationContext(), mhs.getNama() + "is selected!", Toast.LENGTH_SHORT).show();
                Intent detail = new Intent(MainActivity.this, mahasiswa_detail.class);
                detail.putExtra("nim", mahasiswaList.get(position).getNim());
                detail.putExtra("nama", mahasiswaList.get(position).getNama());
                detail.putExtra("nohp", mahasiswaList.get(position).getNohp());
                startActivity(detail);
            }

            @Override
            public void onLongClick (View view,int position){
            }
        }));
    }


    private void prepareMahasiswaData() {
        mahasiswaList = new ArrayList<>();
        mahasiswaList.add(new Mahasiswa("Farid Maulana", "311710019",  "082326337077"));
        mahasiswaList.add(new Mahasiswa("Exnasius Dwi Meda", "311710102",  "082326445578"));
        mahasiswaList.add(new Mahasiswa("Muhammad Mahmudi", "311710119",  "082327880909"));

    }

}

