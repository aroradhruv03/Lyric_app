package dxauxm.lyrictrial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

/*
 * Author - Usha, Dhruv
 * Date : 30/Oct/2014
 * Purpose : Function creates the directory and file at the path if they don't exist, and insert dummy data inside the file.
 *  Debugging Mechanism has been addded, contains logging at all most all levels
 */
public class FileIO {
	
	
	public static String readRawTextFile(Context context, int resId)
    {
        InputStream inputStream = context.getResources().openRawResource(resId);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        StringBuilder text = new StringBuilder();

        try {
            while (( line = buffreader.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            return null;
        }
        return text.toString();
    }
	
	public static List<SongList> readRawSongList(Context context, int resId)
    {
		List<SongList> songList = new ArrayList<SongList>();
        
		try
		{
			InputStream inputStream = context.getResources().openRawResource(resId);

	        InputStreamReader inputreader = new InputStreamReader(inputStream);
	        BufferedReader reader = new BufferedReader(inputreader);
	        String line;
	        
//			FileReader fileReader = new FileReader(file);
//			BufferedReader reader = new BufferedReader(fileReader);
//			String line;
			
			while((line = reader.readLine())!=null)
			{
				String delimited[] = null;
				// Split each line into it's constituent properties, then the result is stored in a string array.
				delimited = line.split("\\|");
				// This will make sure to take only the correct data in the correct format
				if(delimited.length == 4 )
				{
					//	String array is used to initialize a Person object.
					SongList p1 = new SongList( delimited[0], delimited[1], delimited[2], delimited[3] );
					// Each Person object is stored in a List of Person
					songList.add(p1);
				}
			}
			// close and flush connections
			reader.close();
			inputreader.close();
			Log.i("File read successfully", "");
			return songList;
		}
		catch(Exception e)
		{
			Log.e("File not Read : ", e.getMessage());
			return null;	
		}
    }
	
}// end of Class FileIO
