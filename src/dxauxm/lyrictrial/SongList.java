package dxauxm.lyrictrial;


/*
 * Author : Usha
 * Date : 29/Nov/2014
 * Purpose - This is a bean class for Person information. Class is used for storing Person Info. 
 * 
 */
public class SongList implements Comparable<SongList>{

	private String movieName;
	private String songName;
	private String songIdentifier;
	private String videoLink="No_Video_:(";
    
	public SongList(){
        super();
    }

    public SongList(String movieName, String songName, String songIdentifier, String videoLink) {
		super();
		this.movieName = movieName;
		this.songName = songName;
		this.songIdentifier = songIdentifier;
		this.videoLink = videoLink;
	}
    
    
    public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getSongIdentifier() {
		return songIdentifier;
	}

	public void setSongIdentifier(String songIdentifier) {
		this.songIdentifier = songIdentifier;
	}

	public void setHighScore(String timeTaken) {
		this.songIdentifier = timeTaken;
	}

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	// ordering acc to song name
	@Override
	public int compareTo(SongList another) {
		
		String compareSongName = ((SongList) another).getSongName(); 
		//ascending order
		return this.songIdentifier.compareTo(compareSongName);
	}
	
//	public static Comparator<Person> PersonScoreComparator  = new Comparator<Person>() {
//				
//				public int compare(Person lhs, Person rhs) {
//				
//				//ascending order
//				return lhs.getHighScore().compareTo(rhs.getHighScore());
//				
//				//descending order
//				//return fruitName2.compareTo(fruitName1);
//				}
//				
//	};
	
}// end of class