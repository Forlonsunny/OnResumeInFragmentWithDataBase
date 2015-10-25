package com.theoaktroop.onresumeinfragmentwithdatabase.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.theoaktroop.onresumeinfragmentwithdatabase.AnswerTabel.AnswerModule;
import com.theoaktroop.onresumeinfragmentwithdatabase.AnswerTabel.AnswerTableDataBaseQuery;
import com.theoaktroop.onresumeinfragmentwithdatabase.R;

import java.util.List;

/**
 * Created by Suuny on 10/25/2015.
 */
public class SingleItemView extends Activity {
    private EditText editText1;
    private List<AnswerModule> mAnswerModuleList;

    private AnswerTableDataBaseQuery mAnswerTableDataBaseQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleitemview);
        editText1=(EditText)findViewById(R.id.etv);


    }
    public void SaveAction(View v)
    {
        String answerTitle=editText1.getText().toString();
        mAnswerTableDataBaseQuery = new AnswerTableDataBaseQuery(this);
        mAnswerTableDataBaseQuery.createNewAnswer("1",answerTitle,"allAnswerJsonString","formattedDate","15 min", 10,"inputstartLatitudeLongitude","inputfinishLatitudeLongitude");
        finish();
    }
}
