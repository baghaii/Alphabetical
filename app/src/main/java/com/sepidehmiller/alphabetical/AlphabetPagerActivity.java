package com.sepidehmiller.alphabetical;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by baghaii on 7/26/17.
 */

public class AlphabetPagerActivity extends AppCompatActivity {
  private ViewPager mViewPager;
  private ImageButton mPrevButton, mNextButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_alphabet_pager);

    mViewPager = (ViewPager) findViewById(R.id.activity_alphabet_pager_view_pager);
    final AlphabetPagerAdapter adapter = new AlphabetPagerAdapter(this);
    mViewPager.setAdapter(adapter);

    mPrevButton = (ImageButton) findViewById(R.id.previous);
    mNextButton = (ImageButton) findViewById(R.id.next);

    mPrevButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        int item = mViewPager.getCurrentItem();
        if (item > 0) {
          item = item - 1;
          mViewPager.setCurrentItem(item, true);
        }
      }
    });

    mNextButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        int item = mViewPager.getCurrentItem();

        //Start the Finish Activity if on the last letter.
        if (item == adapter.getCount() - 1) {
          startFinishActivity();
        }

        if (item < adapter.getCount() - 1) {
          item = item + 1;
          mViewPager.setCurrentItem(item, true);
        }

      }
    });

    mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
      @Override
      public void onPageSelected(int position) {
        super.onPageSelected(position);
        if (position == 0) {
          mPrevButton.setVisibility(View.INVISIBLE);
        } else {
          mPrevButton.setVisibility(View.VISIBLE);
        }
      }
    });
  }

  //TODO: Add a way to swipe to finish activity.

  private void startFinishActivity() {
    Intent i = new Intent(AlphabetPagerActivity.this, AlphabetFinishActivity.class);
    startActivity(i);
  }

}
