package com.example.chen_enen130app.RecyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chen_enen130app.DataFiles.Model_Chapter;
import com.example.chen_enen130app.R;

public class RVA_Lesson_Inner extends RecyclerView.Adapter<RVA_Lesson_Inner.ViewHolder> {
    Context context;
    Model_Chapter chapter;

    public RVA_Lesson_Inner(Context context, Model_Chapter chapter) {
        this.context = context;
        this.chapter = chapter;
    }

    @NonNull
    @Override
    public RVA_Lesson_Inner.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_lesson_inner, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVA_Lesson_Inner.ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() { return chapter.getLessonArrayList().size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView lessonImage;
        TextView lessonName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lessonImage = itemView.findViewById(R.id.LessonImage);
            lessonName = itemView.findViewById(R.id.LessonName);
        }

        private void setData(final int position) {
            lessonImage.setImageResource(chapter.getLessonArrayList().get(position).getImage());
            lessonName.setText(chapter.getLessonArrayList().get(position).getLessonName());
        }
    }
}
