package dxauxm.lyrictrial;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/*
 * Author - Usha
 * Purpose - The Credits activity, to give credit to the authors of the game
 */
public class Credits extends Activity {
	
	TextView textArray[] = new TextView[6];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_credits);
		Typeface typeFace = Typeface.createFromAsset(getAssets(),
				"fonts/Segoe-Regular.ttf");
		textArray[0] = (TextView) findViewById(R.id.textView1);
		textArray[1] = (TextView) findViewById(R.id.textView2);
		textArray[2] = (TextView) findViewById(R.id.textView3);
		textArray[3] = (TextView) findViewById(R.id.textView4);
		textArray[4] = (TextView) findViewById(R.id.textView5);
		textArray[5] = (TextView) findViewById(R.id.textView6);
		
		for (int i = 0; i < 6; i++) {
			textArray[i].setTypeface(typeFace);
			if(i%2==0)
				textArray[i].setTextColor(Color.BLUE);
			else
				textArray[i].setTextColor(Color.RED);
		}
//		textArray[1].setTextColor(Color.BLACK);
		textArray[2].setTextColor(Color.BLACK);
		
		textArray[0].startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
		textArray[1].startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
		textArray[2].startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
		textArray[3].startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
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
