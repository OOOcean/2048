package com.example.game2048;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.SQLiteHelper;
import com.example.game2048.view.Game2048Layout;
import com.example.game2048.view.Game2048Layout.OnGame2048Listener;
import com.zhy.game2048.R;

public class GameActivity extends Activity implements OnGame2048Listener
{
	private Game2048Layout mGame2048Layout;

	private TextView mScore;
	public static String name;
	int score;
	SQLiteHelper helper;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mScore = (TextView) findViewById(R.id.id_score);
		mGame2048Layout = (Game2048Layout) findViewById(R.id.id_game2048);
		mGame2048Layout.setOnGame2048Listener(this);
	}

	@Override
	public void onScoreChange(int score)
	{
		mScore.setText("SCORE: " + score);
		this.score = score;
	}

	@Override
	public void onGameOver()
	{	
		helper = new SQLiteHelper(this,1);
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		Cursor cursor = db.query("GameRecord", null, null, null, 
				null, null, null);
		if(cursor.moveToFirst()){
			do{
				int cubefour = cursor.getInt(cursor.getColumnIndex("cubefour"));
				int cubefive = cursor.getInt(cursor.getColumnIndex("cubefive"));
				String username = cursor.getString(cursor.getColumnIndex("account"));
				if(username.equals(name)){
					if(Game2048Layout.mColumn==4 && score > cubefour)
					{
						values.put("cubefour", score);
						String whereClause = "account=?";
						String[] whereArgs={String.valueOf(name)};
						db.update("GameRecord",values,whereClause,whereArgs);   
						Toast.makeText(GameActivity.this, "4×4最高分已更新", Toast.LENGTH_SHORT).show();
					}
					else if(Game2048Layout.mColumn==5 && score > cubefive)
					{
						values.put("cubefive", score);
						String whereClause = "account=?";
						String[] whereArgs={String.valueOf(name)};
						db.update("GameRecord",values,whereClause,whereArgs); 
						Toast.makeText(GameActivity.this, "5×5最高分已更新", Toast.LENGTH_SHORT).show();
					}
					break;
				}
			}while(cursor.moveToNext());
		}
		cursor.close();
		
		
		
		new AlertDialog.Builder(this).setTitle("GAME OVER")
				.setMessage("YOU HAVE GOT " + mScore.getText())
				.setPositiveButton("RESTART", new OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						mGame2048Layout.restart();
					}
				}).setNegativeButton("EXIT", new OnClickListener()
				{

					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						finish();
					}
				}).show();
	}

}
