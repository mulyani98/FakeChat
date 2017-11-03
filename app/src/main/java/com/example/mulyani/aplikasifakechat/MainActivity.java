package com.example.mulyani.aplikasifakechat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    public static String preference = "file.main.message";
    RecyclerView rv;
    chatadapter chatadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView) findViewById(R.id.rvPesan);
        rv.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sp = getSharedPreferences(preference,0);
        String konten = sp.getString("pesan","NO_PESAN");

        try {
            JSONArray jsonArray = new JSONArray(konten);
            chatadapter = new chatadapter(jsonArray);

            rv.setAdapter(chatadapter);
            chatadapter.notifyDataSetChanged();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        Log.d("json",konten);
    }

    public void buatPesan(View view) {
        Intent intent = new Intent(this,makechat.class);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
