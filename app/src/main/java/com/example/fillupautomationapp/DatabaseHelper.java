package com.example.fillupautomationapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String db_name = "brgyDB";
    public static final String tbl_name = "tbl_residents";
    public DatabaseHelper(Context context) {
        super(context,db_name , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + tbl_name + "(rin integer primary key autoincrement, lname text, fname text , mi text, house_num text, street txt, gender text" +
                ",age text, year_of_stay text, birthday text , birthplace text, contact_num)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tbl_name);
        onCreate(db);
    }

    public boolean InsertRecord (String Lname, String Fname, String Mi, String House_num, String Street, String Gender, String Age, String Year_of_stay,
                                 String Birthday, String Birthplace, String Contact_num){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("lname",Lname);
        cv.put("fname",Fname);
        cv.put("mi",Mi);
        cv.put("house_num.",House_num);
        cv.put("street",Street);
        cv.put("gender",Gender);
        cv.put("age",Age);
        cv.put("year_of_stay",Year_of_stay);
        cv.put("birthday",Birthday);
        cv.put("birthplace",Birthplace);
        cv.put("contact_num.",Contact_num);
        long result = db.insert(tbl_name,null,cv);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }
    public Cursor getData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res;

        if (id == "0") {
            res=db.rawQuery(" SELECT * FROM " + tbl_name, null);
        } else {
            res=db.rawQuery(" SELECT * FROM " + tbl_name + " WHERE rin = '" +id + "'", null);
        }
        return res;
    }

    public boolean deleteData (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tbl_name, "rin=?", new String[]{id});
        return true;
    }
    public boolean updateData(String id, String Lname, String Fname, String Mi, String House_num, String Street, String Gender, String Age, String Year_of_stay,
                              String Birthday, String Birthplace, String Contact_num){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("rin", id);
        cv.put("lname",Lname);
        cv.put("fname",Fname);
        cv.put("mi",Mi);
        cv.put("house_num.",House_num);
        cv.put("street",Street);
        cv.put("gender",Gender);
        cv.put("age",Age);
        cv.put("year_of_stay",Year_of_stay);
        cv.put("birthday",Birthday);
        cv.put("birthplace",Birthplace);
        cv.put("contact_num.",Contact_num);
        db.update(tbl_name,cv,"rin = ?", new String[]{id});
        return true;
    }
}
