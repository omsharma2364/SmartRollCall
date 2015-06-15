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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class fragmentb extends Fragment implements OnClickListener{

	String username;
	String var="hhh";
	String password;
	String valid[]={null,"","","","",""};
	EditText user,pass;
	TextView date;
	ImageButton login,contact,buy_sell;
	Time today;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		View v = inflater.inflate(R.layout.pageb, container,false);
		user=(EditText)v.findViewById(R.id.editText1);
		pass=(EditText)v.findViewById(R.id.editText2);
		login=(ImageButton)v.findViewById(R.id.button1);
		buy_sell=(ImageButton)v.findViewById(R.id.imageButton1);
		date=(TextView) v.findViewById(R.id.textView1);
		login.setOnClickListener(this);
		buy_sell.setOnClickListener(this);
		today = new Time(Time.getCurrentTimezone());
		today.setToNow();
		date.setText(today.monthDay + "/");
		date.append(today.month+1 + ""); 
	
		// TODO Auto-generated method stub
		
		return v;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		if(arg0.getId()==R.id.button1)
		{
			username=user.getText().toString();
			password=pass.getText().toString();
			if(username.trim().equals("")||password.trim().equals(""))
			{
				Toast.makeText(getActivity().getApplicationContext(),"Username or Password Incomplete!!", Toast.LENGTH_SHORT).show();
			}
			else
			{
				HttpResponse response = null;
				//HttpGet request;
				
			
				HttpPost request =  new HttpPost("http://192.168.137.2:4485/smartrollcall/studentlogin"); 
				
				 List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		            nameValuePairs.add(new BasicNameValuePair("username", username));
		            nameValuePairs.add(new BasicNameValuePair("password", password));
			
				//JSONObject obj = new JSONObject();
				//JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {}
				
				try {
				//	requesta.setURI(new UrlEncodedFormEntity(list,"UTF-8"));
					request.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
				} catch (Exception e3) {
					// TODO Auto-generated catch block
					Toast.makeText(getActivity().getApplicationContext(), "yahan", Toast.LENGTH_LONG).show();
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
					int i=0;
					while((out=rd.readLine())!=null)
					{
						valid[i]=out;
						   //Toast.makeText(getActivity().getApplicationContext(), valid+"op", Toast.LENGTH_LONG).show();

						i++;
						//tv.setText(out);
					}
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					 Toast.makeText(getActivity().getApplicationContext(), "Oops!! Something went wrong.",Toast.LENGTH_SHORT).show();
			           
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//new PostDataAsyncTask().execute();
				if(valid[0].trim().equals(""))
				{
					   Toast.makeText(getActivity().getApplicationContext(), "Account Not Found", Toast.LENGTH_LONG).show();
				}
				else if(valid[0].trim().equals("successful"))
				{
	
				Toast.makeText(getActivity().getApplicationContext(), "Account Found", Toast.LENGTH_LONG).show();
				Toast.makeText(getActivity().getApplicationContext(), valid[1]+valid[2]+valid[3], Toast.LENGTH_LONG).show();
					Intent student=new Intent(getActivity().getApplicationContext(),test.class);
					student.putExtra("name", valid[1]);
					student.putExtra("mail", valid[2]);
					student.putExtra("dept", valid[3]);
					student.putExtra("courseid", valid[4]);
					student.putExtra("contact", valid[5]);
					
					student.putExtra("password", password);
					student.putExtra("username", username);
					valid[0]=null;
					startActivity(student);
				}
				else
				{
					//Toast.makeText(getActivity().getApplicationContext(),var, Toast.LENGTH_LONG).show();

					Toast.makeText(getActivity().getApplicationContext(), "Server not Found", Toast.LENGTH_LONG).show();

				}
			}
		}
		else if(arg0.getId()==R.id.button3);
		{
			//Toast.makeText(getActivity().getApplicationContext(), "You Want Something...", Toast.LENGTH_LONG).show();

			Intent e=new Intent(getActivity().getApplicationContext(),Buy_sell.class);
			startActivity(e);			
		}
	}
}