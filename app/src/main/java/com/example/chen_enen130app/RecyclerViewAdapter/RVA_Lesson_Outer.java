package com.example.chen_enen130app.RecyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chen_enen130app.DataFiles.Model_Chapter;
import com.example.chen_enen130app.DatabaseAccessibility.LessonsDbA;
import com.example.chen_enen130app.R;

import java.util.ArrayList;

public class RVA_Lesson_Outer extends RecyclerView.Adapter<RVA_Lesson_Outer.ViewHolder> {
    Context context;
    ArrayList<Model_Chapter> chapters;
    LessonsDbA lDbA;
    ArrayList<String> chapterNames;

    public RVA_Lesson_Outer(Context context, ArrayList<Model_Chapter> chapters) {
        this.context = context;
        this.chapters = chapters;
        chapterNames = new ArrayList<>();

        lDbA = LessonsDbA.getInstance(this.context);
        lDbA.open();
        lDbA.PopulateArrayString("Chapter_Names", "Chapter_Names", chapterNames);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_lesson_outer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setInnerRecyclerView(position);
        holder.setData(position);

        holder.chapterName.setText(chapterNames.get(position));

    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        RVA_Lesson_Inner rVALessonInner;

        ImageView chapterImage;
        TextView chapterName;
        TextView chapterDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.RVA_Inner);

            chapterImage = itemView.findViewById(R.id.ChapterImage);
            chapterName = itemView.findViewById(R.id.ChapterName);
            chapterDescription = itemView.findViewById(R.id.ChapterDescription);
        }

        private void setData(final int position) {
            chapterImage.setImageResource(chapters.get(position).getImage());
            chapterName.setText(chapters.get(position).getChapterName());
            chapterDescription.setText(chapters.get(position).getChapterName());
        }

        private void setInnerRecyclerView(final int position) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            rVALessonInner = new RVA_Lesson_Inner(context, chapters.get(position));
            recyclerView.setAdapter(rVALessonInner);
        }
    }
}
