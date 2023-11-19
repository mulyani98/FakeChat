package com.example.mulyani.aplikasifakechat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mulyani on 11/3/2017.
 */

public class chatadapter extends RecyclerView.Adapter<chatadapter.ViewHolder> {
    JSONArray jsonArray;

    public chatadapter(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listchat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);
            holder.nama.setText(jsonObject.getString("namaPengguna"));
            holder.isi.setText(jsonObject.getString("isiPesan"));
            holder.tanggal.setText(jsonObject.getString("tanggal"));
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nama, isi, tanggal;

        public ViewHolder(View itemView) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.namaChat);
            isi = (TextView) itemView.findViewById(R.id.isiPesan);
            tanggal = (TextView) itemView.findViewById(R.id.tanggal);
        }
    }
}
