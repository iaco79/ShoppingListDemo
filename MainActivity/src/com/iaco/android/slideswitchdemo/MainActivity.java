/**
 * Shopping List Demo Main activity 
 * 
 * iaco79
 */
package com.iaco.android.slideswitchdemo;

import com.iaco.android.slideswitchdemo.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class MainActivity extends FragmentActivity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		getSupportFragmentManager().beginTransaction().add(
				R.id.ActivityMain_Layout, 
				new ItemListFragment(), 
				"listfragment1"		
				).commit();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
