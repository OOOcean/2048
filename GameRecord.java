package com.example.MainInterface;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.SQLiteHelper;
import com.example.game2048.GameActivity;
import com.example.game2048.view.Game2048Layout;
import com.zhy.game2048.R;

public class GameRecord extends Activity{
	protected static String name;
	TextView h4;
	TextView h5;
	Button resetrecord;
	SQLiteHelper helper;
	SQLiteDatabase db;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_record);
		resetrecord = (Button) findViewById(R.id.resetrecord);
		h4 =(TextView)findViewById(R.id.high4);
		h5 = (TextView)findViewById(R.id.high5);
		helper = new SQLiteHelper(this,1);
		db = helper.getWritableDatabase();
		Cursor cursor = db.query ("GameRecord",null,null,null,null,null,null);   
		if(cursor.moveToFirst()){
			do{
				int cubefour = cursor.getInt(cursor.getColumnIndex("cubefour"));
				int cubefive = cursor.getInt(cursor.getColumnIndex("cubefive"));
				String account = cursor.getString(cursor.getColumnIndex("account"));
				if(account.equals(name)){
					h4.setText(String.valueOf(cubefour));
					h5.setText(String.valueOf(cubefive));
				}
			}while(cursor.moveToNext());
		}
		cursor.close();
		resetrecord.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Prompt();
			}			
		});
	}
	
	public void Prompt(){
		 new AlertDialog.Builder(GameRecord.this).setTitle("系统提示") 		  
	     .setMessage("你确定要删除游戏记录吗？")	  
	     .setPositiveButton("不行，我再考虑一下",new DialogInterface.OnClickListener() {
	         public void onClick(DialogInterface dialog, int which) {
	             
	  
	         }  	  
	     }).setNegativeButton("好的",new DialogInterface.OnClickListener() {
	         public void onClick(DialogInterface dialog, int which) {
	        	db = helper.getWritableDatabase();
	     		ContentValues values = new ContentValues();
	        	Cursor cursor = db.query("GameRecord", null, null, null, 
	     				null, null, null);
	        	
	        	if(cursor.moveToFirst()){
	    			do{
	    				String username = cursor.getString(cursor.getColumnIndex("account"));
	    				if(username.equals(name)){	    			
	    					values.put("cubefour", 0);
	    					values.put("cubefive", 0);
	    					String whereClause = "account=?";
							String[] whereArgs={String.valueOf(name)};
							db.update("GameRecord",values,whereClause,whereArgs);   
							Toast.makeText(GameRecord.this, "已清除游戏记录", Toast.LENGTH_SHORT).show();
							finish();
							break;
	    				}
	    			}while(cursor.moveToNext());
	        	}
	        	cursor.close();
	         }  	  
	     }).show();
	}
}
