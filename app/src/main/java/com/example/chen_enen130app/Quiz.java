package com.example.chen_enen130app;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chen_enen130app.DataFiles.Question;
import com.example.chen_enen130app.DatabaseAccessibility.QuizDBHelper;

import java.util.Collections;
import java.util.List;

public class Quiz extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";

    private TextView questionTextView;
    private TextView scoreTextView;
    private TextView questionCountTextView;
    private RadioGroup mcRadioGroup;
    private RadioButton mcOption1, mcOption2, mcOption3, mcOption4;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;

    private List<Question> questionList;

    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    private long backPressedTime;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        setupViews();

        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!answered) {
                    if(mcOption1.isChecked() || mcOption2.isChecked() || mcOption3.isChecked() || mcOption4.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(Quiz.this, "Please select an answer...", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void setupViews() {
        questionTextView = findViewById(R.id.Exercise_QuestionText);
        scoreTextView = findViewById(R.id.Exercise_ScoreCount);
        questionCountTextView = findViewById(R.id.Exercise_QuestionCount);

        mcRadioGroup = findViewById(R.id.Exercise_RGChoice);
        mcOption1 = findViewById(R.id.Exercise_RB_Choice1);
        mcOption2 = findViewById(R.id.Exercise_RB_Choice2);
        mcOption3 = findViewById(R.id.Exercise_RB_Choice3);
        mcOption4 = findViewById(R.id.Exercise_RB_Choice4);

        buttonConfirmNext = findViewById(R.id.Exercise_ButtonSubmit);

        textColorDefaultRb = mcOption1.getTextColors();

        QuizDBHelper dbHelper = new QuizDBHelper(this);
        questionList = dbHelper.getAllQuestions();
    }

    private void showNextQuestion() {
        mcOption1.setTextColor(textColorDefaultRb);
        mcOption2.setTextColor(textColorDefaultRb);
        mcOption3.setTextColor(textColorDefaultRb);
        mcOption4.setTextColor(textColorDefaultRb);

        mcRadioGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            questionTextView.setText(currentQuestion.getQuestion());
            mcOption1.setText(currentQuestion.getOption1());
            mcOption2.setText(currentQuestion.getOption2());
            mcOption3.setText(currentQuestion.getOption3());
            mcOption4.setText(currentQuestion.getOption4());

            questionCounter++;

            questionCountTextView.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");
        } else {
            finishQuiz();
        }
    }

    private void checkAnswer() {
        answered = true;

        RadioButton rbSelected = findViewById(mcRadioGroup.getCheckedRadioButtonId());
        int answerNr = mcRadioGroup.indexOfChild(rbSelected) + 1;

        if(answerNr == currentQuestion.getAnswerNr()) {
            score++;
            scoreTextView.setText(String.valueOf(score));
        }

        showSolution();
    }

    private void showSolution() {
        mcOption1.setTextColor(Color.RED);
        mcOption2.setTextColor(Color.RED);
        mcOption3.setTextColor(Color.RED);
        mcOption4.setTextColor(Color.RED);

        switch(currentQuestion.getAnswerNr()) {
            case 1:
                mcOption1.setTextColor(Color.GREEN);
                questionTextView.setText("Answer 1 is correct");
                break;
            case 2:
                mcOption2.setTextColor(Color.GREEN);
                questionTextView.setText("Answer 2 is correct");
                break;
            case 3:
                mcOption3.setTextColor(Color.GREEN);
                questionTextView.setText("Answer 3 is correct");
                break;
            case 4:
                mcOption4.setTextColor(Color.GREEN);
                questionTextView.setText("Answer 4 is correct");
                break;
        }

        if(questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Next");
        } else {
            buttonConfirmNext.setText("Finish");
        }
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        }
        backPressedTime = System.currentTimeMillis();
    }
}

/*
public class Fragment_Exercise extends Fragment {

    private RadioGroup rBGroup;
    private RadioButton rB1, rB2, rB3, rB4;
    private ImageView questionImage;
    private TextView questionText, scoreCount, questionCount;
    private Button submitButton;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.activity_quiz, container, false);

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
*/
