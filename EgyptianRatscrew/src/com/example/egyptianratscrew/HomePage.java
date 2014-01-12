package com.example.egyptianratscrew;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class HomePage extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_home_page);
	}
	
	/** Called when the user clicks the Play button */
	public void playGame(View view) {
	    Intent intent = new Intent(this, SettingsActivity.class);
	    startActivity(intent);
	}
	
	/** Called when the user clicks the Rules button */
	public void showRules(View view) {
	    Intent intent = new Intent(this, RulesActivity.class);
	    startActivity(intent);
	}
}