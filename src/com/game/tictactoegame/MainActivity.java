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

	Button playButton;

	ImageView circleImageView;
	ImageView crossImageView;

	ViewSwitcher viewSwitcher;
    /*** for view switcher animation ***/
	Animation slide_in_left, slide_out_right;
	
	/*** boolean to restore when rotating screen ***/
	boolean isChoiceScreen;
	
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
		isChoiceScreen = false;
		slide_in_left = AnimationUtils.loadAnimation(MainActivity.this,
				android.R.anim.slide_in_left);
		slide_out_right = AnimationUtils.loadAnimation(MainActivity.this,
				android.R.anim.slide_out_right);
	}

	private void findViews() {
		viewSwitcher = (ViewSwitcher) findViewById(R.id.viewswitcher);
		playButton = (Button) findViewById(R.id.button_mainLayout_play);
		circleImageView = (ImageView) findViewById(R.id.imageview_mainLayout_circle);
		crossImageView = (ImageView) findViewById(R.id.imageview_mainLayout_cross);
	}

	private void setAnimationToViewSwitcher() {
		viewSwitcher.setInAnimation(slide_in_left);
		viewSwitcher.setOutAnimation(slide_out_right);
	}

	private void listners() {

		playButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				viewSwitcher.showNext();
				isChoiceScreen = true;
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
		/*** Passing data to another activity on the basis of user choice image***/
		intent.putExtra(Constants.ICON, iconValue);
		startActivity(intent);
		finish();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean(Constants.CHOICE_SCREEN, isChoiceScreen);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		findViews();
		listners();
		isChoiceScreen = savedInstanceState.getBoolean(Constants.CHOICE_SCREEN);
		if(isChoiceScreen){
			viewSwitcher.showNext();
		}
	}
}
