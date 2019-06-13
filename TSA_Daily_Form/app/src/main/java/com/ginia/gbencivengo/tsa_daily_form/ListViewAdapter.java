package com.ginia.gbencivengo.tsa_daily_form;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewAdapter extends ArrayAdapter<HashMap<String,String>> {

    private ArrayList<HashMap<String,String>> list;
    private Activity activity;

    public ListViewAdapter(Activity activity, int textViewResourceID, ArrayList<HashMap<String,String>> list)
    {
        super(activity, textViewResourceID, list);
        this.activity = activity;
        this.list = list;
    }

    private static class ViewHolder {
        private TextView id_txt;
        private TextView dateTime_txt;
        private TextView questionOne_txt;
        private TextView questionTwo_txt;
        private TextView questionThree_txt;
        private TextView questionFour_txt;
        private TextView questionFive_txt;
        private TextView questionSix_txt;
        private TextView additionalInfo_txt;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            view = inflater.inflate(R.layout.column_row, viewGroup, false);
            holder = new ViewHolder();
            holder.id_txt = (TextView) view.findViewById(R.id.table_Id);
            holder.dateTime_txt = (TextView) view.findViewById(R.id.table_DateAndTime);
            holder.questionOne_txt = (TextView) view.findViewById(R.id.table_QuestionOne);
            holder.questionTwo_txt = (TextView) view.findViewById(R.id.table_QuestionTwo);
            holder.questionThree_txt = (TextView) view.findViewById(R.id.table_QuestionThree);
            holder.questionFour_txt = (TextView) view.findViewById(R.id.table_QuestionFour);
            holder.questionFive_txt = (TextView) view.findViewById(R.id.table_QuestionFive);
            holder.questionSix_txt = (TextView) view.findViewById(R.id.table_QuestionSix);
            holder.additionalInfo_txt = (TextView) view.findViewById(R.id.table_AdditionalInformation);
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)view.getTag();
        }

        HashMap<String,String> temp = list.get(i);
        if (temp != null) {
            holder.id_txt.setText(temp.get("Id"));
            holder.dateTime_txt.setText(temp.get("DateTime"));
            holder.questionOne_txt.setText(temp.get("QuestionOne"));
            holder.questionTwo_txt.setText(temp.get("QuestionTwo"));
            holder.questionThree_txt.setText(temp.get("QuestionThree"));
            holder.questionFour_txt.setText(temp.get("QuestionFour"));
            holder.questionFive_txt.setText(temp.get("QuestionFive"));
            holder.questionSix_txt.setText(temp.get("QuestionSix"));
            holder.additionalInfo_txt.setText(temp.get("AdditionalInformation"));
        }

        return view;
    }
}
