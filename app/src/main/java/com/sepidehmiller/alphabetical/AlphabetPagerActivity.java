package com.sepidehmiller.alphabetical;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;


/**
 * Created by baghaii on 7/26/17.
 */

public class AlphabetPagerActivity extends AppCompatActivity {
  private ViewPager mViewPager;
  private ImageButton mPrevButton, mNextButton;
  private AlphabetSoundPlayer mSoundPlayer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_alphabet_pager);

    mSoundPlayer = new AlphabetSoundPlayer(getApplicationContext());

    mViewPager = (ViewPager) findViewById(R.id.activity_alphabet_pager_view_pager);
    final AlphabetPagerAdapter adapter = new AlphabetPagerAdapter(this, mSoundPlayer);
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
          startFinishActivity();
        }
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (mViewPager != null) {
      mViewPager.setVisibility(View.VISIBLE);
    }
  }

  private void startFinishActivity() {
    Intent i = new Intent(AlphabetPagerActivity.this, AlphabetFinishActivity.class);
    startActivity(i);
    mViewPager.setVisibility(View.INVISIBLE);
    mViewPager.setCurrentItem(0,true);

  }

}
