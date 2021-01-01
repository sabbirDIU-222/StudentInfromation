package com.example.studentinfromation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class ShowData extends AppCompatActivity implements CardAdapter.OnpositionItemClick {
    RecyclerView recyclerView;
    DatabaseSource source;
    ArrayList<ModelDataClass> arrayList;
    CardAdapter adapter;
    CardAdapter.OnpositionItemClick onpositionItemClick;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_data);

        recyclerView = findViewById(R.id.recylerviewId);

        source = new DatabaseSource(this);
        arrayList = source.fetch_all_data();

         adapter = new CardAdapter(this, arrayList,this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onpositionclick(int position) {
        ModelDataClass dataClass = arrayList.get(position);
        Intent intent = new Intent(ShowData.this,MainActivity.class);
        intent.putExtra("STUDENT",dataClass);
        startActivity(intent);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onContextItemSelected(item);
        switch (item.getItemId()){
            case 101:
                Toast.makeText(this,"Add to wish list", Toast.LENGTH_SHORT).show();
                return true;

            case 102:
                progressdialoug();
                Toast.makeText(this,"delete data", Toast.LENGTH_SHORT).show();

                adapter.removeItem(item.getGroupId());
                return true;
            default:
                Toast.makeText(this,"Add to wish list", Toast.LENGTH_SHORT).show();
                return true;

        }

    }

    public void progressdialoug(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading...");
        progressDialog.setTitle("ProgressDailouge Running");
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCanceledOnTouchOutside(false);// this is mandatory
        progressDialog.show();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (progressDialog.getProgress()<=progressDialog.getMax()){
                    try {
                        Thread.sleep(200);
                        progressDialog.incrementProgressBy(3);

                        /*            handler.sendMessage(handler.obtainMessage());*/

                        if(progressDialog.getProgress()==progressDialog.getMax()){
                            progressDialog.dismiss();

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        thread.start();
    }


}