package dxauxm.lyrictrial;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	// For storing all the buttons
	Button buttonArray[] = new Button[6];
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
     // Linking JAVA variables to XML Views
        buttonArray[0] = (Button) findViewById(R.id.bHList);
        buttonArray[1] = (Button) findViewById(R.id.bEList);
        buttonArray[2] = (Button) findViewById(R.id.bMusic);
        buttonArray[3] = (Button) findViewById(R.id.bCredits);
        buttonArray[4] = (Button) findViewById(R.id.bAbout);
        buttonArray[5] = (Button) findViewById(R.id.bExit);

        Typeface typeFace2 = Typeface.createFromAsset(getAssets(),"fonts/Segoe-Regular.ttf");
	
 		// setting all button properties using the 
 		for (int i = 0; i < 6; i++) {
 			buttonArray[i].setTypeface(typeFace2);
 			buttonArray[i].setOnClickListener(this);
		}
        
    }
    
    @Override
	protected void onResume() {
    	super.onResume();

    } // end of onResume()

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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bHList:
			Intent songListActivity = new Intent(this, SongListActivity.class);
			startActivity(songListActivity);
			break;
		case R.id.bEList:
			break;
		case R.id.bMusic:
			break;
		case R.id.bCredits:
			Intent about = new Intent(this, Credits.class);
			startActivity(about);
			break;
		case R.id.bAbout:
			Intent help = new Intent(this, About.class);
			startActivity(help);
			break;
		case R.id.bExit:
			dialogBox();
			break;
		default:
		}
	}
	
	/*
	 * Author - 
	 * Purpose - Exit Dialog Box
	 */
	public void dialogBox()
	{
		Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/Segoe-UI-Symbol.ttf");
  		// custom dialog
  		final Dialog dialog = new Dialog(this);
  		dialog.setContentView(R.layout.exit_confirmation);
  		dialog.setTitle("Are you sure you want to exit the app?");
  		
  		// set the custom dialog components - text and button, image set in xml
  		TextView text = (TextView) dialog.findViewById(R.id.text);
  		text.setTypeface(typeFace);

  		Button dialogBtnYes = (Button) dialog.findViewById(R.id.dialogBtnYes);
  		dialogBtnYes.setTypeface(typeFace);
  		// if button is clicked, close the custom dialog
  		dialogBtnYes.setOnClickListener(new OnClickListener() {
  			@Override
  			public void onClick(View v) {
  				Log.i("Exit, Yes pressed :", "Will exit the app");
  				dialog.dismiss();
  				// we cancel the dialog so that the window is not leaked
  				dialog.cancel();
  				// close the activity
  				finish();
  			}
  		});
  		
  		Button dialogBtnNo = (Button) dialog.findViewById(R.id.dialogBtnNo);
  		dialogBtnNo.setTypeface(typeFace);
  		// if button is clicked, close the custom dialog
  		dialogBtnNo.setOnClickListener(new OnClickListener() {
  			@Override
  			public void onClick(View v) {
  				Log.i("Exit, No pressed :", "Will not exit");
  				dialog.cancel();
  				dialog.dismiss();
  			}
  		});
  		dialog.show();
	}
    
}
