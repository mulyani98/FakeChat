package com.example.mulyani.aplikasifakechat;

import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class makechat extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor spedit;
    EditText isiNama, isiPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makechat);

        getSupportActionBar().setTitle("Buat Pesan");

        isiNama = (EditText) findViewById(R.id.edtNama);
        isiPesan = (EditText) findViewById(R.id.edtPesan);

        sp = getSharedPreferences(MainActivity.preference,MODE_PRIVATE);
        spedit = sp.edit();

    }

    public void Kirim(View view) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("namaPengguna",isiNama.getText().toString());
            jsonObject.put("isiPesan",isiPesan.getText().toString());
            jsonObject.put("tanggal",new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()));
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        if (sp.contains("pesan")){
            String konten = sp.getString("pesan","NO_PESAN");

            try {
                JSONArray jsonArray = new JSONArray(konten);
                jsonArray.put(jsonObject);
                spedit.putString("pesan",jsonArray.toString());
                spedit.apply();
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }
        else {
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            spedit.putString("pesan",jsonArray.toString());
            spedit.apply();
        }
        finish();
    }
}
