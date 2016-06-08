package com.example.akc.calenderdatabse.Databse;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by akc on 6/8/2016.
 */
public class DatabseHelper extends SQLiteOpenHelper {
 static final public String DbName="MessBill";
 static final public String TableName="ExpenseTable";
    static final private String Dates="Date";
    static final private String Expense="Expense";
    static final private Integer DbVersion=1;
    static final private String p="Create Table "+TableName+"( "+ Dates+" Varchar(20) Unique ,"+Expense+" Integer);";
Context context;
    public DatabseHelper(Context context) {
        super(context, DbName, null, DbVersion);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL(p);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
