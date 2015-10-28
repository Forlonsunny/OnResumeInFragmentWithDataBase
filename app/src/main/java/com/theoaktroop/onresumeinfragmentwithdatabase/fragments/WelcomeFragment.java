package com.theoaktroop.onresumeinfragmentwithdatabase.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.theoaktroop.onresumeinfragmentwithdatabase.AnswerTabel.AnswerTableDataBaseQuery;
import com.theoaktroop.onresumeinfragmentwithdatabase.QuestionnaireTable.QuestionListAdapter;
import com.theoaktroop.onresumeinfragmentwithdatabase.QuestionnaireTable.QuestionModule;
import com.theoaktroop.onresumeinfragmentwithdatabase.QuestionnaireTable.QuestionTableDataBaseQuery;
import com.theoaktroop.onresumeinfragmentwithdatabase.R;

import java.util.List;


public class WelcomeFragment extends Fragment {



    private Context context;
    private EditText editText1;
    private AnswerTableDataBaseQuery mAnswerTableDataBaseQuery;
    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
      context=getActivity();







        View view=inflater.inflate(R.layout.fragment_welcome, container, false);
        editText1=(EditText)view.findViewById(R.id.etv);
        editText1.setSingleLine();
        editText1.setImeOptions(EditorInfo.IME_ACTION_DONE);
        Button updateButton=(Button)view.findViewById(R.id.update_question);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    hideSoftKeyboard((Activity) context);
                }
                catch (Exception d)
                {

                }
                String answerTitle = editText1.getText().toString();
                mAnswerTableDataBaseQuery = new AnswerTableDataBaseQuery(context);
                mAnswerTableDataBaseQuery.createNewAnswer("1", answerTitle, "allAnswerJsonString", "formattedDate", "15 min", 10, "inputstartLatitudeLongitude", "inputfinishLatitudeLongitude");

            }
        });

        return view;
    }
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }




}
