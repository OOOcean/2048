package com.example.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

public class DatabaseOperate extends AndroidTestCase{

	SQLiteHelper helper;
	public DatabaseOperate(){
		helper = new SQLiteHelper(getContext(),1);
	}
	public boolean CheckUser(String account, String password) {
		return true;
	}
	public void addUser(String account, String password){
		helper = new SQLiteHelper(getContext(),1);
		SQLiteDatabase database = helper.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put("account", account);
		values.put("password", password);
		database.insert("UserInfo", null, values);
	}
	public boolean checkByName(String username) {
		return false;
	}

}
