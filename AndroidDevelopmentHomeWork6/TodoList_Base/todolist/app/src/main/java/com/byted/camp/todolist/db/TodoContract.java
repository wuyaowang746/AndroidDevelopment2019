package com.byted.camp.todolist.db;

import android.provider.BaseColumns;

/**
 * Created on 2019/1/22.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public final class TodoContract {

    // TODO 定义表结构和 SQL 语句常量

    private TodoContract() {

    }
    public static final String SQL_CREATE_ENTRIES=
            "CREATE TABLE " + TodoEntry.TABLE_NAME
                    + "(" + TodoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TodoEntry.COLUMN_DATE + " TEXT, " + TodoEntry.COLUMN_STATE + " TEXT, "
                    + TodoEntry.COLUMN_CONTENT + " TEXT)";

    public static final String SQL_DELETE_ENTRIES="DROP TABLE IF EXISTS"+TodoEntry.TABLE_NAME;

    public static final String SQL_ALTER_TABLE_ADD_PRIORITY=
            "ALTER TABLE " + TodoEntry.TABLE_NAME + " ADD " + TodoEntry.COLUMN_PRIORITY + " INTEGER";

    public static class TodoEntry implements BaseColumns{
        public static final String TABLE_NAME="Todo_Table";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_STATE = "state";
        public static final String COLUMN_CONTENT = "content";

        //增加优先级
        public static final String COLUMN_PRIORITY="priority";
    }

}
