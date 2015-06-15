package com.example.smartrollcall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class teacher_option extends Activity implements OnClickListener{
	TextView Name,Mail,Dept,Contact;
	String Name_s,Mail_s,Dept_s,Contact_s,Password_s,uname;
	Button attendance,update;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(com.example.smartrollcall.R.layout.fragment_teacher_option);
		Name=(TextView)findViewById(R.id.textView1);
		Mail=(TextView)findViewById(R.id.textView2);
		Dept=(TextView)findViewById(R.id.textView3);
		Contact=(TextView)findViewById(R.id.textView5);
		Bundle e=getIntent().getExtras();
		Name_s=e.getString("name");
		Mail_s=e.getString("mail");
		Dept_s=e.getString("dept");
		uname=e.getString("username");
		Contact_s=e.getString("contact");
		Password_s=e.getString("password");
		Name.append(Name_s);
		Mail.setText(Mail_s);
		Dept.setText(Dept_s);
		Contact.setText(Contact_s);
		
	}
	
	public void onclick_update(View v)
	{
		
		Intent i = new Intent(this,Teacher_update_info.class);
		i.putExtra("name", Name_s);
		i.putExtra("mail", Mail_s);
		i.putExtra("dept", Dept_s);
		i.putExtra("contact", Contact_s);
		i.putExtra("password", Password_s);
		i.putExtra("username",uname);
		startActivity(i);
		
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.getId()==R.id.button1)
		{
			Intent i=new Intent(this,Teacher_att.class);
			i.putExtra("username", uname);
			startActivity(i);
		}
	}
	

}
