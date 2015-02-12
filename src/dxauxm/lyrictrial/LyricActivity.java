package dxauxm.lyrictrial;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

public class LyricActivity extends Activity {

	TextView txtVideoLink, txtVideo, lyricsHeading ;
	TextView textArray[] = new TextView[3];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lyric);
		
		Bundle bundle = getIntent().getExtras();
	    String songId = bundle.getString("songId");
	    String songName = bundle.getString("songName");
	    String videoLink = bundle.getString("videoLink");
	    
	    Typeface typeFace2 = Typeface.createFromAsset(getAssets(),"fonts/Segoe-Regular.ttf");
		
	    lyricsHeading = (TextView) findViewById(R.id.lyricsHeading);
	    txtVideo = (TextView) findViewById(R.id.video);
		txtVideoLink = (TextView) findViewById(R.id.videoLink);
		txtVideoLink.setText(videoLink);
//		txtVideoLink.setMovementMethod(LinkMovementMethod.getInstance());
		
		textArray[0] = lyricsHeading;
		textArray[1] = txtVideo;
		textArray[2] = txtVideoLink;
		
		// setting all button properties using the 
 		for (int i = 0; i < 1; i++) {
 			textArray[i].setTypeface(typeFace2);
		}
		
 		// Display the lyrics as per the Song ID
		webView(songId);
       
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lyric, menu);
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
	
//	public void checkLyrics(String songId)
//	{
//		
//		switch(songId)
//		{
//		case "kahin_toh" :
//			txtRawResource.setText( FileIO.readRawTextFile(this, R.raw.kahin_toh) );
//		}
//	}
	
	public void webView(String songId)
	{
		WebView urlWebView = (WebView)findViewById(R.id.webView1);
		urlWebView.setWebViewClient(new AppWebViewClients());
//		urlWebView.getSettings().setJavaScriptEnabled(false);
		urlWebView.getSettings().setUseWideViewPort(true);
		
		urlWebView.loadUrl("file:///android_asset/"+songId+".html");
	}
}
