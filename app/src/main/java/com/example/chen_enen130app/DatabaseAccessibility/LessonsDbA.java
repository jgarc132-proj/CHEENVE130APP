package com.example.chen_enen130app.DatabaseAccessibility;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class LessonsDbA {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static LessonsDbA instance;
    Cursor c;

    //private constructor so that object creation from outside the class is avoided
    private LessonsDbA(Context context)
    {
        this.openHelper = new ParametersDbOHelper(context);
    }

    //to return the single instance of database;
    public static LessonsDbA getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new LessonsDbA(context);
        }
        return instance;
    }

    //to open the database
    public void open()
    {
        this.db = openHelper.getReadableDatabase();
    }

    public void close()
    {
        if(db != null)
        {
            this.db.close();
        }
    }

    //Method to populate an ArrayList with Strings.
    public void PopulateArrayString(String TABLE_NAME, String COLUMN, ArrayList<String> Array)
    {
        String[] COLUMNS = {COLUMN};

        c = db.query(TABLE_NAME, COLUMNS, null, null, null, null, null);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
            Array.add(c.getString(c.getColumnIndex(COLUMNS[0])));
        }
    }

    //Method to populate an ArrayList with Doubles.
    public void PopulateArrayDouble(String TABLE_NAME, String COLUMN, ArrayList<Double> Array)
    {
        String[] COLUMNS = {COLUMN};

        c = db.query(TABLE_NAME, COLUMNS, null, null, null, null, null);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
            Array.add(c.getDouble(c.getColumnIndex(COLUMNS[0])));
        }
    }
}