package com.example.meeting;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;



public class custemcursoradapter extends CursorAdapter {

	public custemcursoradapter(Context context, Cursor c) {
		super(context, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		 TextView textViewPersonName = (TextView) view.findViewById(R.id.tv_person_name);
	        textViewPersonName.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));

	        TextView textViewPersonPIN = (TextView) view.findViewById(R.id.tv_person_pin);
	        textViewPersonPIN.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
	        
	        TextView textViewPersonPlace = (TextView) view.findViewById(R.id.tv_person_place);
	        textViewPersonPlace.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));
	       
	       // TextView textViewPersonDate = (TextView) view.findViewById(R.id.tv_person_date);
	      //  textViewPersonDate.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4))));
	        TextView textViewPersonNo = (TextView) view.findViewById(R.id.textView1);
	        textViewPersonNo.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(5))));
		
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.singlerowitem, parent, false);

        return retView;

	}

}
