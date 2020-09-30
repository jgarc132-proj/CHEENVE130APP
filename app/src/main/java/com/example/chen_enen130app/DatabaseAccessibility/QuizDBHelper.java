package com.example.chen_enen130app.DatabaseAccessibility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.chen_enen130app.DataFiles.Question;
import com.example.chen_enen130app.DataFiles.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuizQuestions.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        db = sqLiteDatabase;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" + " )";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);

        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("A is correct", "A", "B", "C", "D", 1);
        addQuestion(q1);
        Question q2 = new Question("B is correct", "A", "B", "C", "D", 2);
        addQuestion(q2);
        Question q3 = new Question("C is correct", "A", "B", "C", "D", 3);
        addQuestion(q3);
        Question q4 = new Question("A is correct", "A", "B", "C", "D", 1);
        addQuestion(q4);
        Question q5 = new Question("B is correct", "A", "B", "C", "D", 2);
        addQuestion(q5);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if(cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(cursor.getString((cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION1))));
                question.setOption2(cursor.getString((cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION2))));
                question.setOption3(cursor.getString((cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION3))));
                question.setOption4(cursor.getString((cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION4))));
                question.setAnswerNr(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));

                questionList.add(question);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionList;
    }
}
