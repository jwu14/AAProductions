package com.example.egyptianratscrew;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	GameView mGameView;
    MediaPlayer slapSound;
    ImageView sound;
    Intent music;
    private boolean mIsBound = false;
	private MusicService mServ;
	private ServiceConnection Scon =new ServiceConnection(){

		public void onServiceConnected(ComponentName name, IBinder binder) {
			mServ = ((MusicService.ServiceBinder) binder).getService();
		}

		public void onServiceDisconnected(ComponentName name) {
			mServ = null;
		}};
		
	void doBindService(){
 		bindService(new Intent(this,MusicService.class),
				Scon,Context.BIND_AUTO_CREATE);
		mIsBound = true;
	}

	void doUnbindService()
	{
		if(mIsBound)
		{
			unbindService(Scon);
      		mIsBound = false;
		}
	}
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        Bundle b = getIntent().getExtras();
        
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setContentView(R.layout.activity_main);
        
        TextView myDeckCounter = (TextView) findViewById(R.id.myDeckCounter);
		TextView cpuDeckCounter1 = (TextView) findViewById(R.id.cpuDeckCounter1);
		TextView cpuDeckCounter2 = (TextView) findViewById(R.id.cpuDeckCounter2);
		TextView cpuDeckCounter3 = (TextView) findViewById(R.id.cpuDeckCounter3);
		TextView cpuDeckCounter4 = (TextView) findViewById(R.id.cpuDeckCounter4);
		TextView cpuDeckCounter5 = (TextView) findViewById(R.id.cpuDeckCounter5);
		TextView burnCounter = (TextView) findViewById(R.id.burnCounter);
		TextView slapMsg = (TextView) findViewById(R.id.slapMsg);
		
		sound = (ImageView) findViewById(R.id.sound);
		
		slapSound = MediaPlayer.create(this, R.raw.slapsound);
		
		int [] settings = b.getIntArray("settings");
		mGameView = (GameView) findViewById(R.id.egyptianratscrew);
		if(settings[0] == 2)
			mGameView.setupCounter(myDeckCounter, burnCounter, cpuDeckCounter1, null, null);
		else if(settings[0] == 3)
			mGameView.setupCounter(myDeckCounter, burnCounter, cpuDeckCounter4, cpuDeckCounter5, null);
		else if(settings[0] == 4)
			mGameView.setupCounter(myDeckCounter, burnCounter, cpuDeckCounter3, cpuDeckCounter1, cpuDeckCounter2);
		mGameView.setupBundle(b);
		
		mGameView.setupTextViews(slapMsg);
		
		doBindService();
		music = new Intent();
		music.setClass(this,MusicService.class);
		startService(music);
		
	}
	
	public void slapListener(View view){
		mGameView.slap();
		if(slapSound.isPlaying())
			slapSound.stop();
		slapSound.start();
	}

	public void drawListener(View view){
		if(mGameView.gameOver != -1)
			gameOver();
		else
			mGameView.playerTurn();	
	}
	
	public void musicListener(View view){
		if(mServ.mPlayer.isPlaying()){
			sound.setImageResource(R.drawable.soundoff);
			mServ.pauseMusic();
		}
		else{
			sound.setImageResource(R.drawable.soundon);
			mServ.resumeMusic();
		}
		
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		stopService(music);
	    doUnbindService();
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		mServ.pauseMusic();
	}
	
	@Override
	protected void onRestart(){
		super.onRestart();
		mServ.resumeMusic();
	}
	
	public void gameOver(){
		Intent intent = new Intent(this, StatsActivity.class);
		Bundle b = new Bundle();
		b.putInt("result", mGameView.gameOver);
		b.putDouble("time", mGameView.time_elapsed);
		b.putInt("slapswon", mGameView.slaps_won);
		b.putInt("totalslaps", mGameView.total_slaps);
		b.putInt("burned", mGameView.cards_burned);
	    intent.putExtras(b);
	    stopService(music);
	    doUnbindService();
	    startActivity(intent);
	}
}
