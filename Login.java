package com.example.MainInterface;

import android.app.Activity;
import android.content.Intent;
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
import com.example.game2048.GameActivity;
import com.zhy.game2048.R;

public class Login extends Activity{
	public EditText name;
	public EditText pass;
	private Button login;
	private Button regist;
	SQLiteHelper helper;
	private DatabaseOperate dbOperate;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		login = (Button) findViewById(R.id.login);
        regist = (Button) findViewById(R.id.regist);
        name =(EditText)findViewById(R.id.log_account);
		pass = (EditText)findViewById(R.id.log_password);
		dbOperate = new DatabaseOperate();
		helper = new SQLiteHelper(this,1);
        login.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
			int flag = 0;
			String username = name.getText().toString();
			String password = pass.getText().toString();			
			SQLiteDatabase db = helper.getWritableDatabase();
			Cursor cursor = db.query("UserInfo", null, "account=? and password=?", new String[]{username,password}, 
					null, null, null);
			if(cursor.moveToNext()){
				if(cursor!=null){
					cursor.close();						
				}
				flag=1;
			}
			helper.getReadableDatabase();
			
			if(username.equals("")||pass.equals("")){
				Toast.makeText(Login.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
			}
			else{
				if(flag==1){
					Toast.makeText(Login.this, "登陆成功", Toast.LENGTH_SHORT).show();
					GameActivity.name = username;
					GameRecord.name = username;
					PersonalInfo.name = username;
					Intent intent = new Intent(Login.this, MainMenu.class);
					startActivity(intent);
				}
				else{
					Toast.makeText(Login.this, "登录失败，请检查用户名和密码", Toast.LENGTH_SHORT).show();
					}
				}
			}
        });
        regist.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Login.this, Regist.class);
				startActivity(intent);				
			}
		});
	}
}
