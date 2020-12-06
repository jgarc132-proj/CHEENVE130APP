package com.example.chen_enen130app.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chen_enen130app.Activity_CourseMaterial;
import com.example.chen_enen130app.R;

public class RVA_CourseOutline extends RecyclerView.Adapter<RVA_CourseOutline.MyViewHolder> {

    String title[], description[];
    int flavorImages[], courseMaterial[];
    Context context;

    public RVA_CourseOutline(Context context, String title[], String description[], int flavorImages[], int courseMaterial[]) {
        this.context = context;
        this.title = title;
        this.description = description;
        this.flavorImages = flavorImages;
        this.courseMaterial = courseMaterial;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.myText1.setText(title[position]);
        holder.myText2.setText(description[position]);
        holder.myImage.setImageResource(flavorImages[position]);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Activity_CourseMaterial.class);
                intent.putExtra("courseMaterial", courseMaterial[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView myText1, myText2;
        ImageView myImage;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.myText1);
            myText2 = itemView.findViewById(R.id.myText2);
            myImage = itemView.findViewById(R.id.myImageView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
