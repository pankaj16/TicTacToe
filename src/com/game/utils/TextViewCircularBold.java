package com.game.utils;

import com.game.tictactoegame.R;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewCircularBold extends TextView{
	public TextViewCircularBold(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public TextViewCircularBold(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public TextViewCircularBold(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		if (!isInEditMode()) {
			Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
					Constants.FONT_CIRCULAR);
			setTypeface(tf);
			setTextAppearance(context, R.style.textview_bold);
		}
	}
}