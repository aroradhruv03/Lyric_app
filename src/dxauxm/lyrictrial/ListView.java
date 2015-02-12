package dxauxm.lyrictrial;

import java.util.List;
import java.util.Random;

import org.w3c.dom.Comment;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

public class ListView extends ListActivity {
	
	private SongDataSource dataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		
		dataSource = new SongDataSource(this);
		dataSource.open();
		
		displayList();
		
//		Song song = new Song();
		// create the song db
//		song1.setSong_hindi(song_hindi)
		
		
		
		// save the new comment to the database
	      
	      
		
		List<Song> list = dataSource.getAllSongs();
		
		// use the SimpleCursorAdapter to show the elements in a ListView
		ArrayAdapter<Song> adapter = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1);
		setListAdapter(adapter);
	}
	
	public void onClick(View view)
	{
		ArrayAdapter<Song> listAdapter = (ArrayAdapter<Song>) getListAdapter();
		Song song = null;
		switch (view.getId()) {
	    case R.id.view:
//	    	String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
	    	String[] songNames = new String[] { "Kahin Toh Hogi Woh", "Saibo", "Hoshwalo Ko Khabar Kya" };

			for(int i=0;i<songNames.length;i++)
			{
				song = dataSource.createSongName(songNames[i]);
				listAdapter.add(song);
			}
//	        int nextInt = new Random().nextInt(3);
//	        // save the new comment to the database
//	        comment = datasource.createComment(comments[nextInt]);
//	        adapter.add(comment);
	      break;
	    case R.id.delete:
	      if (getListAdapter().getCount() > 0) {
	    	  song = (Song) getListAdapter().getItem(0);
	        dataSource.deleteSong(song);
	        listAdapter.remove(song);
	      }
	      break;
	    }
	    listAdapter.notifyDataSetChanged();
	}
	
	public void displayList()
	{
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view, menu);
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
