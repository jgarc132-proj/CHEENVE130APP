package com.example.chen_enen130app.Lesson_RVA;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chen_enen130app.R;

import java.util.ArrayList;
import java.util.List;

public class OuterRVAdapter extends RecyclerView.Adapter<OuterRVAdapter.ViewHolder> {

    Context context;
    List<ModelClass> list;

    public OuterRVAdapter(Context context, List<ModelClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.include_outer_rv_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView inner_rv;
        InnerAdapter innerAdapter;
        List<ModelClass> list;

        ImageView image_view;
        TextView username_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            inner_rv = itemView.findViewById(R.id.inner_rv);
            image_view = itemView.findViewById(R.id.image_view);
            username_tv = itemView.findViewById(R.id.username_tv);

            setInnerRecyclerView();
        }

        private List<ModelClass> getList() {
            list = new ArrayList<>();
            list.add(new ModelClass(R.mipmap.ic_launcher_round, "Lesson 1"));
            list.add(new ModelClass(R.mipmap.ic_launcher_round, "Lesson 2"));
            list.add(new ModelClass(R.mipmap.ic_launcher_round, "Lesson 3"));
            list.add(new ModelClass(R.mipmap.ic_launcher_round, "Lesson 4"));
            list.add(new ModelClass(R.mipmap.ic_launcher_round, "Lesson 5"));
            list.add(new ModelClass(R.mipmap.ic_launcher_round, "Lesson 6"));
            return list;
        }

        private void setData(final int position) {
            image_view.setImageResource(list.get(position).getImage());
            username_tv.setText(list.get(position).getText());

            image_view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Position" + list.get(position), Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void setInnerRecyclerView() {
            inner_rv.setHasFixedSize(true);
            inner_rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            innerAdapter = new InnerAdapter(context, getList());
            inner_rv.setAdapter(innerAdapter);
        }
    }
}
