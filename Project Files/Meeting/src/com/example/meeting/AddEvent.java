package com.example.meeting;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class AddEvent extends Activity {
	private custemcursoradapter customAdapter;
    private PersonDatabaseHelper databaseHelper;
    private static final int ENTER_DATA_REQUEST_CODE = 1;
    private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
		 databaseHelper = new PersonDatabaseHelper(this);

	        listView = (ListView) findViewById(R.id.list_data);
	        listView.setOnItemClickListener( new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View selectedView, int arg2,long arg3) {
					//TextView  textAccountId = (TextView) selectedView.findViewById(R.id.textAccountId);
					//Log.d("Accounts", "Selected Account Id : " + textAccountId.getText().toString());
					//Intent intent = new Intent(MainActivity.this, GoogleMap.class);
				//	intent.putExtra("accountid", textAccountId.getText().toString());
					//startActivity(intent);
				}
			});
	        	
	        new Handler().post(new Runnable() {
	            public void run() {
	                customAdapter = new custemcursoradapter(getBaseContext(), databaseHelper.getAllData());
	                listView.setAdapter(customAdapter);
	            }
	        });
	        

		

	}

	 public void onClickEnterData(View btnAdd) {

	        startActivityForResult(new Intent(this,Enter2.class), ENTER_DATA_REQUEST_CODE);

	    }

	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	        super.onActivityResult(requestCode, resultCode, data);

	        if (requestCode == ENTER_DATA_REQUEST_CODE && resultCode == RESULT_OK) {

	            databaseHelper.insertData(data.getExtras().getString("tag_person_name"), data.getExtras().getString("tag_person_pin"), data.getExtras().getString("tag_person_pin1"), data.getExtras().getString("tag_person_pin2"), data.getExtras().getString("tag_person_pin3"));

	            customAdapter.changeCursor(databaseHelper.getAllData());
	        }
	    }


}
