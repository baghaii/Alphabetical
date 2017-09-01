package com.sepidehmiller.alphabetical;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AlphabetFinishActivity extends AppCompatActivity {

  private Button mAgain;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_alphabet_finish);
    mAgain = (Button) findViewById(R.id.button_again);

    mAgain.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        finish();
      }
    });


  }
}
