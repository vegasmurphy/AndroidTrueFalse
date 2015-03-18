package com.example.vegas.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by vegas on 3/13/2015.
 */
public class CheatActivity extends Activity{

    public static final String EXTRA_ANSWER_IS_TRUE = "geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "geoquiz.answer_shown";

    private final String HAS_CHEATED = "has_cheated";
    private boolean mHasCheated;
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;
    private TextView mApiLevelTextview;

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data= new Intent();
        mHasCheated = isAnswerShown;
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        setAnswerShownResult(false);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);

        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);

        mApiLevelTextview = (TextView) findViewById(R.id.apiLevelTextview);
        int version = Build.VERSION.SDK_INT;
        mApiLevelTextview.setText("API level " + version);

        mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });

        if(savedInstanceState != null) {
            setAnswerShownResult(savedInstanceState.getBoolean(HAS_CHEATED,false));
        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(HAS_CHEATED, mHasCheated);
    }
}
