package dxauxm.lyrictrial;

import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

/*
 * Author - Usha
 * Purpose - The high score activity, to show the top ten high score
 */
public class SongListActivity extends Activity {

//	private final Context context = this;
	private ListView listView1;
	private TextView textHeading,textName, textSongList;
	
	// to count the no of clicks to reverse the sort 
	private static int noOfClicksMovieName=0, noOFClicksSongName=0;
	
	// Custom ArrayAdapter for Person List
	PersonAdaptor adapter;
	
	// read list form the disk
	List<SongList> songLists =null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_song_list);
		
		songLists = FileIO.readRawSongList(this, R.raw.songlist);
		
		// Creating path for file and directory
//        createPath(); // if file and path don't exist they're created
		
		Typeface typeFace2=Typeface.createFromAsset(getAssets(),"fonts/Segoe-UI-Symbol.ttf");
	    textHeading = (TextView)findViewById(R.id.textHeading);
	    textName = (TextView)findViewById(R.id.textName);
	    textSongList = (TextView)findViewById(R.id.textTimeTaken);
	    textHeading.setTypeface(typeFace2);
	    textName.setTypeface(typeFace2);
	    textSongList.setTypeface(typeFace2);
	}

	/*
	* Author : Dhruv
	* Date : 30/Nov/2014
	* Purpose : On Resume will fire the table redraw, and sets the onClick listener for the name and time text heading
	*/
    @Override
	protected void onResume() {
    	super.onResume();

    	// Initialize to create the table.
		makeTable();

		// To refresh the List in case a data has been added or removed
		adapter.notifyDataSetChanged();
		
		// When Name is clicked sort by First name, toggles the sort from A-Z and from Z-A
		textName.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				++noOfClicksMovieName;
				// calls the sorting function
				sortListByName();
			}
		});
		
		// When Phone No is clicked sort by First name, toggles the sort from A-z and from Z-A
		textSongList.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				++noOFClicksSongName;
				// calls the sorting function
				sortListByTimeTaken();
			}
		});
    } // end of onResume()
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.song_list, menu);
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
	
    /*
	* Author : Dhruv
	* Date : 30/Nov/2014
	* Purpose : Creates a custom ArrayAdaptor List
	*/
    public boolean makeTable()
    {
    	// set the sort count to 0
    	noOfClicksMovieName=0;
    	noOFClicksSongName=0;
    	
    	//For setting the font
    	Typeface fontHeading=Typeface.createFromAsset(getAssets(),"fonts/Segoe-UI-Symbol.ttf");
    	Typeface fontBody=Typeface.createFromAsset(getAssets(),"fonts/Segoe-Regular.ttf");
    	
//    	for(SongList sg : songLists)
//        {
//        	System.out.println(sg.getMovieName()+" "+sg.getSongName()+" "+sg.getSongIdentifier());
//        }
//    	
        
    	// Call the Person Adaptor, Person Adaptor is our Customized ArrayAdaptor!
    	adapter = new PersonAdaptor(this, R.layout.listview_item_row, songLists, fontHeading, fontBody);
        
    	listView1 = (ListView)findViewById(R.id.listView1);
             
        listView1.setAdapter(adapter);
        
        // set onTouch or onClick lister
        listView1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
                
            	// Call the function to launch the correct intent
            	loadLyricActivity(position);
            }
        });
        
        //register for onLoongClick give context
        registerForContextMenu(listView1);  
        
    	return true;
    }
    
    /*
	* Author : Dhruv
	* Date : 30/Nov/2014
	* Purpose : Sorts the list by Name, both form A-Z and from Z-A
	*/
    private void sortListByName()
    {
    	adapter.sort(new Comparator<SongList>() {
    	    @Override
    	    public int compare(SongList lhs, SongList rhs) {
    	    	if(noOfClicksMovieName%2==0)
    	    		return rhs.getMovieName().compareTo(lhs.getMovieName());
    	    	else
    	    		return lhs.getMovieName().compareTo(rhs.getMovieName());
    	    }
    	});
    }
    
    /*
	* Author : Dhruv
	* Date : 30/Nov/2014
	* Purpose : Sorts the list by Phone No, both from 0-9 and from 9-0
	*/
    private void sortListByTimeTaken()
    {
    	adapter.sort(new Comparator<SongList>() {
    	    @Override
    	    public int compare(SongList lhs, SongList rhs) {
    	    	if(noOFClicksSongName%2==0)
    	    		return rhs.getSongName().compareTo(lhs.getSongName());
    	    	else
    	    		return lhs.getSongName().compareTo(rhs.getSongName());
    	    }
    	});
    }
    
    /*
	* Author : Dhruv
	* Date : 30/Nov/2014
	* Purpose : Sorts the list in asacending by Time taken
	*/
    private void sortListByTimeTakenLowest()
    {
    	adapter.sort(new Comparator<SongList>() {
    	    @Override
    	    public int compare(SongList lhs, SongList rhs) {
    	    		return lhs.getSongName().compareTo(rhs.getSongName());
    	    }
    	});
    }
    
    /*
     * Author - Usha M
     * Purpose - Used for creating the path. A stub function.
     */
//    public boolean createPath()
//    {
//    	FileIO fileIO = new FileIO();
//    	fileIO.createPath( Environment.getExternalStorageDirectory() );
//    	Log.i("Create Path: " , Environment.getExternalStorageDirectory().toString() );
//    	return true;
//    }
    
    public void loadLyricActivity(int position)
    {
    	SongList sl = songLists.get( position );
    	
        Intent editintent = new Intent(SongListActivity.this, LyricActivity.class);
         Bundle bundle = new Bundle();
         bundle.putString("songName", sl.getSongName() );
         bundle.putString("songId", sl.getSongIdentifier() );
         bundle.putString("videoLink", sl.getVideoLink() );
         editintent.putExtras(bundle);
         startActivity(editintent);
    
    }
    
 // Create a context menu on long click on list
 	@Override  
     public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
     super.onCreateContextMenu(menu, v, menuInfo);  
         menu.setHeaderTitle("Quick Tasks");  
         menu.add(0, v.getId(), 0, "Show Lyrics");
         menu.add(0, v.getId(), 0, "Show Video");
     }  
   
 	/*
 	* Author : Dhruv
 	* Date : 31/Oct/2014
 	* Purpose : Define the context menu functions
 	*/
     @Override  
     public boolean onContextItemSelected(MenuItem item) {  
     	
         if(item.getTitle()=="Show Lyrics"){
         		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
         		loadLyricActivity( (int) info.id );
         	} 
         if(item.getTitle()=="Show Video"){
        	 AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
      		loadLyricActivity( (int) info.id );
      		
      		
      	}
         else {
         		return false;
         	}  
     return true;  
     }  
    
}
