package com.example.chen_enen130app.Lesson_RVA;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chen_enen130app.R;

import java.util.List;

public class InnerAdapter extends RecyclerView.Adapter<InnerAdapter.InnerViewHolder> {

    Context context;
    List<ModelClass> list;

    public InnerAdapter(Context context, List<ModelClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.include_inner_rv_layout, parent, false);
        return new InnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        ImageView image_view;
        TextView photo_name_tv;

        CardView card_view;
        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);

            image_view = itemView.findViewById(R.id.image_view);
            photo_name_tv = itemView.findViewById(R.id.photo_name_tv);
            card_view = itemView.findViewById(R.id.card_view);
        }

        private void setData(final int position) {
            image_view.setImageResource(list.get(position).getImage());
            photo_name_tv.setText(list.get(position).getText());

            card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Position: " + list.get(position), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
