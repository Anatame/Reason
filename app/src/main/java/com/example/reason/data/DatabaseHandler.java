package com.example.reason.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.reason.model.Item;
import com.example.reason.util.Constants;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private Context context;

    public DatabaseHandler(Context context) {
        super(context, Constants.DB_NAME,  null, Constants.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BABY_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY,"
                + Constants.KEY_ACTIVITY_NAME + " TEXT,"
                + Constants.KEY_TIME + " TEXT,"
                + Constants.KEY_INTERVAL + " INTEGER,"
                + Constants.KEY_ACTIVITY_SET_NAME + " TEXT,"
                + Constants.KEY_CHECKED_STATUS + " INTEGER);";

        db.execSQL(CREATE_BABY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME );

        onCreate(db);
    }

        public void addItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.KEY_ACTIVITY_NAME, item.getActivityName());
        values.put(Constants.KEY_TIME, item.getTime());
        values.put(Constants.KEY_INTERVAL, item.getInterval());
        values.put(Constants.KEY_ACTIVITY_SET_NAME, item.getActivitySetName());
        values.put(Constants.KEY_CHECKED_STATUS, item.getChecked());

        //Insert row
        db.insert(Constants.TABLE_NAME, null, values);
        Log.d("DBHandler", "added item");
    }

    public Item getItem(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Constants.TABLE_NAME,
                new String[]{Constants.KEY_ID,
                        Constants.KEY_ACTIVITY_NAME,
                        Constants.KEY_TIME,
                        Constants.KEY_INTERVAL,
                        Constants.KEY_ACTIVITY_SET_NAME,
                        Constants.KEY_CHECKED_STATUS},
                Constants.KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        Item item = new Item();
        assert cursor != null;

        item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
        item.setActivityName(cursor.getString(cursor.getColumnIndex(Constants.KEY_ACTIVITY_NAME)));
        item.setTime(cursor.getString(cursor.getColumnIndex(Constants.KEY_TIME)));
        item.setInterval(cursor.getInt(cursor.getColumnIndex(Constants.KEY_INTERVAL)));
        item.setActivitySetName(cursor.getString(cursor.getColumnIndex(Constants.KEY_ACTIVITY_SET_NAME)));
        item.setChecked(cursor.getInt(cursor.getColumnIndex(Constants.KEY_CHECKED_STATUS)));



        return item;
    }

    //GET ALL THE ITEMS
    public List<Item> getAllItems(){

        SQLiteDatabase db = this.getReadableDatabase();

        List<Item> itemsList = new ArrayList<>();

        Cursor cursor = db.query(Constants.TABLE_NAME,
                new String[]{Constants.KEY_ID,
                        Constants.KEY_ACTIVITY_NAME,
                        Constants.KEY_TIME,
                        Constants.KEY_INTERVAL,
                        Constants.KEY_ACTIVITY_SET_NAME,
                        Constants.KEY_CHECKED_STATUS},
                null, null, null, null,
                Constants.KEY_ID + " ASC");

        if(cursor.moveToFirst()){
            do {
                Item item = new Item();

                item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                item.setActivityName(cursor.getString(cursor.getColumnIndex(Constants.KEY_ACTIVITY_NAME)));
                item.setTime(cursor.getString(cursor.getColumnIndex(Constants.KEY_TIME)));
                item.setInterval(cursor.getInt(cursor.getColumnIndex(Constants.KEY_INTERVAL)));
                item.setActivitySetName(cursor.getString(cursor.getColumnIndex(Constants.KEY_ACTIVITY_SET_NAME)));
                item.setChecked(cursor.getInt(cursor.getColumnIndex(Constants.KEY_CHECKED_STATUS)));


                itemsList.add(item);

            } while (cursor.moveToNext());
        }

        return itemsList;
    }

    //Todo: Add updateItem
    public int updateItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_ACTIVITY_NAME, item.getActivityName());
        values.put(Constants.KEY_TIME, item.getTime());
        values.put(Constants.KEY_INTERVAL, item.getInterval());
        values.put(Constants.KEY_ACTIVITY_SET_NAME, item.getActivitySetName());
        values.put(Constants.KEY_CHECKED_STATUS, item.getChecked());

        //update row
        return db.update(Constants.TABLE_NAME, values,
                Constants.KEY_ID + "=?",
                new String[]{String.valueOf(item.getId())});

    }

    //Todo: Add Delete Item
    public void deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME,
                Constants.KEY_ID + "=?",
                new String[]{String.valueOf(id)});

        //close
        db.close();

    }

    //Todo: getItemCount
    public int getItemsCount() {
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();

    }

}
