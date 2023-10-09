package com.example.demo.UI.Fragment.Home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.UI.Functions.Calculator.CalculatorFunction;
import com.example.demo.UI.Functions.Game.GameFunction;
import com.example.demo.UI.Functions.Weather.WeatherFunction;

import java.util.ArrayList;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {
    private ArrayList mArrayList;
    private ArrayList<Integer> pictureList;
    private Context context;
    public HomeListAdapter(ArrayList<String> arrayList, ArrayList<Integer> picture, Context context){
        this.mArrayList = arrayList;
        this.pictureList = picture;
        this.context = context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.function_image);
            textView = itemView.findViewById(R.id.function_name);
        }
    }
    @NonNull
    @Override
    public HomeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeListAdapter.ViewHolder holder, int position) {
        holder.textView.setText(mArrayList.get(position).toString());
        holder.imageView.setImageResource(pictureList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                switch (mArrayList.get(position).toString()){
                    case "天氣狀況":
                        intent.setClass(context, WeatherFunction.class);
                        break;
                    case "計算機":
                        intent.setClass(context, CalculatorFunction.class);
                        break;
                    case "小遊戲":
                        intent.setClass(context, GameFunction.class);
                        break;
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }
}
