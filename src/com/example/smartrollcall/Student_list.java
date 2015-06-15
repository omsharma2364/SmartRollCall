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
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Student_list extends Activity implements OnClickListener{

	String course;
	TableLayout tbl;
	TableRow temp1;
	String search_stud[],search_add[];
	String students[];
	CheckBox cb,temp;
	int tot_stud;
	int i=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_list);
		tbl=(TableLayout)findViewById(R.id.table1);
		Bundle e=getIntent().getExtras();
		course=e.getString("course");
		HttpResponse response = null;
		//HttpGet request;
		
		HttpPost request =  new HttpPost("http://192.168.137.2:4485/smartrollcall/takeattendance"); 
		
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	        nameValuePairs.add(new BasicNameValuePair("course",course));
	   
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
						students[j]=out;
						j++;
					}
					tot_stud=j;
					// Toast.makeText(getApplicationContext(), i, Toast.LENGTH_LONG).show();
				} catch (IllegalStateException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 
				int i=0;
				for(;i<tot_stud;i++)
				{
					cb = new CheckBox(this);
					temp1=new TableRow(this);
				    cb.setText("Rollno " + students[i]);
				    cb.setId(i);
				    temp1.addView(cb);
				    tbl.addView(temp1);
				}
				Button save_continue=new Button(this);
				save_continue.setText("Save And Continue");
				save_continue.setId(i + 10);
				temp1=new TableRow(this);
				temp1.addView(save_continue);
				tbl.addView(temp1);
		
	}
	public final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // If it's already paired, skip it, because it's been listed already
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) 
                {
                	search_stud[i]=device.getName();
                	search_add[i]=device.getAddress();
                	int flag=0;
                	int j=i;
                	for(int l=0;l<j;l++)
            		{
            			if(search_add[j].equals(search_add[l]))
            				flag=1;
            		}
                	for(int k=0;k<tot_stud;k++)
                	{
                		
                	if(search_stud[j].equals(students[k])&&flag==0)
                	{
                		temp.setId(j);
                		temp.setChecked(true);
                	}
                	}                	
               }
            }
        }
    };
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
