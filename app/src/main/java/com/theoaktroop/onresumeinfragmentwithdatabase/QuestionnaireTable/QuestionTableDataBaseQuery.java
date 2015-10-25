package com.theoaktroop.onresumeinfragmentwithdatabase.QuestionnaireTable;

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
public class QuestionTableDataBaseQuery {

    private DbHelper mQuDbHelper;
    private SQLiteDatabase mSqLiteDatabase;
    private Context mContext;
    private String[] allColumns = {
            DbHelper.COLUMN_QUESTIONNAIRE_QUESTION_ID,
            DbHelper.COLUMN_QUESTIONNAIRE_SURVEY_TITLE,
            DbHelper.COLUMN_QUESTIONNAIRE_FULL_QUESTION,
            DbHelper.COLUMN_QUESTIONNAIRE_NUMBER_OF_TOTAL_QESTION,
            DbHelper.COLUMN_QUESTIONNAIRE_LAST_UPDATE_TIME,
            DbHelper.COLUMN_SERVER_QUESTION_ID


    };

    public QuestionTableDataBaseQuery(Context mContext) {
        this.mContext = mContext;
        this.mQuDbHelper = new DbHelper(mContext,DbHelper.DATABASE_NAME,null,DbHelper.DATABASE_VERSION);

        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void open() throws SQLException {
        mSqLiteDatabase = mQuDbHelper.getWritableDatabase();
    }

    public void close() {
        mQuDbHelper.close();

    }

    public QuestionModule createNewQuestion(int qid,String server_q_id,String surveyTitle, String fullQuestion, int numberOfQuestion, String lastUpdateTime)
    {

        ContentValues values=new ContentValues();
        values.put(DbHelper.COLUMN_QUESTIONNAIRE_QUESTION_ID, qid);
        values.put(DbHelper.COLUMN_QUESTIONNAIRE_SURVEY_TITLE, surveyTitle);
        values.put(DbHelper.COLUMN_QUESTIONNAIRE_FULL_QUESTION,fullQuestion);
        values.put(DbHelper.COLUMN_QUESTIONNAIRE_NUMBER_OF_TOTAL_QESTION,numberOfQuestion);
        values.put(DbHelper.COLUMN_QUESTIONNAIRE_LAST_UPDATE_TIME, lastUpdateTime);
        values.put(DbHelper.COLUMN_SERVER_QUESTION_ID, server_q_id);

        long insertId=mSqLiteDatabase.insert(DbHelper.TABLE_NAME_QUESTIONNAIRE,null,values);

        Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_NAME_QUESTIONNAIRE, allColumns, DbHelper.COLUMN_QUESTIONNAIRE_QUESTION_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        QuestionModule newQuestionModule=cursorToQestionModule(cursor);
        cursor.close();
        return  newQuestionModule;

    }

    public List<QuestionModule>getAllSurveyTitle(){
        List<QuestionModule>listQuestionModules=new ArrayList<QuestionModule>();
        Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_NAME_QUESTIONNAIRE, allColumns, null, null, null, null, null);
        if (cursor!=null)
        {
            cursor.moveToFirst();
            while ((!cursor.isAfterLast())){
                QuestionModule mQuestionModule=cursorToQestionModule(cursor);
                listQuestionModules.add(mQuestionModule);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return listQuestionModules;
    }

   public QuestionModule getAllSurveyTitleById(long id){

    Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_NAME_QUESTIONNAIRE, allColumns,
            DbHelper.COLUMN_QUESTIONNAIRE_QUESTION_ID + "=? ", new String[]{String.valueOf(id)}, null, null, null);
    if (cursor!=null) {
        cursor.moveToFirst();
    }
            QuestionModule mQuestionModule=cursorToQestionModule(cursor);




    return mQuestionModule;
   }

    public String getServerIdBySerial(long id) {
        System.out.println("Searching for ID: "+id);
        String value = "0";
        Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_NAME_QUESTIONNAIRE, allColumns,
                DbHelper.COLUMN_QUESTIONNAIRE_QUESTION_ID + "=? ", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor!=null) {
            cursor.moveToFirst();
            value = cursor.getString(cursor.getColumnIndex("server_q_id"));
        }

        return value;
    }

    protected QuestionModule cursorToQestionModule(Cursor cursor) {
        QuestionModule mQuestionModule=new QuestionModule();
        mQuestionModule.setQuestionId(cursor.getLong(0));
        mQuestionModule.setSurveyTitle(cursor.getString(1));
        mQuestionModule.setFullQuestion(cursor.getString(2));
        mQuestionModule.setNumberOfQuestion(cursor.getInt(3));
        mQuestionModule.setLastUpdateTime(cursor.getString(4));
        mQuestionModule.setLastUpdateTime(cursor.getString(5));
        return mQuestionModule;
    }
    public void questionDeleteById(long ePID) throws SQLException {
        open();
        mSqLiteDatabase.delete(DbHelper.TABLE_NAME_QUESTIONNAIRE,
                DbHelper.COLUMN_QUESTIONNAIRE_QUESTION_ID + " = " + ePID, null);
        close();
    }

    public void deleteAllDataFromQuestionnaireTable(){
        mSqLiteDatabase.execSQL("DELETE FROM " + DbHelper.TABLE_NAME_QUESTIONNAIRE);
    }

    public void deleteAllTable()
    {


        mSqLiteDatabase.delete(DbHelper.TABLE_NAME_QUESTIONNAIRE,null,null);
       // mSqLiteDatabase.delete(DbHelper.TABLE_NAME_ANSWER,null,null);
       // mSqLiteDatabase.delete(DbHelper.TABLE_NAME_IMAGE,null,null);


    }
/*
    public void upDateQuestion(long insertId,String questionTitle, String fullQuestion, int numberOfQuestion, String lastUpdateTime) throws SQLException {
        open();
        ContentValues values = new ContentValues();

        values.put(DbHelper.COLUMN_QUESTIONNAIRE_SURVEY_TITLE, questionTitle);
        values.put(DbHelper.COLUMN_QUESTIONNAIRE_FULL_QUESTION, fullQuestion);
        values.put(DbHelper.COLUMN_QUESTIONNAIRE_NUMBER_OF_TOTAL_QESTION, numberOfQuestion);
        values.put(DbHelper.COLUMN_QUESTIONNAIRE_LAST_UPDATE_TIME, lastUpdateTime);




        mSqLiteDatabase.update(DbHelper.TABLE_NAME_QUESTIONNAIRE, values, DbHelper.COLUMN_QUESTIONNAIRE_QUESTION_ID + " = " + insertId,  null);
        close();
    }
*/

}
