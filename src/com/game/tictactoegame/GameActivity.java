package com.game.tictactoegame;

import java.util.ArrayList;
import com.game.utils.Constants;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends Activity {

	/***
	 * Player will play with his/her choice i.e. either with x or o which will
	 * be getting from getIntent() method
	 ***/
	int player1Icon;
	int player2Icon;

	/*** Include views ***/
	View view0;
	View view1;
	View view2;
	View view3;
	View view4;
	View view5;
	View view6;
	View view7;
	View view8;

	ImageView imageView0;
	ImageView imageView1;
	ImageView imageView2;
	ImageView imageView3;
	ImageView imageView4;
	ImageView imageView5;
	ImageView imageView6;
	ImageView imageView7;
	ImageView imageView8;

	/*** showing player turn ***/
	TextView playerTextView;
	/*** for clearing screen and reset the game ***/
	Button resetButton;

	/***
	 * iconArrayList will have 3 values:- (0 - circle), (1 - cross), (2 -
	 * none/empty)
	 * 
	 * 
	 */
	ArrayList<Integer> cellArrayList;
	/***
	 * nextPlayer = 0 means firstPlayer turn,nextPlayer = 1 means secondPlayer
	 * turn
	 ***/
	int nextPlayer;

	/*** currentPlayer is for saving values at screen rotation ***/
	int currentPlayer;

	/*** variable use for when player simultaneously clicked on two different cells without any wait ***/
	boolean isCalculationOver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_layout);
		if (savedInstanceState == null) {
			init();
			findViews();
			listners();
		}
	}

	private void init() {
		cellArrayList = new ArrayList<>();
		/*** Initialsing arraylist with non/empty image by value 2 ***/
		for (int i = 0; i <= 8; i++) {
			cellArrayList.add(i, 2);
		}
		
		isCalculationOver = true;
		nextPlayer = 0; // initialy first player = 0
		currentPlayer = 1; //its 1 if screen rotated when all cells empty
		try {
			player1Icon = getIntent().getExtras().getInt(Constants.ICON);
			/*** on depending intent extra value player icons will be decided ***/
			if (player1Icon == 1) {
				player2Icon = 0;
			} else {
				player2Icon = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void findViews() {
		view0 = (View) findViewById(R.id.include_0);
		view1 = (View) findViewById(R.id.include_1);
		view2 = (View) findViewById(R.id.include_2);
		view3 = (View) findViewById(R.id.include_3);
		view4 = (View) findViewById(R.id.include_4);
		view5 = (View) findViewById(R.id.include_5);
		view6 = (View) findViewById(R.id.include_6);
		view7 = (View) findViewById(R.id.include_7);
		view8 = (View) findViewById(R.id.include_8);
		imageView0 = (ImageView) view0.findViewById(R.id.imageView);
		imageView1 = (ImageView) view1.findViewById(R.id.imageView);
		imageView2 = (ImageView) view2.findViewById(R.id.imageView);
		imageView3 = (ImageView) view3.findViewById(R.id.imageView);
		imageView4 = (ImageView) view4.findViewById(R.id.imageView);
		imageView5 = (ImageView) view5.findViewById(R.id.imageView);
		imageView6 = (ImageView) view6.findViewById(R.id.imageView);
		imageView7 = (ImageView) view7.findViewById(R.id.imageView);
		imageView8 = (ImageView) view8.findViewById(R.id.imageView);
		playerTextView = (TextView) findViewById(R.id.textview_gameLayout_player);
		resetButton = (Button) findViewById(R.id.button_gameLayout_reset);
	}

	private void listners() {

		resetButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clearData();
			}
		});

		OnClickListener onClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if(!isCalculationOver){
					return;
				}
				isCalculationOver = false;
				switch (v.getId()) {
				case R.id.include_0:
					makeChanges(0);
					break;

				case R.id.include_1:
					makeChanges(1);
					break;

				case R.id.include_2:
					makeChanges(2);
					break;

				case R.id.include_3:
					makeChanges(3);
					break;

				case R.id.include_4:
					makeChanges(4);
					break;

				case R.id.include_5:
					makeChanges(5);
					break;

				case R.id.include_6:
					makeChanges(6);
					break;

				case R.id.include_7:
					makeChanges(7);
					break;

				case R.id.include_8:
					makeChanges(8);
					break;
				}

			}
		};

		view0.setOnClickListener(onClickListener);
		view1.setOnClickListener(onClickListener);
		view2.setOnClickListener(onClickListener);
		view3.setOnClickListener(onClickListener);
		view4.setOnClickListener(onClickListener);
		view5.setOnClickListener(onClickListener);
		view6.setOnClickListener(onClickListener);
		view7.setOnClickListener(onClickListener);
		view8.setOnClickListener(onClickListener);
	}

	/*** Base Method to do all calculation and operations ***/
	private void makeChanges(int position) {
		if (checkValidTurn(position)) {
			/***
			 * Storing values in current player as its now valid turn and if
			 * screen gets rotated then we can restore value Player turn
			 ***/
			currentPlayer = nextPlayer;
			setImage(position);
			nextPlayer();
			calculatePosition();
		}else{
			isCalculationOver = true;
		}
	}

	/*** For checking if the cell is already filled or not ***/
	private boolean checkValidTurn(int position) {
		/***
		 * 2 is for none/blank, if 2 means you can put x or o, it's valid turn
		 ***/
		if (cellArrayList.get(position) == 2) {
			return true;
		}
		return false;
	}

	/***
	 * Method to show which player has the next turn to play and set value in
	 * nextPlayer variable for code logic
	 ***/
	private void nextPlayer() {
		if (nextPlayer == 0) {
			nextPlayer = 1;
			playerTextView.setText(R.string.player2);
		} else {
			nextPlayer = 0;
			playerTextView.setText(R.string.player1);
		}
	}

	/***
	 * Setting image on the basis of position, nextPlayer and
	 * player1icon/player2icon value
	 ***/
	private void setImage(int position) {
		switch (position) {
		case 0:
			setImageDrawable(imageView0, position);
			break;

		case 1:
			setImageDrawable(imageView1, position);
			break;

		case 2:
			setImageDrawable(imageView2, position);
			break;

		case 3:
			setImageDrawable(imageView3, position);
			break;

		case 4:
			setImageDrawable(imageView4, position);
			break;

		case 5:
			setImageDrawable(imageView5, position);
			break;

		case 6:
			setImageDrawable(imageView6, position);
			break;

		case 7:
			setImageDrawable(imageView7, position);
			break;

		case 8:
			setImageDrawable(imageView8, position);
			break;
		}
	}

	/*** subMethod of setImage(int position) for setting image ***/
	private void setImageDrawable(ImageView image, int position) {
		if (currentPlayer == 0) {
			if (player1Icon == 0) {
				image.setImageResource(R.drawable.circle);
			} else {
				image.setImageResource(R.drawable.cross);
			}
			cellArrayList.set(position, player1Icon);
		} else {
			if (player2Icon == 0) {
				image.setImageResource(R.drawable.circle);
			} else {
				image.setImageResource(R.drawable.cross);
			}
			cellArrayList.set(position, player2Icon);
		}
	}

	/***
	 * Setting AlertDialog message on the basis of value matching to player1icon
	 * or player2icon
	 ***/
	private void setMessage(int value) {
		if (player1Icon == value) {
			showMessage("Player 1 wins!!!");
		} else {
			showMessage("Player 2 wins!!!");
		}
	}

	/***
	 * Checking Diagonal, horizontal and vertical positions and on the basis of
	 * the positions showing specific message
	 ***/
	private void calculatePosition() {
		/*** checking diagonal position ***/
		if ((cellArrayList.get(0) == 0 && cellArrayList.get(4) == 0 && cellArrayList
				.get(8) == 0)
				|| (cellArrayList.get(2) == 0 && cellArrayList.get(4) == 0 && cellArrayList
						.get(6) == 0)) {
			setMessage(0);
			return;
		} else if ((cellArrayList.get(0) == 1 && cellArrayList.get(4) == 1 && cellArrayList
				.get(8) == 1)
				|| (cellArrayList.get(2) == 1 && cellArrayList.get(4) == 1 && cellArrayList
						.get(6) == 1)) {
			setMessage(1);
			return;
		}

		/*** Checking horizontal line ***/
		if ((cellArrayList.get(0) == 0 && cellArrayList.get(1) == 0 && cellArrayList
				.get(2) == 0)
				|| (cellArrayList.get(3) == 0 && cellArrayList.get(4) == 0 && cellArrayList
						.get(5) == 0)
				|| (cellArrayList.get(6) == 0 && cellArrayList.get(7) == 0 && cellArrayList
						.get(8) == 0)) {
			setMessage(0);
			return;
		} else if ((cellArrayList.get(0) == 1 && cellArrayList.get(1) == 1 && cellArrayList
				.get(2) == 1)
				|| (cellArrayList.get(3) == 1 && cellArrayList.get(4) == 1 && cellArrayList
						.get(5) == 1)
				|| (cellArrayList.get(6) == 1 && cellArrayList.get(7) == 1 && cellArrayList
						.get(8) == 1)) {
			setMessage(1);
			return;
		}

		/*** Checking vertical line ***/
		if ((cellArrayList.get(0) == 0 && cellArrayList.get(3) == 0 && cellArrayList
				.get(6) == 0)
				|| (cellArrayList.get(1) == 0 && cellArrayList.get(4) == 0 && cellArrayList
						.get(7) == 0)
				|| (cellArrayList.get(2) == 0 && cellArrayList.get(5) == 0 && cellArrayList
						.get(8) == 0)) {
			setMessage(0);
			return;
		} else if ((cellArrayList.get(0) == 1 && cellArrayList.get(3) == 1 && cellArrayList
				.get(6) == 1)
				|| (cellArrayList.get(1) == 1 && cellArrayList.get(4) == 1 && cellArrayList
						.get(7) == 1)
				|| (cellArrayList.get(2) == 1 && cellArrayList.get(5) == 1 && cellArrayList
						.get(8) == 1)) {
			setMessage(1);
			return;
		}

		for (int i = 0; i < cellArrayList.size(); i++) {
			if (cellArrayList.get(i) == 2) {
				isCalculationOver = true;
				return;
				/*** if any of the cell value is 2 means game is not over yet ***/
			}
		}
		showMessage("Tie");
	}

	/*** Showing an AlertDialog when any of the player wins or tie ***/
	private void showMessage(String message) {
		AlertDialog.Builder altDialog = new AlertDialog.Builder(
				GameActivity.this);
		altDialog.setMessage(message);
		altDialog.setTitle("Game Over");
		altDialog.setCancelable(false);

		altDialog.setPositiveButton("Back To Home",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(GameActivity.this,
								MainActivity.class);
						startActivity(intent);
						finish();
					}
				});
		altDialog.setNegativeButton("Restart Game",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						clearData();
					}
				});
		altDialog.show();
	}

	/*** Setting to initial value ***/
	private void clearData() {
		nextPlayer = 0;
		currentPlayer = 1;
		isCalculationOver = true;
		playerTextView.setText(R.string.player1);
		for (int i = 0; i < cellArrayList.size(); i++) {
			cellArrayList.set(i, 2);
		}

		imageView0.setImageResource(R.drawable.blank);
		imageView1.setImageResource(R.drawable.blank);
		imageView2.setImageResource(R.drawable.blank);
		imageView3.setImageResource(R.drawable.blank);
		imageView4.setImageResource(R.drawable.blank);
		imageView5.setImageResource(R.drawable.blank);
		imageView6.setImageResource(R.drawable.blank);
		imageView7.setImageResource(R.drawable.blank);
		imageView8.setImageResource(R.drawable.blank);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(Constants.PLAYER1ICON, player1Icon);
		outState.putInt(Constants.PLAYER2ICON, player2Icon);
		outState.putInt(Constants.NEXT_PLAYER, currentPlayer);
		outState.putSerializable(Constants.CELL_DATA, cellArrayList);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		findViews();
		listners();
		isCalculationOver = true;
		player1Icon = savedInstanceState.getInt(Constants.PLAYER1ICON);
		player2Icon = savedInstanceState.getInt(Constants.PLAYER2ICON);
		nextPlayer = savedInstanceState.getInt(Constants.NEXT_PLAYER);
		currentPlayer = nextPlayer;
		nextPlayer();
		cellArrayList = new ArrayList<Integer>();
		cellArrayList.clear();
		cellArrayList.trimToSize();
		cellArrayList = (ArrayList<Integer>) savedInstanceState
				.getSerializable(Constants.CELL_DATA);
		for (int i = 0; i < cellArrayList.size(); i++) {
				setImageOnRestoreInstanceState(i);
		}
		calculatePosition();
	}

	private void setImageOnRestoreInstanceState(int position) {
		switch (position) {
		case 0:
			setImageDrawableOnRestoreInstance(imageView0, position);
			break;

		case 1:
			setImageDrawableOnRestoreInstance(imageView1, position);
			break;

		case 2:
			setImageDrawableOnRestoreInstance(imageView2, position);
			break;

		case 3:
			setImageDrawableOnRestoreInstance(imageView3, position);
			break;

		case 4:
			setImageDrawableOnRestoreInstance(imageView4, position);
			break;

		case 5:
			setImageDrawableOnRestoreInstance(imageView5, position);
			break;

		case 6:
			setImageDrawableOnRestoreInstance(imageView6, position);
			break;

		case 7:
			setImageDrawableOnRestoreInstance(imageView7, position);
			break;

		case 8:
			setImageDrawableOnRestoreInstance(imageView8, position);
			break;
		}
	}

	private void setImageDrawableOnRestoreInstance(ImageView image, int position) {
		if (cellArrayList.get(position) == 0) {
			image.setImageResource(R.drawable.circle);
		} else if (cellArrayList.get(position) == 1) {
			image.setImageResource(R.drawable.cross);
		} else {
			image.setImageResource(R.drawable.blank);
		}
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(GameActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
		super.onBackPressed();
	}
}
