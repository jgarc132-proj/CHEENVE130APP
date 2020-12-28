package com.example.chen_enen130app.DatabaseAccessibility;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class ParametersDbOHelper extends SQLiteAssetHelper
{
    private static final String DATABASE_NAME = "Parameters.sqlite";
    private static final int DATABASE_VERSION = 1;

    public ParametersDbOHelper(Context context)
    {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }
}