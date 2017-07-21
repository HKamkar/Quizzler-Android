package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

  // TODO: Declare member variables here:
  Button btn_true;
  Button btn_false;
  TextView txt_score;
  TextView txt_question;
  ProgressBar progressBar;
  int fIndex = 0;
  int fQuestion = 0;
  int fScore = 0;

  // TODO: Uncomment to create fQuestion bank
    private TrueFalse[] fQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

  // TODO: Declare constants here
  final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / fQuestionBank.length);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState != null) {
      fScore = savedInstanceState.getInt("ScoreKey");
      fIndex = savedInstanceState.getInt("IndexKey");
    }
    btn_true = (Button) findViewById(R.id.btn_true);
    btn_false = (Button) findViewById(R.id.btn_false);
    txt_question = (TextView) findViewById(R.id.txt_question);
    txt_score = (TextView) findViewById(R.id.txt_score);
    progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    fQuestion = fQuestionBank[fIndex].getQuestionID();
    txt_question.setText(fQuestion);
    txt_score.setText("Score " + fScore + "/" + fQuestionBank.length);
    /*btn_true.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        updateQuestion();
      }
    });
    btn_false.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        updateQuestion();
      }
    });*/
  }
  public void clickTrue(View v) {
    checkAnswer(true);
    updateQuestion();
  }
  public void clickFalse(View v) {
    checkAnswer(false);
    updateQuestion();
  }
  private void updateQuestion() {
    fIndex = (fIndex + 1) % fQuestionBank.length;
    fQuestion = fQuestionBank[fIndex].getQuestionID();
    txt_question.setText(fQuestion);
    progressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
    txt_score.setText("Score " + fScore + "/" + fQuestionBank.length);

    //Set Alert Dialog
    if (fIndex == 0) {
    AlertDialog.Builder alert = new AlertDialog.Builder(this);
      alert.setTitle("The Game Is Over");
      alert.setCancelable(false);
      alert.setMessage("You scored " + fScore + " points!");
      alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          finish();
        }
      });
      alert.show();
    }
  }
  private void checkAnswer(boolean userSelection) {
    boolean correctAnswer = fQuestionBank[fIndex].isAnswer();
    if (userSelection == correctAnswer) {
      Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();
      fScore++;
    } else {
      Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    outState.putInt("ScoreKey" , fScore);
    outState.putInt("IndexKey", fIndex);

  }
}
