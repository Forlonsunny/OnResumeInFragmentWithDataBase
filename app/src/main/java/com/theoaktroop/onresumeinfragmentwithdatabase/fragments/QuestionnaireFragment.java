package com.theoaktroop.onresumeinfragmentwithdatabase.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.theoaktroop.onresumeinfragmentwithdatabase.AnswerTabel.AnswerListAdapter;
import com.theoaktroop.onresumeinfragmentwithdatabase.AnswerTabel.AnswerModule;
import com.theoaktroop.onresumeinfragmentwithdatabase.AnswerTabel.AnswerTableDataBaseQuery;
import com.theoaktroop.onresumeinfragmentwithdatabase.R;

import java.sql.SQLException;
import java.util.List;




public class QuestionnaireFragment extends Fragment implements AdapterView.OnItemLongClickListener {



    private ListView mListView;
    private List<AnswerModule> mAnswerModuleList;
    public static AnswerListAdapter mAdapter2;
    private AnswerTableDataBaseQuery mAnswerTableDataBaseQuery;
    private Context context;
    private View rootView;
    long ePID = 0;

    public QuestionnaireFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        context = getActivity();
        mListView = (ListView) rootView.findViewById(R.id.listview_saved_form);
        mAnswerTableDataBaseQuery = new AnswerTableDataBaseQuery(context);

        mAnswerModuleList = mAnswerTableDataBaseQuery.getAllAnswerSavedQuestion();

        mAdapter2 = new AnswerListAdapter(context, mAnswerModuleList);
        mAdapter2.notifyDataSetChanged();
       // Log.d("bug", "here");
        mListView.setAdapter(mAdapter2);


        mListView.setOnItemLongClickListener(this);

        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         rootView =  inflater.inflate(R.layout.fragment_savedforms, container, false);





        return rootView;
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ePID=id;
        showDeleteDialog();
        return true;
    }
    private void showDeleteDialog()
    {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(context);
        alertDialog.setTitle("Delete Entry?")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        try {
                            mAnswerTableDataBaseQuery.answerTableDeleteByAnswerId(ePID);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        forRefresh();

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void forRefresh() {

        mAnswerTableDataBaseQuery = new AnswerTableDataBaseQuery(context);

        mAnswerModuleList = mAnswerTableDataBaseQuery.getAllAnswerSavedQuestion();

        mAdapter2 = new AnswerListAdapter(context, mAnswerModuleList);
        mListView.setAdapter(mAdapter2);

    }

}
