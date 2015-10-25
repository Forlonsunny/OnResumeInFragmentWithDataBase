package com.theoaktroop.onresumeinfragmentwithdatabase.AnswerTabel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.theoaktroop.onresumeinfragmentwithdatabase.R;

import java.util.List;



/**
 * Created by Suuny on 10/12/2015.
 */
public class AnswerListAdapter extends BaseAdapter{

    public static final String TAG="AnswerListAdapter";

   private List<AnswerModule> mItems;
    private LayoutInflater mInflater;

    public AnswerListAdapter(Context context, List<AnswerModule> mItems) {
        this.mItems = mItems;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public AnswerModule getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getAnswerId() : position;
    }
    public List<AnswerModule> getItems()
    {
        return mItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v == null) {
            v = mInflater.inflate(R.layout.list_question_title_helper, parent, false);
            holder = new ViewHolder();
            holder.txtQuestionTitle = (TextView) v.findViewById(R.id.txt_question_title);

            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }
        AnswerModule currentItem=getItem(position);
        if(currentItem!=null)
        {
            holder.txtQuestionTitle.setText(currentItem.getSurveyTitle()+" ["+currentItem.getLastSavedTime()+"]");
        }
        return v;
    }
    class ViewHolder {
        TextView txtQuestionTitle;

    }
}