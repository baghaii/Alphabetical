package com.sepidehmiller.alphabetical;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by baghaii on 7/26/17.
 */

public class AlphabetPagerActivity extends AppCompatActivity {
  private ViewPager mViewPager;
  private View mPrevButton, mNextButton, mAgainButton;
  private AlphabetSoundPlayer mSoundPlayer;
  private View mAlphabetView;
  private View mFinishView;
  private FinishBackgroundView mFinishBackgroundView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_alphabet);

    mAlphabetView = findViewById(R.id.view_alphabet_pager);
    mFinishView = findViewById(R.id.view_alphabet_finish);
    mFinishBackgroundView = (FinishBackgroundView) findViewById(R.id.finish_background_view);
    mSoundPlayer = new AlphabetSoundPlayer(getApplicationContext());

    mViewPager = (ViewPager) findViewById(R.id.activity_alphabet_pager_view_pager);
    final AlphabetPagerAdapter adapter = new AlphabetPagerAdapter(this, mSoundPlayer);
    mViewPager.setAdapter(adapter);

    mPrevButton = findViewById(R.id.previous);
    mNextButton = findViewById(R.id.next);

    mAgainButton = findViewById(R.id.button_again);

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

        //Show the Finish View if on the last letter.
        if (item == adapter.getCount() - 1) {
          showFinishView();
        }

        if (item < adapter.getCount() - 1) {
          item = item + 1;
          mViewPager.setCurrentItem(item, true);
        }

      }
    });

    mAgainButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mFinishView.setVisibility(View.GONE);
        mAlphabetView.setVisibility(View.VISIBLE);
      }
    });

    mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

      boolean dragLastPage;

      @Override
      public void onPageSelected(int position) {

        //If a page is selected, then we are not dragging the last page.
        dragLastPage = false;
        super.onPageSelected(position);
        if (position == 0) {
          mPrevButton.setVisibility(View.INVISIBLE);
        } else {
          mPrevButton.setVisibility(View.VISIBLE);
        }
      }


      @Override
      public void onPageScrollStateChanged(int state) {

        if (adapter.getCount() - 1 == mViewPager.getCurrentItem() &&
            state == ViewPager.SCROLL_STATE_DRAGGING) {
          dragLastPage = true;
        }

        //If we dragged the last page and didn't end up selecting a different page, then finish
        if (dragLastPage && state == ViewPager.SCROLL_STATE_IDLE) {
          showFinishView();
        }
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    startLockTask();
  }

  private void showFinishView() {
    mFinishBackgroundView.resetChangingRadius();
    mFinishView.setVisibility(View.VISIBLE);
    mAlphabetView.setVisibility(View.GONE);
    mViewPager.setCurrentItem(0, true);
  }

}
