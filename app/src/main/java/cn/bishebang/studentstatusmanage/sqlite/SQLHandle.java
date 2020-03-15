package cn.bishebang.studentstatusmanage.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.List;

public class SQLHandle {

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;

    public SQLHandle(Context context) {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(context, MySQLiteOpenHelper.DBNAME, null, 1);
        db = mySQLiteOpenHelper.getWritableDatabase();
    }

    public boolean addData(String name, List<AddModel> addModelList) {
        ContentValues values = new ContentValues();
        for (int i = 0; i < addModelList.size(); i++) {
            values.put(addModelList.get(i).getName(),addModelList.get(i).getValue());
        }
        return db.insert(name, null, values) > 0;
    }

    public boolean delData(String name,String value) {
        return db.delete(name, "id = ?", new String[]{ value }) > 0;
    }

    public boolean updateData(String name, String key, List<AddModel> addModelList) {
        ContentValues values = new ContentValues();
        for (int i = 0; i < addModelList.size(); i++) {
            values.put(addModelList.get(i).getName(),addModelList.get(i).getValue());
        }
        return db.update(name, values, "id = ?", new String[]{ key }) > 0;
    }

    public Cursor queryAllData(String name) {
        return db.rawQuery("select * from " + name, null);
    }

    public Cursor queryOneData(String name,String key,String value) {
        return db.rawQuery("select * from " + name + " where " + key + " = ?",new String[]{ value }, null);
    }
}
