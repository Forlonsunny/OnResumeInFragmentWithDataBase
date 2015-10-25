package com.theoaktroop.onresumeinfragmentwithdatabase.AnswerTabel;

import java.io.Serializable;

/**
 * Created by Suuny on 10/16/2015.
 */
public class AnswerModule implements Serializable {
    private static final long serialVersionUID = -7406082437623008161L;

    private long answerId;
    private String inAnsQuestionId;
    private String surveyTitle;
    private String fullAnswer;
    private String lastSavedTime;
    private String totalTimeDuration;
    private int completeNumberAnswer;
    private String startLatitude;
    private String finishLatitude;

    public AnswerModule(long answerId, String inAnsQuestionId, String fullAnswer, String lastSavedTime, String totalTimeDuration, int completeNumberAnswer, String startLatitude, String finishLatitude) {
        this.answerId = answerId;
        this.inAnsQuestionId = inAnsQuestionId;
        this.fullAnswer = fullAnswer;
        this.lastSavedTime = lastSavedTime;
        this.totalTimeDuration = totalTimeDuration;
        this.completeNumberAnswer = completeNumberAnswer;
        this.startLatitude = startLatitude;
        this.finishLatitude = finishLatitude;
    }

    public AnswerModule() {
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public String getInAnsQuestionId() {
        return inAnsQuestionId;
    }

    public void setInAnsQuestionId(String  inAnsQuestionId) {
        this.inAnsQuestionId = inAnsQuestionId;
    }

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }
    public String getFullAnswer() {
        return fullAnswer;
    }

    public void setFullAnswer(String fullAnswer) {
        this.fullAnswer = fullAnswer;
    }

    public String getLastSavedTime() {
        return lastSavedTime;
    }

    public void setLastSavedTime(String lastSavedTime) {
        this.lastSavedTime = lastSavedTime;
    }

    public String getTotalTimeDuration() {
        return totalTimeDuration;
    }

    public void setTotalTimeDuration(String totalTimeDuration) {
        this.totalTimeDuration = totalTimeDuration;
    }

    public int getCompleteNumberAnswer() {
        return completeNumberAnswer;
    }

    public void setCompleteNumberAnswer(int completeNumberAnswer) {
        this.completeNumberAnswer = completeNumberAnswer;
    }

    public String getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(String startLatitude) {
        this.startLatitude = startLatitude;
    }

    public String getFinishLatitude() {
        return finishLatitude;
    }

    public void setFinishLatitude(String finishLatitude) {
        this.finishLatitude = finishLatitude;
    }
}