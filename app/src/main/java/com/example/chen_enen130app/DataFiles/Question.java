package com.example.chen_enen130app.DataFiles;

import java.util.ArrayList;

public class Question {

    private int position;
    private int layoutID;
    private ArrayList<String> MCQuestion;
    private String answer;

    public Question(int newPosition, int newLayoutID, ArrayList<String> newMCQuestion, String newAnswer) {
        position = newPosition;
        layoutID = newLayoutID;
        MCQuestion = newMCQuestion;
        answer = newAnswer;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getLayoutID() {
        return layoutID;
    }

    public void setLayoutID(int layoutID) {
        this.layoutID = layoutID;
    }

    public ArrayList<String> getMCQuestion() {
        return MCQuestion;
    }

    public void setMCQuestion(ArrayList<String> MCQuestion) {
        this.MCQuestion = MCQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}