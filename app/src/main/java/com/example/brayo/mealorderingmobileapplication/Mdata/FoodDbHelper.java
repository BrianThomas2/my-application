package com.example.brayo.mealorderingmobileapplication.Mdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



/**
 * Created by Brayo on 17/04/2018.
 */

public class FoodDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FOODTABLE.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY =
            "CREATE TABLE "+FoodContract.FoodInfo.TABLE_NAME+"("+ FoodContract.FoodInfo.FOOD_DESC+" TEXT,"+
                    FoodContract.FoodInfo.QUANTITY+" TEXT,"+ FoodContract.FoodInfo.TOTAL_PRICE+" TEXT);";

    public FoodDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS","Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS","Table created");

    }
    public void addFood(String desc,String quantity,String totalprice,SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(FoodContract.FoodInfo.FOOD_DESC,desc);
        contentValues.put(FoodContract.FoodInfo.QUANTITY,quantity);
        contentValues.put(FoodContract.FoodInfo.TOTAL_PRICE,totalprice);
        db.insert(FoodContract.FoodInfo.TABLE_NAME,null,contentValues);
        Log.e("DATABASE OPERATIONS","One row inserted");

    }

    public Cursor getFood(SQLiteDatabase db){
        Cursor cursor;
        String [] projections ={FoodContract.FoodInfo.FOOD_DESC, FoodContract.FoodInfo.QUANTITY, FoodContract.FoodInfo.TOTAL_PRICE};
        cursor = db.query(FoodContract.FoodInfo.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
