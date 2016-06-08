package com.example.akc.calenderdatabse.Databse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by akc on 6/8/2016.
 */
public class DatabaseAdapter {
    DatabseHelper helper;

    public DatabaseAdapter(Context context) {
        helper = new DatabseHelper(context);
    }

    public long insertdata(String value, Integer exp) {
        SQLiteDatabase database = helper.getWritableDatabase();
        long id = 0;
        Cursor cursor = database.query(DatabseHelper.TableName, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String string = cursor.getString(0);
            if (string.equals(value)) {
                Integer integer = cursor.getInt(1)+exp;
                Log.d("XYZ",integer.toString());
//                String  selection ="DELETE from ExpenseTable WHERE Date='"+value+"';";
//                database.rawQuery(selection,null);
                ContentValues contentValues = new ContentValues();
                contentValues.put("Date", value);
                contentValues.put("Expense", integer);
//                id = database.insert(DatabseHelper.TableName, null, contentValues);
                String str = "Date=' " + value+" '";
                database.rawQuery("UPDATE ExpenseTable SET Expense="+integer+" where Date='"+value+"' ;",null);
//          Integer xyz = database.update(DatabseHelper.TableName, contentValues, str, null);
                return id;
            }

        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("Date", value);
        contentValues.put("Expense", exp);
        id = database.insert(DatabseHelper.TableName, null, contentValues);
        return id;


    }

    public int fetchdata(String value)
    {
        SQLiteDatabase database = helper.getWritableDatabase();
        int id=0;
        Cursor cursor = database.query(DatabseHelper.TableName, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String string = cursor.getString(0);
            if (string.equals(value))
            {
                id=cursor.getInt(1);
                String string1=Integer.toString(id);
                Log.d("XYz",string1);
                return id;
            }
        }

        return  id;
    }
}
