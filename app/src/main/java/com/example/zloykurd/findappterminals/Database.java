package com.example.zloykurd.findappterminals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZloykurD on 16.06.2016.
 */
public class Database extends SQLiteOpenHelper {
    String LOG_TAG = "zloykurd_DB";

    private static final String DATABASE_NAME = "mydbfindapp";
    private static final int DATABASE_VERSION = 5;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String sQuery = "CREATE TABLE findlocation(id integer primary key, point_name text, date_point text, lat text, longt text);";

        db.execSQL(sQuery);

    }

    public void createUserPosition() {
        SQLiteDatabase db = this.getWritableDatabase();
        String secQuery = "CREATE TABLE findlocationId(id integer primary key, point_name text,  lat text, longt text);";
        db.execSQL(secQuery);
        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addPoint(Point point) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("point_name", point.getPoint_name());
        cv.put("lat", point.getLat());

        cv.put("longt", point.getLongt());
        db.insert("findlocation", null, cv);
        Log.d("Add point", point.toString());
        db.close();
    }

    public void addMyPosition(Double lat, Double longT) {
        SQLiteDatabase db = this.getWritableDatabase();
        this.deleteMyPos();
        this.createUserPosition();
        ContentValues cv = new ContentValues();
        cv.put("point_name", "Мое местоположение");
        cv.put("lat", String.valueOf(lat));
        cv.put("longt", String.valueOf(longT));
        db.insert("findlocationId", null, cv);


        db.close();
    }


    public List<Point> getAllMapsPoint() {
        List<Point> pointList = new ArrayList<Point>();
        Log.d(LOG_TAG, "List<Point> getAllMapsPoint");
        String selectQuery = "SELECT * FROM findlocation ORDER BY id DESC";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Point point = new Point();
                point.setPoint_id(Integer.valueOf(cursor.getString(0)));
                point.setPoint_name(cursor.getString(1));
                point.setDate_point(cursor.getString(2));
                Log.d(LOG_TAG, "getAllMapsPoint cursor.getString(3) " + cursor.getString(3));
                point.setLat(Double.valueOf(cursor.getString(3)));
                point.setLongt(Double.valueOf(cursor.getString(4)));
                pointList.add(point);
            } while (cursor.moveToNext());
        }
        db.close();
        return pointList;
    }

    public void deletePOINTS() {

        Log.d(LOG_TAG, "Удаление таблицы newWebHistory ");
        SQLiteDatabase delet = this.getWritableDatabase();
        delet.execSQL("DROP TABLE findlocation");

        Log.d(LOG_TAG, "создание таблицы findlocation ");
        onCreate(delet);
        delet.close();

    }

    public void deleteMyPos() {
        SQLiteDatabase delet = this.getWritableDatabase();
        delet.execSQL("DROP TABLE findlocationId");
        delet.close();
    }
}
