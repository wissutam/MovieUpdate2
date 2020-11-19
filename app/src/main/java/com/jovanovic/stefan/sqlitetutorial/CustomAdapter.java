package com.jovanovic.stefan.sqlitetutorial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.style.UpdateLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {


    private Context context;
    private Activity activity;
    private ArrayList movie_id, movie_name, movie_type, movie_runtime, movie_plot;
    int N=0;

    CustomAdapter(Activity activity, Context context, ArrayList movie_id, ArrayList movie_name, ArrayList movie_type,
                  ArrayList movie_runtime, ArrayList movie_plot,int N){
        this.activity = activity;
        this.context = context;
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_type = movie_type;
        this.movie_runtime = movie_runtime;
        this.movie_plot = movie_plot;
        this.N=N;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.movie_id_txt.setText(String.valueOf(movie_id.get(position)));
        holder.movie_name_txt.setText(String.valueOf(movie_name.get(position)));
        holder.movie_type_txt.setText(String.valueOf(movie_type.get(position)));
        holder.movie_runtime_txt.setText(String.valueOf(movie_runtime.get(position)));
        holder.movie_plot_txt.setText(String.valueOf(movie_plot.get(position)));
        if(String.valueOf(movie_type.get(position)).equals("adventure")){
        holder.MV.setImageResource(R.drawable.adventure);
        }
        if(String.valueOf(movie_type.get(position)).equals("comedy")){
            holder.MV.setImageResource(R.drawable.comedy);
        }
        if(String.valueOf(movie_type.get(position)).equals("drama")){
            holder.MV.setImageResource(R.drawable.drama);
        }
        if(String.valueOf(movie_type.get(position)).equals("erotica")){
            holder.MV.setImageResource(R.drawable.erotica);
        }
        if(String.valueOf(movie_type.get(position)).equals("fantasy")){
            holder.MV.setImageResource(R.drawable.fantasy);
        }
        if(String.valueOf(movie_type.get(position)).equals("horror")){
            holder.MV.setImageResource(R.drawable.horror);
        }
        if(String.valueOf(movie_type.get(position)).equals("sci_fi")){
            holder.MV.setImageResource(R.drawable.sci_fi);
        }
        if(String.valueOf(movie_type.get(position)).equals("western")){
            holder.MV.setImageResource(R.drawable.western);
        }

        //Recyclerview onClickListener
            if(N==2) {
                holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(context, ReadOnly.class);
                        intent.putExtra("id", String.valueOf(movie_id.get(position)));
                        intent.putExtra("name", String.valueOf(movie_name.get(position)));
                        intent.putExtra("type", String.valueOf(movie_type.get(position)));
                        intent.putExtra("runtime", String.valueOf(movie_runtime.get(position)));
                        intent.putExtra("plot", String.valueOf(movie_plot.get(position)));

                        activity.startActivityForResult(intent, 2);
                    }
                });
            }

            if(N==99) {
                holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, UpdateActivity.class);
                        intent.putExtra("id", String.valueOf(movie_id.get(position)));
                        intent.putExtra("name", String.valueOf(movie_name.get(position)));
                        intent.putExtra("type", String.valueOf(movie_type.get(position)));
                        intent.putExtra("runtime", String.valueOf(movie_runtime.get(position)));
                        intent.putExtra("plot", String.valueOf(movie_plot.get(position)));
                        intent.putExtra("N", N);
                        activity.startActivityForResult(intent, 99);
                    }
                });
            }


    }

    @Override
    public int getItemCount() {
        return movie_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView movie_id_txt, movie_name_txt, movie_type_txt, movie_runtime_txt, movie_plot_txt;
        ImageView MV;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_id_txt = itemView.findViewById(R.id.movie_id_txt);
            movie_name_txt = itemView.findViewById(R.id.movie_name_txt);
            movie_type_txt = itemView.findViewById(R.id.movie_type_txt);
            movie_runtime_txt = itemView.findViewById(R.id.movie_runtime_txt);
            movie_plot_txt = itemView.findViewById(R.id.movie_plot_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            MV = itemView.findViewById(R.id.imageView_T);
            //Animate Recyclerview
           /* Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);*/
        }

    }

}
