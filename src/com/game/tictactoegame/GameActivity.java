package com.game.tictactoegame;

import java.util.ArrayList;

import com.game.utils.Constants;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
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

	/***
	 * iconArrayList will have 3 values:-  0 - circle, 1 - cross, 2 - none/blank
	 * 
	 * 
	 */
	ArrayList<Integer> cellArrayList;
	/*** nextPlayer = 0 means firstPlayer turn,nextPlayer = 1 means secondPlayer turn ***/
	int nextPlayer;

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
		nextPlayer = 0;
		try {
			player1Icon = getIntent().getExtras().getInt(Constants.ICON);
			/*** on depending intent extra value player icons will be decided ***/
			if (player1Icon == 1) {
				player2Icon = 0;
			} else {
				player2Icon = 1;
			}
			Toast.makeText(
					GameActivity.this,
					"Player1Icon: " + player1Icon + "\n Plar2Icon: "
							+ player2Icon, Toast.LENGTH_SHORT).show();
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

	}

	private void listners() {

		OnClickListener onClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
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
					makeChanges(4);
					break;

				case R.id.include_4:
					makeChanges(5);
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
				default:
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

	private void makeChanges(int position){
		if(checkValidTurn(position)){
			
		}
	}
	
	/*** For checking if the cell is already filled or not ***/
	private boolean checkValidTurn(int position) {
		/*** check against 2 because 2 is for none/blank ***/
		if (cellArrayList.get(position) != 2) {
			return true;
		}
		return false;
	}
	
	private void nextPlayer() {
		if(nextPlayer == 0){
			nextPlayer = 1;
		}else{
			nextPlayer = 0;
		}
	}
}
