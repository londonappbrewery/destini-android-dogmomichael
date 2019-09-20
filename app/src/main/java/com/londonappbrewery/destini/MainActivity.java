package com.londonappbrewery.destini;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
    private TextView mStoryTextView;
    private Button mButtonTop;
    private Button mButtonBottom;
    private boolean mEndOfLecture = false;

    /*private StoryLine[] mStories = {
            new StoryLine(getString(R.string.T1_Story),getString(R.string.T1_Ans1),getString(R.string.T1_Ans2)),
            new StoryLine(getString(R.string.T2_Story),getString(R.string.T2_Ans1),getString(R.string.T2_Ans2)),
            new StoryLine(getString(R.string.T3_Story),getString(R.string.T3_Ans1),getString(R.string.T3_Ans2)),
            new StoryLine(getString(R.string.T4_End)),
            new StoryLine(getString(R.string.T5_End)),
            new StoryLine(getString(R.string.T6_End))
            //too costly for memory
    };*/
    private StoryLine2[] mStories = {
            new StoryLine2(R.string.T1_Story, R.string.T1_Ans1, R.string.T1_Ans2),
            new StoryLine2(R.string.T2_Story, R.string.T2_Ans1, R.string.T2_Ans2),
            new StoryLine2(R.string.T3_Story, R.string.T3_Ans1, R.string.T3_Ans2),
            new StoryLine2(R.string.T4_End),
            new StoryLine2(R.string.T5_End),
            new StoryLine2(R.string.T6_End)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
        mStoryTextView = findViewById(R.id.storyTextView);
        mButtonTop = findViewById(R.id.buttonTop);
        mButtonBottom = findViewById(R.id.buttonBottom);

        // Retrieving any saved state
        if (savedInstanceState != null) {
            mStoryTextView.setText(savedInstanceState.getCharSequence("storyKey"));
            mButtonTop.setText(savedInstanceState.getCharSequence("buttonTopKey"));
            mButtonBottom.setText(savedInstanceState.getCharSequence("buttonBottomKey"));
            mEndOfLecture = savedInstanceState.getBoolean("endOfLectureKey");

            if (mEndOfLecture) {
                clearButtons();
                displayAlertDialog();
            }
        } else {

        }


        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:
        mButtonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if(mStoryTextView.getText() == mStories[0].getStory() || mStoryTextView.getText() == mStories[1].getStory()){
                 *//*

                    //move to T3_Story
                    changeStoryLine(mStories[2]);
                }else{
                    //mStoryTextView.setText(R.string.T6_End);

                    //move to T6_End
                    storyEnd(mStories[5]);
                }*/

                if (mStoryTextView.getText() == getString(mStories[0].getStoryId()) || mStoryTextView.getText() == getString(mStories[1].getStoryId())) {
                    /*
                    mStoryTextView.setText(R.string.T3_Story);
                    mButtonTop.setText(R.string.T3_Ans1);
                    mButtonBottom.setText(R.string.T3_Ans2);
                    */

                    //move to T3_Story
                    changeStoryLine(mStories[2]);
                } else {
                    //mStoryTextView.setText(R.string.T6_End);

                    //move to T6_End
                    storyEnd(mStories[5]);
                }
                Log.d("up", "pressed");
            }
        });


        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
        mButtonBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mStoryTextView.getText() == getString(mStories[0].getStoryId())) {
                    /*
                    mStoryTextView.setText(R.string.T2_Story);
                    mButtonTop.setText(R.string.T2_Ans1);
                    mButtonBottom.setText(R.string.T2_Ans2);
                    */

                    //move to T2_Story
                    changeStoryLine(mStories[1]);
                } else if (mStoryTextView.getText() == getString(mStories[1].getStoryId())) {
                    //mStoryTextView.setText(R.string.T4_End);

                    //move to T4_End
                    storyEnd(mStories[3]);
                } else {
                    //mStoryTextView.setText(R.string.T5_End);

                    //move to T5_End
                    storyEnd(mStories[4]);
                }
                Log.d("down", "pressed");
            }
        });

    }

    /*private void changeStoryLine(int storyString, int buttonTopString, int buttonBottomString){
        mStoryTextView.setText(storyString);
        mButtonTop.setText(buttonTopString);
        mButtonBottom.setText(buttonBottomString);
    }*/
    private void changeStoryLine(StoryLine2 storyline) {
        mStoryTextView.setText(storyline.getStoryId());
        mButtonTop.setText(storyline.getAnswer1Id());
        mButtonBottom.setText(storyline.getAnswer2Id());
    }
    /*private void storyEnd(int storyString){
        mStoryTextView.setText(storyString);
        Toast.makeText(getApplicationContext(),"End of Story! The choices were up to you",Toast.LENGTH_LONG).show();

        // hiding buttons

        mButtonTop.setVisibility(View.INVISIBLE);
        mButtonBottom.setVisibility(View.INVISIBLE);
    }*/

    private void storyEnd(StoryLine2 storyline) {
        mStoryTextView.setText(storyline.getStoryId());
        Toast.makeText(getApplicationContext(), "End of Story! The choices were up to you", Toast.LENGTH_LONG).show();
        mEndOfLecture = true;
        clearButtons();
        displayAlertDialog();
    }

    private void clearButtons() {
        //hiding buttons
        mButtonTop.setVisibility(View.INVISIBLE);
        mButtonBottom.setVisibility(View.INVISIBLE);

    }

    //implemnting an AlertDialog

    private void displayAlertDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Farewell, End of Story");
        alert.setCancelable(false);
        alert.setMessage("Hope you Enjoyed the Story");
        alert.setNegativeButton("restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //back to first page

                changeStoryLine(mStories[0]);
                mEndOfLecture = false;

                //make buttons visible
                mButtonTop.setVisibility(View.VISIBLE);
                mButtonBottom.setVisibility(View.VISIBLE);
            }
        });
        alert.setPositiveButton("stop", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alert.show();
    }

    //lets solve screen rotation issues

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putCharSequence("storyKey", mStoryTextView.getText());
        outState.putCharSequence("buttonTopKey", mButtonTop.getText());
        outState.putCharSequence("buttonBottomKey", mButtonBottom.getText());
        outState.putBoolean("endOfLectureKey", mEndOfLecture);
    }
}
