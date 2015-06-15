package com.example.smartrollcall;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.os.Build;

public class MainActivity extends FragmentActivity {

	ViewPager viewp= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        viewp = (ViewPager)findViewById(R.id.pager);
        FragmentManager fm = getSupportFragmentManager();
        viewp.setAdapter(new myadapter(fm));
        

    }

    class myadapter extends FragmentPagerAdapter{

		public myadapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int i) {
			// TODO Auto-generated method stub
			Fragment frag=null;
			if(i==0)
			{
				frag = new fragmenta();
			}
			if(i==1) 
			{
				frag=new fragmentb();
			}
			return frag;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			String tile = new String();
			if(position==1)
			{
				tile = "Teacher";
			}
			else
				tile = "Student";
			return tile;
    	
		
		}
    }
}
