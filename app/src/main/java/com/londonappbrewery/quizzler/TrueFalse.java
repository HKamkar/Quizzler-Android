package com.londonappbrewery.quizzler;

public class TrueFalse {
  private int fQuestionID;
  private boolean fAnswer;
  public TrueFalse(int questionResourceID, boolean trueOrFalse) {
    fQuestionID = questionResourceID;
    fAnswer = trueOrFalse;
  }

  public int getQuestionID() {
    return fQuestionID;
  }

  public void setQuestionID(int questionID) {
    fQuestionID = questionID;
  }

  public boolean isAnswer() {
    return fAnswer;
  }

  public void setAnswer(boolean answer) {
    fAnswer = answer;
  }
}
