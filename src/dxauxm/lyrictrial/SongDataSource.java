package dxauxm.lyrictrial;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SongDataSource {
	
	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_NAME };
	
	public SongDataSource(Context context)
	{
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open() throws SQLException
	{
		database = dbHelper.getWritableDatabase();
	}
	
	public void close()
	{
		dbHelper.close();
	}
	
	public Song createSongName(String comment)
	{
		ContentValues values = new ContentValues();
		
		values.put(MySQLiteHelper.COLUMN_NAME, comment);
		long insertId = database.insert(MySQLiteHelper.TABLE_NAME, null, values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME, allColumns, 
				MySQLiteHelper.COLUMN_ID + "=" + insertId, null, null, null, null );
		// database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
		cursor.moveToFirst();
		Song newSong = cursorToSong(cursor);
		cursor.close();
		return newSong;
	}

	private Song cursorToSong(Cursor cursor) {
		Song newSong = new Song();
		newSong.setSong_id(cursor.getLong(0));
		newSong.setSong_name(cursor.getString(1));
		return newSong;
	}
	
	public List<Song> getAllSongs()
	{
		List<Song> listSongs = new ArrayList<Song>();
		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME, allColumns,null, null, null,null,null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast())
		{
			Song song = cursorToSong(cursor);
			listSongs.add(song);
			cursor.moveToNext();
		}
		
		return listSongs;
	}
	
	public void deleteSong(Song song) {
	    long id = song.getSong_id();
	    System.out.println("Comment deleted with id: " + id);
	    database.delete(MySQLiteHelper.TABLE_NAME, MySQLiteHelper.COLUMN_ID
	        + " = " + id, null);
	  }
}
