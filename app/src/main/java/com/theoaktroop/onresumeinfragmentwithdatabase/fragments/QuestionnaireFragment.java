package com.theoaktroop.onresumeinfragmentwithdatabase.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.theoaktroop.onresumeinfragmentwithdatabase.QuestionnaireTable.QuestionListAdapter;
import com.theoaktroop.onresumeinfragmentwithdatabase.QuestionnaireTable.QuestionModule;
import com.theoaktroop.onresumeinfragmentwithdatabase.QuestionnaireTable.QuestionTableDataBaseQuery;
import com.theoaktroop.onresumeinfragmentwithdatabase.R;

import java.util.List;




public class QuestionnaireFragment extends Fragment {



    private ListView mListView;
    private List<QuestionModule> mQuestionModuleList;
    private QuestionListAdapter mAdapter;
    private QuestionTableDataBaseQuery mQuestionTableDataBaseQuery;
    private Context context;
    long ePID=0;

    public QuestionnaireFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_questionaire, container, false);


        context=getActivity();


        mQuestionTableDataBaseQuery=new QuestionTableDataBaseQuery(context);
        // First time inserted only because id are not same
        // QuestionModule mQuestionModule1=mQuestionTableDataBaseQuery.createNewQuestion(1,"1","English","This is a english full question",10,"14.10.15");
         //QuestionModule mQuestionModule2=mQuestionTableDataBaseQuery.createNewQuestion(2,"2","Physic","This is a physic full question",10,"14.10.15");
        mQuestionModuleList=mQuestionTableDataBaseQuery.getAllSurveyTitle();

        System.out.println("Question List Size: "+mQuestionModuleList.size());


        mListView = (ListView) rootView.findViewById(R.id.listview);

        System.out.println("Questionnaire Fragment in");


        if(mQuestionModuleList==null)
            System.out.println("mQuestionModuleList is NULL");
        if(mQuestionModuleList.isEmpty())
            System.out.println("mQuestionModuleList is Empty");

        if(mQuestionModuleList!=null && !mQuestionModuleList.isEmpty())
        {
            System.out.println("Questionnaire Fragment OK");
            mAdapter = new QuestionListAdapter(context,mQuestionModuleList);

            mListView.setAdapter(mAdapter);
            System.out.println("ListView size: " + mListView.getCount());
        }


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // Send single item click data to SingleItemView Class
                Intent i = new Intent(getActivity(), SingleItemView.class);
                i.putExtra("id",1);
                startActivity(i);
            }

        });

        return rootView;
    }

}
