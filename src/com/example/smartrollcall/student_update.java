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
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class student_update extends Activity {
	
	EditText Name,Mail,Contact;
	TextView depar,course_id;
	String Name_s,Mail_s,Dept_s,Contact_s,Password_s,uname,i,course_s;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_update);

		Bundle e = getIntent().getExtras();
		Name_s=e.getString("name");
		Mail_s=e.getString("mail");
		Dept_s=e.getString("dept");
		Contact_s=e.getString("contact");
		Password_s=e.getString("password");
		uname = e.getString("username");
		course_s= e.getString("course");
		Name = (EditText) findViewById(R.id.editText1);
		Mail = (EditText) findViewById(R.id.editText2);
		Contact = (EditText) findViewById(R.id.editText3);
		depar = (TextView) findViewById(R.id.textView3);
		course_id = (TextView) findViewById(R.id.textView7);
		
		Name.setText(Name_s);
		Mail.setText(Mail_s);
		Contact.setText(Contact_s);
		depar.setText(Dept_s);
		course_id.setText(course_s);
	
		
		
	}
	
	public void onclick_confirm(View v)
	{
		Dialog d = new Dialog(this);
		d.setTitle("CONFIRM PASSWORD"); 
		TextView tv = new TextView(this);
		tv.setText("Success");
		d.setContentView(tv);
		d.show();
		HttpResponse response = null;
		//HttpGet request;
		String name_a,mail_a,contact_a;
		name_a=Name.getText().toString();
		mail_a=Mail.getText().toString();
		contact_a=Contact.getText().toString();
	
		HttpPost request =  new HttpPost("http://192.168.137.2:4485/smartrollcall/studentupdate"); 
		
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("name", name_a));
            nameValuePairs.add(new BasicNameValuePair("email", mail_a));
         //  nameValuePairs.add(new BasicNameValuePair("email", Mail));
            nameValuePairs.add(new BasicNameValuePair("phone", contact_a));
            nameValuePairs.add(new BasicNameValuePair("id", uname));
         //   nameValuePairs.add(new BasicNameValuePair("pwd", Password_s));
            
	
	
		
		try {
		//	requesta.setURI(new UrlEncodedFormEntity(list,"UTF-8"));
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
					

					while((out=rd.readLine())!=null)
					{
						i=out;
						
						  

						
						//tv.setText(out);
					}
					 Toast.makeText(getApplicationContext(), i, Toast.LENGTH_LONG).show();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
		
		
	}

}