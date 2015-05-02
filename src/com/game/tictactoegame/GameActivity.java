package com.game.tictactoegame;

import java.util.ArrayList;
import com.game.utils.Constants;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {

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

	TextView playerTextView;
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
	 * tur, nextPlayer = 2 means Computer turn
	 ***/
	int nextPlayer;

	boolean isComputerPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_layout);

		init();
		findViews();
		listners();
	}

	private void init() {
		cellArrayList = new ArrayList<>();
		/*** Initialsing arraylist with non/empty image by value 2 ***/
		for (int i = 0; i <= 8; i++) {
			cellArrayList.add(i, 2);
		}
		nextPlayer = 0; // initialy first player = 0
		try {
			player1Icon = getIntent().getExtras().getInt(Constants.ICON);
			/*** on depending intent extra value player icons will be decided ***/
			if (player1Icon == 1) {
				player2Icon = 0;
			} else {
				player2Icon = 1;
			}
			isComputerPlayer = getIntent().getExtras().getBoolean(
					Constants.COMPUTER_PLAYER);
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
				// 2 means computer player
				if (nextPlayer == 2) {
					return;
				}
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

	private void makeChanges(int position) {
		if (checkValidTurn(position)) {
			setImage(position);
			nextPlayer();
			calculatePosition(position);
		}
	}

	/*** For checking if the cell is already filled or not ***/
	private boolean checkValidTurn(int position) {
		/***
		 * check against 2 because 2 is for none/blank if 2 means you can put x
		 * or o, it is valid turn
		 ***/
		if (cellArrayList.get(position) == 2) {
			return true;
		}
		return false;
	}

	private void nextPlayer() {
		if (isComputerPlayer) {
			if (nextPlayer == 0) {
				nextPlayer = 2;
				playerTextView.setText(R.string.computer);
			} else {
				nextPlayer = 0;
				playerTextView.setText(R.string.player1);
			}
		} else {
			if (nextPlayer == 0) {
				nextPlayer = 1;
				playerTextView.setText(R.string.player2);
			} else {
				nextPlayer = 0;
				playerTextView.setText(R.string.player1);
			}
		}
	}

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

	private void setImageDrawable(ImageView image, int position) {
		if (nextPlayer == 0) {
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

	private void setMessage(int value) {
		if (player1Icon == value) {
			showMessage("Player 1 wins!!!");
		} else {
			showMessage("Player 2 wins!!!");
		}
	}

	private void calculatePosition(int position) {
		/*** checking diagonal position ***/
		if ((cellArrayList.get(0) == 0 && cellArrayList.get(4) == 0 && cellArrayList
				.get(8) == 0)
				|| (cellArrayList.get(2) == 0 && cellArrayList.get(4) == 0 && cellArrayList
						.get(6) == 0)) {
			setMessage(0);
		} else if ((cellArrayList.get(0) == 1 && cellArrayList.get(4) == 1 && cellArrayList
				.get(8) == 1)
				|| (cellArrayList.get(2) == 1 && cellArrayList.get(4) == 1 && cellArrayList
						.get(6) == 1)) {
			setMessage(1);
		}

		/*** Checking horizontal line ***/
		if ((cellArrayList.get(0) == 0 && cellArrayList.get(1) == 0 && cellArrayList
				.get(2) == 0)
				|| (cellArrayList.get(3) == 0 && cellArrayList.get(4) == 0 && cellArrayList
						.get(5) == 0)
				|| (cellArrayList.get(6) == 0 && cellArrayList.get(7) == 0 && cellArrayList
						.get(8) == 0)) {
			setMessage(0);
		} else if ((cellArrayList.get(0) == 1 && cellArrayList.get(1) == 1 && cellArrayList
				.get(2) == 1)
				|| (cellArrayList.get(3) == 1 && cellArrayList.get(4) == 1 && cellArrayList
						.get(5) == 1)
				|| (cellArrayList.get(6) == 1 && cellArrayList.get(7) == 1 && cellArrayList
						.get(8) == 1)) {
			setMessage(1);
		}

		/*** Checking vertical line ***/
		if ((cellArrayList.get(0) == 0 && cellArrayList.get(3) == 0 && cellArrayList
				.get(6) == 0)
				|| (cellArrayList.get(1) == 0 && cellArrayList.get(4) == 0 && cellArrayList
						.get(7) == 0)
				|| (cellArrayList.get(2) == 0 && cellArrayList.get(5) == 0 && cellArrayList
						.get(8) == 0)) {
			setMessage(0);
		} else if ((cellArrayList.get(0) == 1 && cellArrayList.get(3) == 1 && cellArrayList
				.get(6) == 1)
				|| (cellArrayList.get(1) == 1 && cellArrayList.get(4) == 1 && cellArrayList
						.get(7) == 1)
				|| (cellArrayList.get(2) == 1 && cellArrayList.get(5) == 1 && cellArrayList
						.get(8) == 1)) {
			setMessage(1);
		}

		for (int i = 0; i < cellArrayList.size(); i++) {
			if (cellArrayList.get(i) == 2) {
				return; // if any of the cell is 2 i.e. empty then return
			}
		}
		showMessage("Tie");
	}

	private void showMessage(String message) {
		AlertDialog.Builder altDialog = new AlertDialog.Builder(
				GameActivity.this);
		altDialog.setMessage(message);
		altDialog.setTitle("Tic Tac Toe");
		altDialog.setCancelable(false);

		altDialog.setPositiveButton("Back To Menu",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
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

	private void clearData() {
		/*** Setting to initial value ***/
		nextPlayer = 0;
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
}
