package com.byted.camp.todolist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created on 2019/1/22.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public class TodoDbHelper extends SQLiteOpenHelper {

    // TODO 定义数据库名、版本；创建数据库
    public static final int DATABASE_VERSION=2;
    public static final String DATABASE_NAME="TodoDataBase.db";
    public static final String TAG="upgrade";

    public TodoDbHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("test","oncreate");
        db.execSQL(TodoContract.SQL_CREATE_ENTRIES);
    }

    @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("tt", "onUpgrade: ");
        Log.e(TAG, "oldVersion: "+oldVersion);
        Log.e(TAG, "newVersion: "+newVersion);
       /*for (int i = oldVersion; i <newVersion ; i++) {
            switch (i){
                case 1:
                    try{
                        db.execSQL(TodoContract.SQL_ALTER_TABLE_ADD_PRIORITY);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                    default:break;
            }
        }*/
    }

}
