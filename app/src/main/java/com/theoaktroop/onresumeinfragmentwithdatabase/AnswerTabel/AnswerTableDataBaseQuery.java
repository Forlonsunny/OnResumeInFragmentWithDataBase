package com.theoaktroop.onresumeinfragmentwithdatabase.AnswerTabel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.theoaktroop.onresumeinfragmentwithdatabase.DataBaseHalper.DbHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Suuny on 10/14/2015.
 */
public class AnswerTableDataBaseQuery {

    private DbHelper mAnsDbHelper;
    private SQLiteDatabase mSqLiteDatabase;
    private Context mContext;
    private String[] allColumns = {
            DbHelper.COLUMN_ANSWER_FORM_ID,
            DbHelper.COLUMN_QUESTIONNAIRE_QUESTION_ID,
            DbHelper.COLUMN_QUESTIONNAIRE_SURVEY_TITLE,
            DbHelper.COLUMN_FULL_ANSWER_STRING,
            DbHelper.COLUMN_ANSWER_LAST_SAVED_TIME,
            DbHelper.COLUMN_ANSWER_TIME_DURATION,
            DbHelper.COLUMN_COMPLETED_ANSWER,
            DbHelper.COLUMN_START_LATITUDE_LONGITUDE,
            DbHelper.COLUMN_FINISH_LATITUDE_LONGITUDE


    };

    public AnswerTableDataBaseQuery(Context mContext) {
        this.mContext = mContext;
        this.mAnsDbHelper = new DbHelper(mContext,DbHelper.DATABASE_NAME,null,DbHelper.DATABASE_VERSION);

        try {
            open();
            System.out.println("DataBase open" +"AnswerTableDataBaseQuery");
        } catch (SQLException e) {
            // Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void open() throws SQLException {
        mSqLiteDatabase = mAnsDbHelper.getWritableDatabase();
    }

    public void close() {
        mAnsDbHelper.close();

    }

    public AnswerModule createNewAnswer(String inAnsQuestionId,String surveyTitle, String fullAnswer, String lastSavedTime, String totalTimeDuration, int completeNumberAnswer, String startLatitude, String finishLatitude)
    {
        ContentValues values=new ContentValues();
        values.put(DbHelper.COLUMN_QUESTIONNAIRE_QUESTION_ID,inAnsQuestionId);
        values.put(DbHelper.COLUMN_QUESTIONNAIRE_SURVEY_TITLE,surveyTitle);
        values.put(DbHelper.COLUMN_FULL_ANSWER_STRING,fullAnswer);
        values.put(DbHelper.COLUMN_ANSWER_LAST_SAVED_TIME,lastSavedTime);
        values.put(DbHelper.COLUMN_ANSWER_TIME_DURATION, totalTimeDuration);
        values.put(DbHelper.COLUMN_COMPLETED_ANSWER, completeNumberAnswer);
        values.put(DbHelper.COLUMN_START_LATITUDE_LONGITUDE, startLatitude);
        values.put(DbHelper.COLUMN_FINISH_LATITUDE_LONGITUDE, finishLatitude);
        

        long insertId=mSqLiteDatabase.insert(DbHelper.TABLE_NAME_ANSWER, null, values);
        System.out.println("insert id=" + insertId);
        Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_NAME_ANSWER, allColumns, DbHelper.COLUMN_ANSWER_FORM_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        AnswerModule newAnswerModule=cursorToAnswerModule(cursor);
        cursor.close();
        return  newAnswerModule;
    }

    public List<AnswerModule>getAllAnswerSavedQuestion(){
        List<AnswerModule>listAnswerModules=new ArrayList<AnswerModule>();
        Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_NAME_ANSWER, allColumns, null, null, null, null, null);
        if (cursor!=null)
        {
            cursor.moveToFirst();
            while ((!cursor.isAfterLast())){
                AnswerModule mAnswerModule=cursorToAnswerModule(cursor);
                listAnswerModules.add(mAnswerModule);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return listAnswerModules;
    }

   public AnswerModule getAllAnswerSavedQuestionById(long id){

    Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_NAME_ANSWER,allColumns,
            DbHelper.COLUMN_ANSWER_FORM_ID + "=? ",new String[]{String.valueOf(id)},null,null,null);
    if (cursor!=null) {
        cursor.moveToFirst();
    }
            AnswerModule mAnswerModule=cursorToAnswerModule(cursor);




    return mAnswerModule;
   }

    protected AnswerModule cursorToAnswerModule(Cursor cursor) {
        AnswerModule mAnswerModule=new AnswerModule();

        mAnswerModule.setAnswerId(cursor.getLong(0));
        mAnswerModule.setInAnsQuestionId(cursor.getString(1));
        mAnswerModule.setSurveyTitle(cursor.getString(2));
        mAnswerModule.setFullAnswer(cursor.getString(3));
        mAnswerModule.setLastSavedTime(cursor.getString(4));
        mAnswerModule.setTotalTimeDuration(cursor.getString(5));
        mAnswerModule.setCompleteNumberAnswer(cursor.getInt(6));
        mAnswerModule.setStartLatitude(cursor.getString(7));
        mAnswerModule.setFinishLatitude(cursor.getString(8));


        return mAnswerModule;
    }
    public void answerTableDeleteByAnswerId(long ePID) throws SQLException {
        open();
        mSqLiteDatabase.delete(DbHelper.TABLE_NAME_ANSWER,
                DbHelper.COLUMN_ANSWER_FORM_ID+ " = " + ePID, null);
        close();
    }
    public void answerTableDeleteByQuestionId(long ePID) throws SQLException {
        open();
        mSqLiteDatabase.delete(DbHelper.TABLE_NAME_ANSWER,
                DbHelper.COLUMN_QUESTIONNAIRE_QUESTION_ID + " = " + ePID, null);
        close();
    }
    public void deleteAllTable()
    {


       // mSqLiteDatabase.delete(DbHelper.TABLE_NAME_QUESTIONNAIRE,null,null);
        mSqLiteDatabase.delete(DbHelper.TABLE_NAME_ANSWER,null,null);
        //mSqLiteDatabase.delete(DbHelper.TABLE_NAME_IMAGE,null,null);


    }

    public void upDateAnswer(long insertId,int inAnsQuestionId,String surveyTitle, String fullAnswer, String lastSavedTime, String totalTimeDuration, int completeNumberAnswer, String startLatitude, String finishLatitude) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_QUESTIONNAIRE_QUESTION_ID,inAnsQuestionId);
        values.put(DbHelper.COLUMN_QUESTIONNAIRE_SURVEY_TITLE,surveyTitle);
        values.put(DbHelper.COLUMN_FULL_ANSWER_STRING,fullAnswer);
        values.put(DbHelper.COLUMN_ANSWER_LAST_SAVED_TIME, lastSavedTime);
        values.put(DbHelper.COLUMN_ANSWER_TIME_DURATION, totalTimeDuration);
        values.put(DbHelper.COLUMN_COMPLETED_ANSWER, completeNumberAnswer);
        values.put(DbHelper.COLUMN_START_LATITUDE_LONGITUDE, startLatitude);
        values.put(DbHelper.COLUMN_FINISH_LATITUDE_LONGITUDE, finishLatitude);




        mSqLiteDatabase.update(DbHelper.TABLE_NAME_ANSWER, values, DbHelper.COLUMN_ANSWER_FORM_ID + " = " + insertId, null);
        close();
    }

}
