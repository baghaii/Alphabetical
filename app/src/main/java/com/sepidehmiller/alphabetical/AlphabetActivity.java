package com.sepidehmiller.alphabetical;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import static com.sepidehmiller.alphabetical.R.id.letter;

public class AlphabetActivity extends AppCompatActivity {

  private static final String TAG = "AlphabetActivity";
  private static final String KEY_INDEX = "index";

  ImageButton mLetterButton;
  ImageButton mLeftButton;
  ImageButton mRightButton;
  int mCurrentIndex = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_alphabet);

    mLetterButton = (ImageButton) findViewById(letter);

    mLetterButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

      }
    });



    mLeftButton = (ImageButton) findViewById(R.id.previous);
    mLeftButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (mCurrentIndex > 1) {
          mCurrentIndex = mCurrentIndex - 1;
          setLetterView(mCurrentIndex);
          setNavigationView(mCurrentIndex);
        }
      }
    });

    mRightButton = (ImageButton) findViewById(R.id.next);
    mRightButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (mCurrentIndex < 26) {
          mCurrentIndex = mCurrentIndex + 1;
          setLetterView(mCurrentIndex);
          setNavigationView(mCurrentIndex);
        }
      }
    });

    if (savedInstanceState != null) {
      mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,1);
      setLetterView(mCurrentIndex);
      setNavigationView(mCurrentIndex);
    }
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    Log.i(TAG, "onSaveInstanceState");
    outState.putInt(KEY_INDEX, mCurrentIndex);
  }

  private void setLetterView(int count) {

    switch (count) {
      case 1:
        mLetterButton.setImageResource(R.drawable.letter_a);
        mLetterButton.setContentDescription(getText(R.string.letter_a));
        break;
      case 2:
        mLetterButton.setImageResource(R.drawable.letter_b);
        mLetterButton.setContentDescription(getText(R.string.letter_b));
        break;
      case 3:
        mLetterButton.setImageResource(R.drawable.letter_c);
        mLetterButton.setContentDescription(getText(R.string.letter_c));
        break;
      case 4:
        mLetterButton.setImageResource(R.drawable.letter_d);
        mLetterButton.setContentDescription(getText(R.string.letter_d));
        break;
      case 5:
        mLetterButton.setImageResource(R.drawable.letter_e);
        mLetterButton.setContentDescription(getText(R.string.letter_e));
        break;
      case 6:
        mLetterButton.setImageResource(R.drawable.letter_f);
        mLetterButton.setContentDescription(getText(R.string.letter_f));
        break;
      case 7:
        mLetterButton.setImageResource(R.drawable.letter_g);
        mLetterButton.setContentDescription(getText(R.string.letter_g));
        break;
      case 8:
        mLetterButton.setImageResource(R.drawable.letter_h);
        mLetterButton.setContentDescription(getText(R.string.letter_h));
        break;
      case 9:
        mLetterButton.setImageResource(R.drawable.letter_i);
        mLetterButton.setContentDescription(getText(R.string.letter_i));
        break;
      case 10:
        mLetterButton.setImageResource(R.drawable.letter_j);
        mLetterButton.setContentDescription(getText(R.string.letter_j));
        break;
      case 11:
        mLetterButton.setImageResource(R.drawable.letter_k);
        mLetterButton.setContentDescription(getText(R.string.letter_k));
        break;
      case 12:
        mLetterButton.setImageResource(R.drawable.letter_l);
        mLetterButton.setContentDescription(getText(R.string.letter_l));
        break;
      case 13:
        mLetterButton.setImageResource(R.drawable.letter_m);
        mLetterButton.setContentDescription(getText(R.string.letter_m));
        break;
      case 14:
        mLetterButton.setImageResource(R.drawable.letter_n);
        mLetterButton.setContentDescription(getText(R.string.letter_n));
        break;
      case 15:
        mLetterButton.setImageResource(R.drawable.letter_o);
        mLetterButton.setContentDescription(getText(R.string.letter_o));
        break;
      case 16:
        mLetterButton.setImageResource(R.drawable.letter_p);
        mLetterButton.setContentDescription(getText(R.string.letter_p));
        break;
      case 17:
        mLetterButton.setImageResource(R.drawable.letter_q);
        mLetterButton.setContentDescription(getText(R.string.letter_q));
        break;
      case 18:
        mLetterButton.setImageResource(R.drawable.letter_r);
        mLetterButton.setContentDescription(getText(R.string.letter_r));
        break;
      case 19:
        mLetterButton.setImageResource(R.drawable.letter_s);
        mLetterButton.setContentDescription(getText(R.string.letter_s));
        break;
      case 20:
        mLetterButton.setImageResource(R.drawable.letter_t);
        mLetterButton.setContentDescription(getText(R.string.letter_t));
        break;
      case 21:
        mLetterButton.setImageResource(R.drawable.letter_u);
        mLetterButton.setContentDescription(getText(R.string.letter_u));
        break;
      case 22:
        mLetterButton.setImageResource(R.drawable.letter_v);
        mLetterButton.setContentDescription(getText(R.string.letter_v));
        break;
      case 23:
        mLetterButton.setImageResource(R.drawable.letter_w);
        mLetterButton.setContentDescription(getText(R.string.letter_w));
        break;
      case 24:
        mLetterButton.setImageResource(R.drawable.letter_x);
        mLetterButton.setContentDescription(getText(R.string.letter_x));
        break;
      case 25:
        mLetterButton.setImageResource(R.drawable.letter_y);
        mLetterButton.setContentDescription(getText(R.string.letter_y));
        break;
      case 26:
        mLetterButton.setImageResource(R.drawable.letter_z);
        mLetterButton.setContentDescription(getText(R.string.letter_z));
        break;
      default:
        mLetterButton.setImageResource(R.drawable.letter_a);
        mLetterButton.setContentDescription(getText(R.string.letter_a));
        break;
    }

  }

  void setNavigationView(int i) {
    if (i == 1) {
      mLeftButton.setVisibility(View.INVISIBLE);
    } else {
      mLeftButton.setVisibility(View.VISIBLE);
    }
  }

}
