package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class SQLiteHelper extends SQLiteOpenHelper{
	public static final String CREATE_USERINFO = "create table UserInfo(" +
			"account TEXT primary key," +
			"password INTEGER not null)";
	public static final String CREATE_GAMERECORD = "create table GameRecord(" +
			"account TEXT primary key"+
			"cubefour INTEGER ," +
			"cubefive INTEGER)";
	Context mContext;
	public SQLiteHelper(Context context, int version) {
		super(context, "GameInfo.db", null, version);
		mContext = context;
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_USERINFO);
		db.execSQL(CREATE_GAMERECORD);
		Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
