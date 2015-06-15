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
import android.widget.TextView;
import android.widget.Toast;

public class stud_att extends Activity implements OnClickListener,OnItemSelectedListener{
	Spinner s;
	Button mark,check;
	TextView att_mark;
	String uname,course_a,course_b,marked,bname;
	String sel_sub="Nothing";
	String arr[]={"Sub_1","Sub_2"};
	  private static final int REQUEST_ENABLE_BT = 1;
	    private BluetoothAdapter mBluetoothAdapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stud_att);
		s = (Spinner) findViewById(R.id.spinner1);
		mark = (Button) findViewById(R.id.button1);
		mark.setVisibility(0);
		check = (Button) findViewById(R.id.button2);
		check.setVisibility(0);
		att_mark=(TextView)findViewById(R.id.textView1);
		Bundle e = getIntent().getExtras();
		
		course_a=e.getString("course");
		uname=e.getString("username");
		int i=course_a.length();
		char a = course_a.charAt(i-1);
		int op = Integer.parseInt(a+"");
		op++;
		Integer o=new Integer(op);
		course_b=course_a.substring(0, i-1).concat(o.toString());
		arr[0]=course_a;
		arr[1]=course_b;
		ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,arr);
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
		s.setAdapter(adapter);
		
         
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.getId()==R.id.button1)
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
         bname=mBluetoothAdapter.getName();
         mBluetoothAdapter.setName(uname);
        
         Intent d=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
         d.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
         startActivity(d);
         check.setVisibility(1);
		}
		else
		{
			HttpResponse response = null;
			HttpPost request =  new HttpPost("http://192.168.137.2:4485/smartrollcall/????"); 
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	            nameValuePairs.add(new BasicNameValuePair("username", uname));
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
			 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				String out = "";
				while((out=rd.readLine())!=null)
				{
					marked=out;
				}
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				 Toast.makeText(getApplicationContext(), "Oops!! Something went wrong.",Toast.LENGTH_SHORT).show();
		           
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(marked.equals("yes"))
			{
				att_mark.setText("Congrats,Your Attendance has been Marked.");
				mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
				mBluetoothAdapter.setName(bname);
				if (mBluetoothAdapter.isEnabled()) 
				{
				    mBluetoothAdapter.disable(); 
				}
			}
			else
			{
				att_mark.setText("Sorry,but Your attendance has not been marked yet... Keep Checking!! ");
			}
		}
         
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		mark.setVisibility(1);
		sel_sub=arr[arg2];
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
