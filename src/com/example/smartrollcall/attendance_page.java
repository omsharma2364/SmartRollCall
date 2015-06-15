package com.example.smartrollcall;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class attendance_page extends Activity{

	String course_id_s,course_name_s,attended_s,total_s,percentage_S,name_s;
	TextView name,course,id,total,attended,percentage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.attendance);
		Bundle e = getIntent().getExtras();
		
		name=(TextView) findViewById(R.id.textView6);
		course = (TextView) findViewById(R.id.textView1);
		id = (TextView) findViewById(R.id.textView2);
		total = (TextView) findViewById(R.id.textView4);
		attended = (TextView) findViewById(R.id.textView3);
		percentage = (TextView) findViewById(R.id.textView5);
		
		
		name_s=e.getString("name");
		course_name_s=e.getString("course_name");
		course_id_s=e.getString("course_id");
		attended_s=e.getString("attended");
		total_s=e.getString("total");
		percentage_S=e.getString("percentage");
		
		name.append("\t"+name_s);
		course.setText(course_name_s);
		id.setText(course_id_s);
		attended.setText(attended_s);
		total.setText(total_s);
		percentage.setText(percentage_S);
		
		
	}

	
	
}
