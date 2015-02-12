package dxauxm.lyrictrial;

public class Song {
	private long song_id;
	private String song_name;
	private String song_hindi;
	private String song_eng;
	
	Song()
	{
		super();
	}
	
	public long getSong_id() {
		return song_id;
	}
	public void setSong_id(long song_id) {
		this.song_id = song_id;
	}
	public String getSong_name() {
		return song_name;
	}
	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}
	
	public String getSong_hindi() {
		return song_hindi;
	}

	public void setSong_hindi(String song_hindi) {
		this.song_hindi = song_hindi;
	}

	public String getSong_eng() {
		return song_eng;
	}

	public void setSong_eng(String song_eng) {
		this.song_eng = song_eng;
	}

	// Will be used by the ArrayAdapter in the ListView
	  @Override
	  public String toString() {
	    return song_name;
	  }
	
}
