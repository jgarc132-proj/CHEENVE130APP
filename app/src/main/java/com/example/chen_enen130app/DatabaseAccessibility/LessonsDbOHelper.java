package com.example.chen_enen130app.DatabaseAccessibility;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class LessonsDbOHelper extends SQLiteAssetHelper
{
    private static final String DATABASE_NAME = "Lessons.sqlite";
    private static final int DATABASE_VERSION = 1;

    public LessonsDbOHelper(Context context)
    {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }
}