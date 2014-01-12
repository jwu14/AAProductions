package com.example.egyptianratscrew;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class StatsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_stats);
		
		Bundle b = getIntent().getExtras();
		ImageView gameResult = (ImageView) findViewById(R.id.gameResult);
		TextView time = (TextView) findViewById(R.id.Time);
		TextView slap = (TextView) findViewById(R.id.SlapPerc);
		TextView burn = (TextView) findViewById(R.id.BurnPerc);
		
		if(b.getInt("result") == 1) gameResult.setImageResource(R.drawable.win2);
		else gameResult.setImageResource(R.drawable.lose2);
		
		double t = b.getDouble("time");
		time.setText("Time: " + (int)t + "m " + (int)((t-(int)t)*60) + "s ");
		
		int slapswon = b.getInt("slapswon");
		int totalslaps = b.getInt("totalslaps");
		
		if(totalslaps < 1)
			slap.setText("Slaps Won: No Valid Slaps");
		else
			slap.setText("Slaps Won: " + Integer.toString(slapswon) + " (" + (slapswon*100/totalslaps) + "%) ");
		
		burn.setText("Total Cards Burned: " + Integer.toString(b.getInt("burned")));
		
		gameResult.invalidate();
		time.invalidate();
		slap.invalidate();
		burn.invalidate();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_stats, menu);
		return true;
	}

	/** Called when the user clicks the Play Again button */
	public void playAgain(View view) {
	    Intent intent = new Intent(this, HomePage.class);
	    startActivity(intent);
	}
}
