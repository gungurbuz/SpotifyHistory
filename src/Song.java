public class Song {
	
	private String trackName;
	private String album;
	private String artist;
	private int timesPlayed;
	private long totalPlayedFor;

	/**
	 * @return the album
	 */
	public String getAlbum() {
		return album;
	}
	public String getTrackName() {
		return trackName;
	}
	public long getTotalPlayedFor() {
		return totalPlayedFor;
	}
	public String getArtist() {
		return artist;
	}
	public int getTimesPlayed() {
		return timesPlayed;
	}
	public Song(String trackName, String album, String artist, long playedFor) {
		this.trackName = trackName;
		this.album = album;
		this.artist = artist;
		this.timesPlayed = 1;
		totalPlayedFor = playedFor;
	}
	public void updateSong(long playedFor) {
		timesPlayed = getTimesPlayed() + 1;
		totalPlayedFor = getTotalPlayedFor() + playedFor;
	}
	
}
