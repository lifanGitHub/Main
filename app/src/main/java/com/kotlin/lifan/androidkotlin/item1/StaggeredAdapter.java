package com.kotlin.lifan.androidkotlin.item1;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kotlin.lifan.androidkotlin.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by LiFan on 2016/11/8.
 */
public class StaggeredAdapter extends RecyclerView.Adapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> data;

    private List<Integer> heights;

    public StaggeredAdapter(Context context, List<String> data){
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
        heights = new ArrayList<>();
        Random random = new Random();
        for (int i=0; i<data.size(); i++){
            heights.add(100+random.nextInt(300));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.simple_textview_t,parent,false);
        MyViewHolder MyViewHolder2 = new MyViewHolder(view);

        return MyViewHolder2;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        final MyViewHolder holder = (MyViewHolder) viewHolder;
        ViewGroup.LayoutParams lp = holder.tv.getLayoutParams();
        lp.height = heights.get(position);
        holder.tv.setLayoutParams(lp);
        holder.tv.setText(data.get(position));
        holder.tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                data.remove(adapterPosition);
                notifyItemRemoved(adapterPosition);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
