package cn.bishebang.studentstatusmanage.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "sql.db";

    public Context contextId;

    public static final String BANJI = "create table banji("
            + "id Integer primary key autoincrement,"
            + "nianji text,"
            + "zhuanye text,"
            + "num text)";

    public static final String XUEJI = "create table xueji("
            + "id Integer primary key autoincrement,"
            + "stuname text,"
            + "ruxuenianfen text,"
            + "shifozaiji text)";

    public static final String ZHUANYE = "create table zhuanye("
            + "id Integer primary key autoincrement,"
            + "name text)";

    public static final String XINXI = "create table xinxi("
            + "id Integer primary key autoincrement,"
            + "xuehao text,"
            + "name text,"
            + "dianhua text,"
            + "banji text)";

    public static final String KECHENG = "create table kecheng("
            + "id Integer primary key autoincrement,"
            + "name text)";

    public static final String LUNTAN = "create table luntan("
            + "id Integer primary key autoincrement,"
            + "title text,"
            + "content text)";

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        contextId = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BANJI);
        db.execSQL(XUEJI);
        db.execSQL(ZHUANYE);
        db.execSQL(XINXI);
        db.execSQL(KECHENG);
        db.execSQL(LUNTAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
