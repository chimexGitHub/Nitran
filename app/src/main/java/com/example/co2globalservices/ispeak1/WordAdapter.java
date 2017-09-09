package com.example.co2globalservices.ispeak1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CO2 on 5/31/2017.
 */
public class WordAdapter extends ArrayAdapter <Word> {

    public WordAdapter(Activity context, ArrayList<Word> words){
        super(context, 0 , words);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // return super.getView(position, convertView, parent);

        View ListItemView = convertView;
        if(ListItemView == null){
            ListItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        }

        Word currentWord = getItem(position);

        TextView nkayiTextView = (TextView) ListItemView.findViewById(R.id.nkanyi_text_view);

        nkayiTextView.setText(currentWord.getIgboTranslation());


        TextView defaultTextView = (TextView) ListItemView.findViewById(R.id.default_text_view);

        defaultTextView.setText(currentWord.getDefaultTranslation());

        return  ListItemView;
    }
}
