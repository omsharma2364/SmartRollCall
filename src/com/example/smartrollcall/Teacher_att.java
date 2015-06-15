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
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Teacher_att extends Activity implements OnClickListener,OnItemSelectedListener{
	Spinner course;
	Button take;
	String arr[]={"Null","Null","Null","Null","Null"};
	String uname,sel_course="";
	 private static final int REQUEST_ENABLE_BT = 1;
	    private BluetoothAdapter mBluetoothAdapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.example.smartrollcall.R.layout.activity_teacher_att);
		course=(Spinner)findViewById(R.id.spinner1);
		take=(Button)findViewById(R.id.button1);
		Bundle e=getIntent().getExtras();
		uname=e.getString("username");
		HttpResponse response = null;
		//HttpGet request;
		
		HttpPost request =  new HttpPost("http://192.168.137.2:4485/smartrollcall/getcourse"); 
		
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	        nameValuePairs.add(new BasicNameValuePair("teacher_id", uname));
	   
		try {
			request.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}		
		 try	{	
			 HttpParams params = new BasicHttpParams();
			 params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
			         HttpVersion.HTTP_1_1);
			HttpClient client = new DefaultHttpClient(params);
		    response = client.execute(request);
		    
		 
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
				try {
					BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
					String out = "";
					int j=0;
					while((out=rd.readLine())!=null)
					{
						arr[j]=out;
						j++;
					}

					// Toast.makeText(getApplicationContext(), i, Toast.LENGTH_LONG).show();
				} catch (IllegalStateException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,arr);
				adapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
				course.setAdapter(adapter);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.getId()==R.id.button1)
		{
			Intent i=new Intent(this,Student_list.class);
			if(sel_course.equals("Null"))
			{
				Toast.makeText(getApplicationContext(),"Please Select valid Course!!", Toast.LENGTH_SHORT).show();
			}
			else
			{
				IntentFilter filter=new IntentFilter(BluetoothDevice.ACTION_FOUND);
				mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
				 if (mBluetoothAdapter == null) 
		         {
		               Toast.makeText(this, "Bluetooth is not present", Toast.LENGTH_LONG).show();
		               finish();
		               return;
		         }
		         else if (!mBluetoothAdapter.isEnabled()) 
		         {
		               Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		               startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		         }
		         Toast.makeText(this, "Bluetooth is on", Toast.LENGTH_LONG).show();
		         Intent d=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		         d.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 1200);
		         startActivity(d);
				
				i.putExtra("course",sel_course);
				startActivity(i);
			}
		}
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		sel_course=arr[arg2];
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
