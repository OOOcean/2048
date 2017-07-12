package com.example.MainInterface;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.database.DatabaseOperate;
import com.example.database.SQLiteHelper;
import com.zhy.game2048.R;

public class Regist extends Activity{
	private EditText name;
	private EditText pass;
	private Button submit;
	private Button cancel;
	DatabaseOperate dboperate;
	SQLiteHelper helper;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regist_layout);
		submit = (Button) findViewById(R.id.submit);
        cancel = (Button) findViewById(R.id.cancel);
        name =(EditText)findViewById(R.id.txtaccount);
		pass = (EditText)findViewById(R.id.txtpassword);

		helper = new SQLiteHelper(this,1);
        submit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				int flag = 0;
				String username = name.getText().toString();
				String password = pass.getText().toString();
				SQLiteDatabase db = helper.getWritableDatabase();
				Cursor cursor = db.query("UserInfo", null, "account=?", new String[]{username}, 
						null, null, null);
				if(cursor.moveToNext()){
					if(cursor!=null){
						cursor.close();						
					}
					flag=1;
				}
				helper.getReadableDatabase();
				
				if(username.equals("")||password.equals("")){
					Toast.makeText(Regist.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
				}else{
					if(flag==1){
						Toast.makeText(Regist.this, "注册失败，已经存在的用户名", Toast.LENGTH_SHORT).show();
					}else{
						ContentValues values = new ContentValues();
						values.put("account", username);
						values.put("password", password);
						db.insertOrThrow("UserInfo", null, values);
						values = new ContentValues();
						values.put("cubefour", 0);
						values.put("cubefive", 0);
						values.put("account", username);
						db.insertOrThrow("GameInfo", null, values);
						Toast.makeText(Regist.this, "注册成功，返回登录界面", Toast.LENGTH_SHORT).show();
						finish();
					}
				}
			}
		});
        cancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
			}
		});
	}
}
