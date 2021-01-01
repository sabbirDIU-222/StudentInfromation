package com.example.studentinfromation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Display;

import java.util.ArrayList;


/*
so we work with data and we need database
this database source class is very helpful to make and take of data
we create some custom method that will help us to insert the data
first we make a UI of our application
we create database using the help of sqlite open helper class
then we make a  model class and now we create database source class that is very helpful
to insert the data
in this class we take a reference of sqlitehelper and sqlitedatabase
Remember our database in SQLITEHELPER class where we make Student.db
* */

public class DatabaseSource {
    SqliteHelper sqliteHelper;
    SQLiteDatabase sqLiteDatabase;  // sqlitedatabase is a build in class in java


    public DatabaseSource(Context context) {         // we make this constructor that contain
        // when we call this instance of this class the whole
        sqliteHelper = new SqliteHelper(context);

    }

    // this open method is a custom method
    // in sqliteopenhelper calss we have get writable database
    // this get writeabel database method help us to to write on our database
    // this method return a object of sqlitedatabase
    // so we need this sqlitedatabase class
    public void open(){
        sqLiteDatabase = sqliteHelper.getWritableDatabase(); // get writeable data return a sqlite database
    }
    // this close method is also a custom method
    // in sqlite database class we have a  method named close
    // every time when we insert data on database we must close the database
    // other wise it can rise an error
    public void close(){
        sqLiteDatabase.close();
    }

    // this add method is also a custom method
    // the return type is boolean
    // so what does the method do?-> first we look at the perameter of this class
    // this method take the model class object
    // remember once again we this database source class is directly  interact with main activity class
    // first we call open method that open the database by calling getwritable database
    // we need ContentValues class so what is ContentValue class ?
    // in our database we store the data , that called content and each content need a value
    // after that we use insert method of sqlite database class


    public boolean addData(ModelDataClass modelDataClass){
        this.open();
        ContentValues values = new ContentValues();
        values.put(SqliteHelper.COL_STU_ID,modelDataClass.getStudentid());
        values.put(SqliteHelper.COL_NAME,modelDataClass.getName());
        values.put(SqliteHelper.COL_COURSE_CODE,modelDataClass.getCourses());
        values.put(SqliteHelper.COL_SECTION,modelDataClass.getSection());
        values.put(SqliteHelper.COL_REG_STATUS,modelDataClass.getRegstatus());

        long inserted = sqLiteDatabase.insert(SqliteHelper.TABLE_NAME,null,values);
        this.close();

        if(inserted>0){
            return true;
        }else return false;

    }

    // update method
    public boolean updatedata(ModelDataClass modelDataClass){
        this.open();
        ContentValues values = new ContentValues();
        values.put(SqliteHelper.COL_STU_ID,modelDataClass.getStudentid());
        values.put(SqliteHelper.COL_NAME,modelDataClass.getName());
        values.put(SqliteHelper.COL_COURSE_CODE,modelDataClass.getCourses());
        values.put(SqliteHelper.COL_SECTION,modelDataClass.getSection());
        values.put(SqliteHelper.COL_REG_STATUS,modelDataClass.getRegstatus());

        int updatedrow  = sqLiteDatabase.update(SqliteHelper.TABLE_NAME,values,SqliteHelper.COL_ID+" =?"
                     ,new String[] {String.valueOf(modelDataClass.getId())});

        if(updatedrow>0){
            return true;
        }
        else return false;

    }


    // now this time we fetch the data from database
    // and store them all in a arraylist because we want to see it in a specific card view or list
    // we can use sqlite query like SELECT * from student_info
    // now create a custom method like adddata
    // this time we need cursor class
    //  This interface provides random read-write access to the result set returned

    public ArrayList<ModelDataClass> fetch_all_data(){
        this.open();
        ArrayList<ModelDataClass> arrayList = new ArrayList<>(); // local arraylist


        Cursor cursor = sqLiteDatabase.query(SqliteHelper.TABLE_NAME,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
               int stu_id =  cursor.getInt(cursor.getColumnIndex(SqliteHelper.COL_STU_ID));
               String name =  cursor.getString(cursor.getColumnIndex(SqliteHelper.COL_NAME));
               String course_code =  cursor.getString(cursor.getColumnIndex(SqliteHelper.COL_COURSE_CODE));
               String section =  cursor.getString(cursor.getColumnIndex(SqliteHelper.COL_SECTION));
               String status = cursor.getString(cursor.getColumnIndex(SqliteHelper.COL_REG_STATUS));
               int id = cursor.getInt(cursor.getColumnIndex(SqliteHelper.COL_ID));


               // now we create an instanace of ModelDataClass
                // this model data class will help us to take or get a row
               ModelDataClass data = new ModelDataClass(id,stu_id,name,course_code,section,status);
                // and now we can set the data in our arraylist
                arrayList.add(data);
                 }while (cursor.moveToNext());
        }
        this.close();
        cursor.close();
        return arrayList;
    }  /// we can do this code in short formet but i am new in sqlite databse so i write it in detail



}
