package com.example.lost_found;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.jar.Attributes;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList Typeid,Nameid;
    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.entries,parent,false);
        return new MyViewHolder(view);
    }

    public MyAdapter(Context context, ArrayList typeid, ArrayList nameid) {
        this.context = context;
        Nameid = nameid;
        Typeid = typeid;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Nameid.setText(String.valueOf(Nameid.get(position)));
        holder.Typeid.setText(String.valueOf(Typeid.get(position)));
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return Nameid.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Typeid,Nameid;
        Button mybutton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mybutton=itemView.findViewById(R.id.ViewButton);
            Typeid=itemView.findViewById(R.id.posttypeText);
            Nameid=itemView.findViewById(R.id.postnameText);

            mybutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }


}
