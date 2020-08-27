package com.example.chen_enen130app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Exercise extends Fragment {

    private RadioGroup rBGroup;
    private RadioButton rB1, rB2, rB3, rB4;
    private ImageView questionImage;
    private TextView questionText, scoreCount, questionCount;
    private Button submitButton;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_exercise, container, false);

        initializeViews(fragmentView);

        return fragmentView;
    }

    private void initializeViews(View view) {
        rBGroup = (RadioGroup) view.findViewById(R.id.Exercise_RGChoice);

        rB1 = (RadioButton) view.findViewById(R.id.Exercise_RB_Choice1);
        rB2 = (RadioButton) view.findViewById(R.id.Exercise_RB_Choice2);
        rB3 = (RadioButton) view.findViewById(R.id.Exercise_RB_Choice3);
        rB4 = (RadioButton) view.findViewById(R.id.Exercise_RB_Choice4);

        questionImage = (ImageView) view.findViewById(R.id.Exercise_QuestionImage);

        questionText = (TextView) view.findViewById(R.id.Exercise_QuestionText);
        scoreCount = (TextView) view.findViewById(R.id.Exercise_ScoreCount);
        questionCount = (TextView) view.findViewById(R.id.Exercise_QuestionCount);

        submitButton = (Button) view.findViewById(R.id.Exercise_ButtonSubmit);
    }
}
