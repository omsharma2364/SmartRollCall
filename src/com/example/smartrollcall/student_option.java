package com.example.smartrollcall;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class student_option extends Activity{
	TextView Name,Mail,Dept,Contact,CourseId;
	String Name_s,Mail_s,Dept_s,Contact_s,Password_s,uname,Course_s;
	Button attendance,update;
	String valid[]={null,"","","",""};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(com.example.smartrollcall.R.layout.tried);
		Name=(TextView)findViewById(R.id.textView1);
		Mail=(TextView)findViewById(R.id.textView2);
		Dept=(TextView)findViewById(R.id.textView3);
		CourseId=(TextView)findViewById(R.id.textView6);
		Contact=(TextView)findViewById(R.id.textView5);
		Bundle e=getIntent().getExtras();
		Name_s=e.getString("name");
		Mail_s=e.getString("mail");
		
		Dept_s=e.getString("dept");
		Course_s=e.getString("courseid");
		uname=e.getString("username");
		Contact_s=e.getString("contact");
		Password_s=e.getString("password");
		
		Name.append(Name_s);
		Mail.setText(Mail_s);
		Dept.setText(Dept_s);
		Contact.setText(Contact_s);
		CourseId.setText(Course_s);
	}
	
	public void onclick_update(View v)
	{
		
		Intent i = new Intent(this,student_update.class);
		i.putExtra("name", Name_s);
		i.putExtra("mail", Mail_s);
		i.putExtra("dept", Dept_s);
		i.putExtra("course", Course_s);
		i.putExtra("contact", Contact_s);
		i.putExtra("password", Password_s);
		i.putExtra("username",uname);
		startActivity(i);
		
		//Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
		
	}
	
	public void onclick_viewatt(View v)
	{
		
		
		HttpResponse response = null;
	//HttpGet request;
	
	HttpPost request =  new HttpPost("http://192.168.137.2:4485/smartrollcall/viewattendance"); 
	
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("roll", uname));
   
	try {
		request.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
	} catch (Exception e3) {
		// TODO Auto-generated catch block
		Toast.makeText(getApplicationContext(), "yahan", Toast.LENGTH_LONG).show();
		e3.printStackTrace();
	}
	
	
	 try	{	
		 HttpParams params = new BasicHttpParams();
		 params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
		         HttpVersion.HTTP_1_1);
		 
		// HttpClient httpclient = new DefaultHttpClient(params);
		//request = new HttpPost("http://10.0.2.2/server.php");
		HttpClient client = new DefaultHttpClient(params);
	    response = client.execute(request);
	    
	 
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
			try {
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				String out = "";
				int j=0;
				while((out=rd.readLine())!=null)
				{
					valid[j]=out;
					   //Toast.makeText(getActivity().getApplicationContext(), valid+"op", Toast.LENGTH_LONG).show();

					j++;
					//tv.setText(out);
				}

				// Toast.makeText(getApplicationContext(), i, Toast.LENGTH_LONG).show();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Intent i = new Intent(this,attendance_page.class);
			i.putExtra("course_id", valid[0]);
			i.putExtra("course_name", valid[1]);
			i.putExtra("attended", valid[2]);
			i.putExtra("total", valid[3]);
			i.putExtra("percentage", valid[4]);
			i.putExtra("name", Name_s);
			startActivity(i);
	}
}
