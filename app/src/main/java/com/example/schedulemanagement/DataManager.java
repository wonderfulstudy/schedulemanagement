package com.example.schedulemanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class DataManager {
    /***************************
     *用户数据表的数据库操作在以下代码块中进行
     **************************/
    //用于数据库创建时使用的表的列名
    public static final String USER_ID = "user_id";     //用户ID
    public static final String USER_NAME = "user_name"; //用户名
    public static final String USER_PWD = "user_pwd";   //用户密码
    public static final String USER_GROUP_ID = "object_id";  //群组ID

    private static final String USER_TABLE_NAME = "users";   //用户表名
    //创建用户表
    private static final String DB_USER_TABLE_CREATE =
            "CREATE TABLE "+USER_TABLE_NAME+"("+ USER_ID + " integer primary key," + USER_NAME
                    + " varchar," + USER_PWD + " varchar," + USER_GROUP_ID + " integer);";

    //添加新用户，即注册
    public long insertUserData(UserData userData) {
        String userName=userData.getUserName();
        String userPwd=userData.getUserPwd();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, userName);
        values.put(USER_PWD, userPwd);
        return mSQLiteDatabase.insert(USER_TABLE_NAME, USER_ID, values);
    }
    //根据用户名，更新用户信息，如修改密码
    public boolean updateUserDataByName(UserData userData) {
        String userName = userData.getUserName();
        String userPwd = userData.getUserPwd();
        int groupId = userData.getgroupId();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, userName);
        values.put(USER_PWD, userPwd);
        values.put(USER_GROUP_ID, groupId);
        return mSQLiteDatabase.update(USER_TABLE_NAME, values,USER_NAME + "='" + userName, null) > 0;
    }
    //根据用户id更新数据
    public boolean updateUserDataById(String columnName, int id,
                                      String columnValue) {
        ContentValues values = new ContentValues();
        values.put(columnName, columnValue);
        return mSQLiteDatabase.update(USER_TABLE_NAME, values, USER_ID + "='" + id +"'", null) > 0;
    }
    //根据id删除用户
    public boolean deleteUserData(int id) {
        return mSQLiteDatabase.delete(USER_TABLE_NAME, USER_ID + "='" + id + "'", null) > 0;
    }
    //根据用户名删除
    public boolean deleteUserDatabyname(String name) {
        return mSQLiteDatabase.delete(USER_TABLE_NAME, USER_NAME + "='" + name +"'", null) > 0;
    }
    //删除所有用户
    public boolean deleteAllUserDatas() {
        return mSQLiteDatabase.delete(USER_TABLE_NAME, null, null) > 0;
    }
    //根据用户名找用户，可以判断注册时用户名是否已经存在
    public int findUserByName(String userName){
        int result=0;
        Cursor mCursor=mSQLiteDatabase.query(USER_TABLE_NAME, null, USER_NAME+ "='" +userName+"'",
                null, null, null, null);
        if(mCursor!=null){
            result=mCursor.getCount();
            mCursor.close();
        }
        return result;
    }
    //根据用户名和密码找用户，用于登录
    public boolean findUserByNameAndPwd(String userName,String pwd){
        boolean result = false;
        Cursor mCursor=mSQLiteDatabase.query(USER_TABLE_NAME, null, USER_NAME+"='"+userName+"' and "+USER_PWD+"='"+pwd+"'",
                null, null, null, null);
        if(mCursor!=null){
            result = true;
            mCursor.close();
        }
        return result;
    }
    /********************************
     * 事务数据库表的操作在以下代码块中操作
     ********************************/
    //事务表的列名
    private static final String CALENDER_ID = "calender_id";//事务id
    private static final String CALENDER_THEME = "calender_theme";//事务主题
    private static final String CALENDER_TIME = "calender_time";//事务创建时间
    private static final String CALENDER_REMARK = "calender_remark";//事务备注
    private static final String CALENDER_USER = "calender_user";//事务用户id

    private static final String CALENDER_TABLE_NAME = "calender";//事务表名

    //创建事务表
    private static final String DB_CALENDER_TABLE_CREATE =
            "CREATE TABLE "+CALENDER_TABLE_NAME+"("+CALENDER_ID+" integer primary key,"+
                    CALENDER_THEME+" date,"+CALENDER_TIME+" varchar,"+CALENDER_REMARK+
                    " varchar,"+CALENDER_USER+" integer);";

    //添加新事务
    public long insertObjectData(ObjectData objectData) {
        String theme = objectData.getTheme();
        String remark = objectData.getRemark();
        Date time = objectData.getTime();

        ContentValues values = new ContentValues();
        values.put(CALENDER_THEME, theme);
        values.put(CALENDER_TIME, time.toString());
        values.put(CALENDER_REMARK, remark);
        return mSQLiteDatabase.insert(CALENDER_TABLE_NAME, CALENDER_ID, values);
    }
    //根据事务id更新事务信息
    public boolean updateObjectDataByid(ObjectData objectData,int id) {
        String theme = objectData.getTheme();
        String remark = objectData.getRemark();
        Date time = objectData.getTime();

        ContentValues values = new ContentValues();
        values.put(CALENDER_THEME, theme);
        values.put(CALENDER_TIME, time.toString());
        values.put(CALENDER_REMARK, remark);
        return mSQLiteDatabase.update(CALENDER_TABLE_NAME, values,CALENDER_ID + "='"+ id +"'", null) > 0;
    }

    //根据事务id删除
    public boolean deleteObjectDataByid(int id) {
        return mSQLiteDatabase.delete(CALENDER_TABLE_NAME, CALENDER_ID + "='" + id + "'", null) > 0;
    }

    //根据用户id删除所有事务
    public boolean deleteAllObjectDatasByUserId(int id) {
        return mSQLiteDatabase.delete(CALENDER_TABLE_NAME, CALENDER_USER + "='" + id + "'", null) > 0;
    }

    //根据时间和用户id查询所有事务的信息
    public String[] fetchObjectByTime(Date date){
        String[] result;
        int list = 0,count = 0;

        Cursor mCursor=mSQLiteDatabase.query(CALENDER_TABLE_NAME, null, CALENDER_TIME+"='"+date.toString()+"'",
                null, null, null, null);
        list = mCursor.getCount();
        result = new String[list];
        mCursor.moveToFirst();
        while (mCursor.moveToNext()){
            result[count] = mCursor.getString(2);
            count++;
        }

        return result;
    }

    /**********************************
     * 群组数据库表的操作在以下代码块中操作
     * ********************************/
    //群组表的列名
    private static final String GROUP_ID = "group_id";//群组id
    private static final String GROUP_NAME = "group_name";//群组名称
    private static final String GROUP_ADMINISTRATOR_ID = "administrator_id";//管理员id

    private static final String OBJECT_TABLE_NAME = "object";   //群组表名

    //创建群组表
    private static final String DB_OBJECT_TABLE_CREATE =
            "CREATE TABLE "+OBJECT_TABLE_NAME+"("+GROUP_ID+" integer primary key,"+
                    GROUP_NAME+" varchar,"+GROUP_ADMINISTRATOR_ID+" integer);";


    //添加新群组
    public long insertGroupData(GroupData groupData) {
        String groupName = groupData.getGroupName();
        String administratorId = groupData.getAdministratorId();

        ContentValues values = new ContentValues();
        values.put(GROUP_NAME, groupName);
        values.put(GROUP_ADMINISTRATOR_ID, administratorId);
        return mSQLiteDatabase.insert(OBJECT_TABLE_NAME, GROUP_ID, values);
    }
    //根据群组id更新事务信息
    public boolean updateGroupDataByid(GroupData groupData,int id) {
        String groupName = groupData.getGroupName();
        String administratorId = groupData.getAdministratorId();

        ContentValues values = new ContentValues();
        values.put(GROUP_NAME, groupName);
        values.put(GROUP_ADMINISTRATOR_ID, administratorId);
        return mSQLiteDatabase.update(OBJECT_TABLE_NAME, values,GROUP_ID + "='"+ id +"'", null) > 0;
    }
    //根据群组id删除
    public boolean deleteGroupDataByid(int id) {
        return mSQLiteDatabase.delete(OBJECT_TABLE_NAME, GROUP_ID + "='" + id + "'", null) > 0;
    }
    //根据管理员id删除所有事务
    public boolean deleteAllGroupDatasByUserId(int id) {
        return mSQLiteDatabase.delete(OBJECT_TABLE_NAME, GROUP_ADMINISTRATOR_ID + "='" + id + "'", null) > 0;
    }

    /*************************
     *全局管理函数在以下代码块中
     *************************/

    private static final String TAG = "DataManager";    //日志信息文件
    private static final String DB_NAME = "schedule_management";  //数据库名
    private static final int DB_VERSION = 2;            //数据库版本号
    private Context mContext = null;                    //数据库连接变量
    private SQLiteDatabase mSQLiteDatabase = null;
    private DataBaseManagementHelper mDatabaseHelper = null;



    private static class DataBaseManagementHelper extends SQLiteOpenHelper {

        public DataBaseManagementHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME + ";");
            db.execSQL("DROP TABLE IF EXISTS " + CALENDER_TABLE_NAME + ";");
            db.execSQL("DROP TABLE IF EXISTS " + OBJECT_TABLE_NAME + ";");
            db.execSQL(DB_USER_TABLE_CREATE);
            db.execSQL(DB_CALENDER_TABLE_CREATE);
            db.execSQL(DB_OBJECT_TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    //重写DataManager方法传递上下文信息
    public DataManager(Context context) {
        mContext = context;
    }
    //打开数据库
    public void openDataBase() throws SQLException {
        mDatabaseHelper = new DataBaseManagementHelper(mContext);
        mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
    }
    //关闭数据库
    public void closeDataBase() throws SQLException {
        mDatabaseHelper.close();
    }

}
