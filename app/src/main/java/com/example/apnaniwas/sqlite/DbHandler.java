/*
package com.example.apnaniwas.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {
  private static final int DB_VERSION = 1;
   private static final String DB_NAME = "usersdb";
   private static final String TABLE_Users = "userdetails";
   private static final String KEY_ID = "Usrid";
   private static final String KEY_NAME = "Usrname";
   private static final String KEY_EMAIL = "Usremail";
   private static final String KEY_PSWD = "Usrpswd";
   private static final String KEY_MOB = "Usrmob";

   public DbHandler(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
       String CREATE_TABLE = "CREATE  TABLE " +  TABLE_Users +
               "("
               + KEY_ID + " INTEGER PRIMARY KEY,"
               + KEY_NAME +" TEXT,"
               + KEY_PSWD + " TEXT,"
               + KEY_EMAIL + " TEXT,"
               + KEY_MOB + "  TEXT" + ")";
       db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        onCreate(db);
    }
    void insertUserDetails(UserDetailsModel userDetailsModel ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
            cValues.put(KEY_NAME, userDetailsModel.getUsrname());
            cValues.put(KEY_PSWD, userDetailsModel.getUsrpswd());
            cValues.put(KEY_EMAIL, userDetailsModel.getUsremail());
            cValues.put(KEY_MOB, userDetailsModel.getUsrmob());
        db.insert(TABLE_Users,null, cValues);
        db.close();
    }

    public List<UserDetailsModel> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<UserDetailsModel> userList = new ArrayList<UserDetailsModel>();
        String query = "SELECT * FROM "+ TABLE_Users;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do{
                UserDetailsModel userDetailsModel = new UserDetailsModel();
                userDetailsModel.setId(Integer.parseInt(cursor.getString(0)));
                userDetailsModel.setUsrname(cursor.getString(1));
                userDetailsModel.setUsrpswd(cursor.getString(2));
                userDetailsModel.setUsremail(cursor.getString(2));
                userDetailsModel.setUsrmob(cursor.getString(4));
                userList.add(userDetailsModel);
            }while (cursor.moveToNext());
        }
        return  userList;
    }

    public  int updateUsersDetails(UserDetailsModel userDetailsModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, userDetailsModel.getUsrname());
        cValues.put(KEY_PSWD, userDetailsModel.getUsrpswd());
        cValues.put(KEY_EMAIL, userDetailsModel.getUsremail());
        cValues.put(KEY_MOB, userDetailsModel.getUsrmob());
        return db.update(TABLE_Users, cValues, KEY_ID + " =?", new String[]
                {String.valueOf(userDetailsModel.getId())});
    }

    public void deleteUserDetails(UserDetailsModel userDetailsModel){
       SQLiteDatabase db = this.getWritableDatabase();
       db.delete(TABLE_Users,KEY_ID +  " =?",
               new String[] {String.valueOf(userDetailsModel.getId())});

       db.close();
    }

    private void reloadUserDetails() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<UserDetailsModel> userList = new ArrayList<UserDetailsModel>();
        String query = "SELECT * FROM "+ TABLE_Users;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do{
                UserDetailsModel userDetailsModel = new UserDetailsModel();
                userDetailsModel.setId(Integer.parseInt(cursor.getString(0)));
                userDetailsModel.setUsrname(cursor.getString(1));
                userDetailsModel.setUsrpswd(cursor.getString(2));
                userDetailsModel.setUsremail(cursor.getString(2));
                userDetailsModel.setUsrmob(cursor.getString(4));
                userList.add(userDetailsModel);
            }while (cursor.moveToNext());
        }
        SQLiteDB sqLiteDB = new SQLiteDB();
        sqLiteDB.loadListViewLayout();
    }
}
*/
