package com.example.egyptianratscrew;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends Activity {

	RadioGroup numComps;
	RadioGroup diff;
	CheckBox doubles;
	CheckBox sandwiches;
	CheckBox tens;
	CheckBox tens_sandwiches;
	CheckBox marriage;
	CheckBox divorce;
	CheckBox slap7s;
	CheckBox top_bottom;
	
	int[] settings = new int[10];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
		setContentView(R.layout.activity_settings);
		
		numComps = (RadioGroup) findViewById(R.id.numComps);
	    numComps.check(R.id.comp1);
	    
		diff = (RadioGroup) findViewById(R.id.difficulty);
		diff.check(R.id.med);
		
		doubles = (CheckBox) findViewById(R.id.check_doubles);
		doubles.setChecked(true);
		
		sandwiches = (CheckBox) findViewById(R.id.check_sandwiches);
		sandwiches.setChecked(true);
	    
		tens = (CheckBox) findViewById(R.id.check_tens);
		tens.setChecked(false);
		
		tens_sandwiches = (CheckBox) findViewById(R.id.check_tens_sandwiches);
		tens_sandwiches.setChecked(false);
		
		marriage = (CheckBox) findViewById(R.id.check_marriage);
		marriage.setChecked(false);
	    
		divorce = (CheckBox) findViewById(R.id.check_divorce);
		divorce.setChecked(false);
		
		slap7s = (CheckBox) findViewById(R.id.check_slap7s);
		slap7s.setChecked(false);
		
		top_bottom = (CheckBox) findViewById(R.id.check_top_bottom);
		top_bottom.setChecked(false);
	  }

	public void playListener(View view){
		Intent intent = new Intent(this, MainActivity.class);
		Bundle b = new Bundle();
		int selected;
		RadioButton r;

		selected = numComps.getCheckedRadioButtonId();
	    r = (RadioButton) findViewById(selected);
	    settings[0] = Integer.parseInt((String) r.getText()) + 1;
	    
	    selected = diff.getCheckedRadioButtonId();
	    r = (RadioButton) findViewById(selected);
	    if (((String) r.getText()).equals("Easy")) settings[1] = 1;
	    else if (((String) r.getText()).equals("Medium")) settings[1] = 2;
	    else if (((String) r.getText()).equals("Hard")) settings[1] = 3;
	    else if (((String) r.getText()).equals("Impossible")) settings[1] = 4;
	    
	    if (doubles.isChecked()) settings[2] = 1;
	    else settings[2] = 0;
	    if (sandwiches.isChecked()) settings[3] = 1;
	    else settings[3] = 0;
	    if (tens.isChecked()) settings[4] = 1;
	    else settings[4] = 0;
	    if (tens_sandwiches.isChecked()) settings[5] = 1;
	    else settings[5] = 0;
	    if (marriage.isChecked()) settings[6] = 1;
	    else settings[6] = 0;
	    if (divorce.isChecked()) settings[7] = 1;
	    else settings[7] = 0;
	    if (slap7s.isChecked()) settings[8] = 1;
	    else settings[8] = 0;
	    if (top_bottom.isChecked()) settings[9] = 1;
	    else settings[9] = 0;
	    
	    b.putIntArray("settings", settings);
	    intent.putExtras(b);
	    
	    startActivity(intent);
	}
}
