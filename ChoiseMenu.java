package com.example.MainInterface;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.game2048.GameActivity;
import com.example.game2048.view.Game2048Layout;

public class ChoiseMenu {
	Context context;
	public ChoiseMenu(Context context){
		this.context = context;
	}
	public void showMenu(){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Ñ¡ÔñÄÑ¶È");
		builder.setItems(new String[]{"4¡Á4", "5¡Á5"}, 
				new DialogInterface.OnClickListener() {			
			public void onClick(DialogInterface dialog, int which) {
				switch(which){
				case 0:
					Game2048Layout.mColumn = 4;
					Intent intent = new Intent(context, GameActivity.class);
					context.startActivity(intent);
					break;
				case 1:
					Game2048Layout.mColumn = 5;
					Intent intent1 = new Intent(context, GameActivity.class);
					context.startActivity(intent1);
					break;
				}
			}
		});
		builder.create().show();
	}
}
