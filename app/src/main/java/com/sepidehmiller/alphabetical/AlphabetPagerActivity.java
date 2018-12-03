package com.sepidehmiller.alphabetical;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
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


    mViewPager = findViewById(R.id.activity_alphabet_pager_view_pager);
    final AlphabetPagerAdapter adapter = new AlphabetPagerAdapter(mSoundPlayer);
    mViewPager.setAdapter(adapter);

    //http://andraskindler.com/blog/2013/create-viewpager-transitions-a-pagertransformer-example/
    //This code caused the wrong letter sound to load on the LG 5 on screen rotation.
    /* mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
      @Override
      public void transformPage(@NonNull View view, float v) {
        final float normalizedposition = Math.abs(Math.abs(v) - 1);
        view.setScaleX(normalizedposition / 2 + 0.5f);
        view.setScaleY(normalizedposition / 2 + 0.5f);
        view.setRotationY(v * -90);
      }
    }); */

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
        mFinishView.animate()
            .alpha(0f)
            .setDuration(2000)
            .setListener(new AnimatorListenerAdapter() {
              @Override
              public void onAnimationEnd(Animator animation) {
                mFinishView.setVisibility(View.GONE);
              }
            });

        mAlphabetView.setAlpha(0f);
        mAlphabetView.setVisibility(View.VISIBLE);
        mAlphabetView.animate()
            .alpha(1f)
            .setDuration(2000)
            .setListener(null);

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
    if (!isLocked()) {
      startLockTask();
    }
  }

  private boolean isLocked() {
    ActivityManager activityManager;
    activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      return activityManager.getLockTaskModeState() != ActivityManager.LOCK_TASK_MODE_NONE;
    } else {
      return activityManager.isInLockTaskMode();
    }
  }

  private void showFinishView() {
    //This crossfade animation only works on Lollipop and up

    // Animate the loading view to 0% opacity. After the animation ends,
    // set its visibility to GONE as an optimization step (it won't
    // participate in layout passes, etc.)

    //https://developer.android.com/training/animation/reveal-or-hide-view

    mAlphabetView.animate()
        .alpha(0f)
        .setDuration(2000)
        .setListener(new AnimatorListenerAdapter() {
          @Override
          public void onAnimationEnd(Animator animation) {
            mAlphabetView.setVisibility(View.GONE);
            mViewPager.setCurrentItem(0, true);
          }
        });

    mFinishView.setAlpha(0f);
    mFinishView.setVisibility(View.VISIBLE);

    mFinishView.animate()
        .alpha(1f)
        .setDuration(2000)
        .setListener(null);

    mFinishBackgroundView.resetChangingRadius();
  }

  @Override
  protected void onDestroy() {
    mSoundPlayer.release();
    mSoundPlayer = null;
    super.onDestroy();
  }

}
