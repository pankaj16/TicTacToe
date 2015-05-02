package com.game.tictactoegame;

import com.game.utils.Constants;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends Activity {

	Button onePlayer;
	Button twoPlayer;

	ImageView circleImageView;
	ImageView crossImageView;

	ViewSwitcher viewSwitcher;

	Animation slide_in_left, slide_out_right;

	boolean isComputerPlayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);

		init();
		findViews();
		setAnimationToViewSwitcher();
		listners();
	}

	private void init() {
		isComputerPlayer = true;
		slide_in_left = AnimationUtils.loadAnimation(MainActivity.this,
				android.R.anim.slide_in_left);
		slide_out_right = AnimationUtils.loadAnimation(MainActivity.this,
				android.R.anim.slide_out_right);
	}

	private void findViews() {
		viewSwitcher = (ViewSwitcher) findViewById(R.id.viewswitcher);
		onePlayer = (Button) findViewById(R.id.button_mainLayout_onePlayer);
		twoPlayer = (Button) findViewById(R.id.button_mainLayout_twoPlayer);
		circleImageView = (ImageView) findViewById(R.id.imageview_mainLayout_circle);
		crossImageView = (ImageView) findViewById(R.id.imageview_mainLayout_cross);
	}

	private void setAnimationToViewSwitcher() {
		viewSwitcher.setInAnimation(slide_in_left);
		viewSwitcher.setOutAnimation(slide_out_right);
	}

	private void listners() {
		onePlayer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				isComputerPlayer = true;
				viewSwitcher.showNext();
			}
		});

		twoPlayer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				isComputerPlayer = false;
				viewSwitcher.showNext();
			}
		});

		circleImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*** 0 value for circle ***/
				startActivityWithInfo(0);
			}
		});

		crossImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*** 1 value for cross ***/
				startActivityWithInfo(1);
			}
		});
	}
	
	private void startActivityWithInfo(int iconValue){
		Intent intent = new Intent(MainActivity.this, GameActivity.class);
		intent.putExtra(Constants.ICON, iconValue);
		intent.putExtra(Constants.COMPUTER_PLAYER, isComputerPlayer);
		startActivity(intent);
	}
	
	@Override
	protected void onRestart() {
		viewSwitcher.showPrevious();
		super.onRestart();
	}
}
