package com.theoaktroop.onresumeinfragmentwithdatabase.QuestionnaireTable;

import java.io.Serializable;

/**
 * Created by Suuny on 10/11/2015.
 */
public class QuestionModule implements Serializable {
    private static final long serialVersionUID = -7406082437623008161L;

    private long questionId;
    private String surveyTitle;
    private  String fullQuestion;
    private  int numberOfQuestion;
    private String lastUpdateTime;

    public QuestionModule(long questionId, String surveyTitle, String fullQuestion, int numberOfQuestion, String lastUpdateTime) {
        this.questionId = questionId;
        this.surveyTitle = surveyTitle;
        this.fullQuestion = fullQuestion;
        this.numberOfQuestion = numberOfQuestion;
        this.lastUpdateTime = lastUpdateTime;
    }
    public QuestionModule()
    {

    }

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }



    public String getFullQuestion() {
        return fullQuestion;
    }

    public void setFullQuestion(String fullQuestion) {
        this.fullQuestion = fullQuestion;
    }

    public int getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(int numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}

