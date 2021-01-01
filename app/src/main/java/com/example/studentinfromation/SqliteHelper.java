package com.example.studentinfromation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SqliteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_info";
    public static final int VERSION_NUMBER = 1;
    public Context context;

    public static final String COL_ID = "_id"; // for auto increment 1,2,3,4,5,6.....
    public static final String COL_STU_ID = "studnet_id"; // student id 191151037 191151038....
    public static final String COL_NAME = "name"; // only nickname sabbir , rohim , korim
    public static final String COL_SECTION = "section"; // section text are auto complete text UC A PC A ...
    public static final String COL_COURSE_CODE = "course_code"; // course code also auto complete but , only 1 attempt , cse121,cse123,cse124...
    public static final String COL_REG_STATUS = "Status"; //

    public static final String Create_Table = "CREATE TABLE "+TABLE_NAME+"("+ COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_STU_ID +" INTEGER NOT NULL,"+ COL_NAME +" TEXT , "+ COL_COURSE_CODE +" TEXT, " +
            " "+ COL_SECTION +" TEXT, "+ COL_REG_STATUS +" TEXT );";



    public SqliteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(Create_Table);
        }catch (Exception e){
            Toast.makeText(context,"Exception "+e,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        }catch (Exception e){
            Toast.makeText(context,"Exception "+e,Toast.LENGTH_SHORT).show();

        }

    }
}
