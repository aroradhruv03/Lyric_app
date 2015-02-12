package dxauxm.lyrictrial;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/*
* Author : Usha
* Date : 30/Nov/2014
* Purpose : An activity which explains the rules of the game
*/
public class About extends Activity {

	TextView textArray[] = new TextView[4];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		Typeface typeFace = Typeface.createFromAsset(getAssets(),
				"fonts/Segoe-Regular.ttf");
		textArray[0] = (TextView) findViewById(R.id.textView1);
		textArray[1] = (TextView) findViewById(R.id.textView2);
		textArray[2] = (TextView) findViewById(R.id.textView3);
		textArray[3] = (TextView) findViewById(R.id.textView4);

		for (int i = 0; i < 4; i++) {
			textArray[i].setTypeface(typeFace);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
