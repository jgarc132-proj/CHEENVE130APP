package com.example.chen_enen130app.DataFiles;

import java.util.ArrayList;

public class Question {

    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int answerNr;

    public Question() {}

    public Question(String question, String option1, String option2, String option3, String option4, int answerNr) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answerNr = answerNr;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }

    /*
    private int position;
    private int drawableID;
    private int question;
    private ArrayList<String> MCQuestion;
    private String answer;

    public Question(int position, int drawableID, int question, ArrayList<String> MCQuestion, String answer) {
        this.position = position;
        this.drawableID = drawableID;
        this.question = question;
        this.MCQuestion = MCQuestion;
        this.answer = answer;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getDrawableID() {
        return drawableID;
    }

    public void setDrawableID(int drawableID) {
        this.drawableID = drawableID;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
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
    }*/
}