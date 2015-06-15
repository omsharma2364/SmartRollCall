package com.example.smartrollcall;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Buy_sell extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(com.example.smartrollcall.R.anim.jump_up, R.anim.fade_out);
		setContentView(R.layout.activity_buy_sell);
	}

	
	
}
