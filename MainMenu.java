package com.example.MainInterface;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.database.SQLiteHelper;
import com.zhy.game2048.R;

public class MainMenu extends Activity{
	private Button newGame;
	private Button gameRecord;
	private Button personalInfo;
	SQLiteHelper helper;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_layout);
		
		newGame = (Button) findViewById(R.id.newgame);
        gameRecord = (Button) findViewById(R.id.gamerecord);
        personalInfo = (Button) findViewById(R.id.personalinformation);
		final ChoiseMenu choisemenu = new ChoiseMenu(this);
        newGame.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				choisemenu.showMenu();
			}					
		});
        
        helper = new SQLiteHelper(this,1);
        gameRecord.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainMenu.this, GameRecord.class);
				startActivity(intent);
//				SQLiteDatabase db = helper.getWritableDatabase();
//				ContentValues values = new ContentValues();
//				try
//				{
//					Cursor cursor = db.query ("GameRecord",null,null,null,null,null,null);   
//					if(cursor.moveToFirst()){
//						do{
//							String name1 = cursor.getString(cursor.getColumnIndex("cubefour"));
//							String name2 = cursor.getString(cursor.getColumnIndex("cubefive"));
//							String name3 = cursor.getString(cursor.getColumnIndex("account"));
//						}while(cursor.moveToNext());
//					}
//					cursor.close();
//					Toast.makeText(MainMenu.this, "修改成功", Toast.LENGTH_SHORT).show();
//				}
//				catch(Exception e){
//					Toast.makeText(MainMenu.this, "修改失败", Toast.LENGTH_SHORT).show();
//				}
			}
		});
        personalInfo.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
			}
		});
	}
}
