package dxauxm.lyrictrial;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

	/*
	* Author : Dhruv
	* Date : 31/Oct/2014
	* Purpose : Custom ArrayAdapter for Person List.
	* This method overrides the public View getView Method..
	* This method can display an icon, Name_text and then Phone_Text as a list 
	*/
	public class PersonAdaptor extends ArrayAdapter<SongList> {

		Context context; 
		int layoutResourceId;    
		List<SongList> songList = null;
		Typeface fontHeading;
		Typeface fontText;
		String movieName, songName;
		
		// Constructor
		// Context - we can pass the reference of the activity in which we will use our class
		// resource id - of the layout file we want to use for displaying each ListView item. eg: listview_item_row.xml
		// Person[] - array of Person class objects that will be used by the Adapter to display data. 
		public PersonAdaptor(Context context, int layoutResourceId, List<SongList> list, Typeface fontHeading, Typeface fontText) {
		    super(context, layoutResourceId, list);
		    this.layoutResourceId = layoutResourceId;
		    this.context = context;
		    this.songList = list;
		    this.fontHeading = fontHeading;
			this.fontText = fontText;
		}
		
		//  Method will be called for every item in the ListView to create views with their properties set as we want.
		@Override
	    public View getView(int position, View convertView, ViewGroup parent) {
			
	        View row = convertView;
	        PersonHolder holder = null;
	        
	        if(row == null)
	        {
	        	// Uses the Android built in Layout Inflater to inflate (parse) the xml layout file. 
	            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	            row = inflater.inflate(layoutResourceId, parent, false);
	            
	            holder = new PersonHolder();
//	            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
	            holder.txtMovieName = (TextView)row.findViewById(R.id.txtTitle);
	            holder.txtsongName = (TextView)row.findViewById(R.id.txt_Phone);
	            
	            holder.txtMovieName.setId(position);
            	holder.txtMovieName.setTypeface(fontText);
            	holder.txtsongName.setTypeface(fontText);
        	  	
	            	
	            row.setTag(holder);
	        }
	        else
	        {
	            holder = (PersonHolder)row.getTag();
	        }
	        
	        movieName = songList.get(position).getMovieName();
	        movieName = movieName.replace("_", " ");
	        
	        songName = songList.get(position).getSongName();
	        songName = songName.replace("_", " ");
	        
	        holder.txtMovieName.setText( movieName+" :-> " );
	        holder.txtsongName.setText( songName );
	        
	        return row;
	    }
	    
	    static class PersonHolder
	    {
//	        ImageView imgIcon;
	        TextView txtMovieName;
	        TextView txtsongName;
	    }
	}// end of class
