import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONParser jsonParser = new JSONParser();
		ArrayList<Song> songs = new ArrayList<>();
		Song mostPlayed = null;
		Song mostTimesPlayed = null;
		//ArrayList<Song> highlights = new ArrayList<>();
		long maxLong = 0;
		int maxPlays = 0;
		
		try {
			JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("C:\\Users\\Güney\\eclipse-workspace\\csh\\csh\\history.json"));
			Iterator<Object> iterator = jsonArray.iterator();
			while (iterator.hasNext()) {
				JSONObject jsonObject = (JSONObject) iterator.next();
				String tempTrack = (String) jsonObject.get("master_metadata_track_name");
				if (tempTrack == null)continue;
				String tempAlbum = (String) jsonObject.get("master_metadata_album_album_name");
				String tempArtist = (String) jsonObject.get("master_metadata_album_artist_name");
				long tempLong = (long) jsonObject.get("ms_played");
				if (songs.isEmpty() /*||  tempLong >=30000*/) {
					songs.add(new Song(tempTrack, tempAlbum, tempArtist, tempLong));
				}
				else{
					int i;
					for (i = songs.size() - 1; i >= 0; i--) {
						System.out.println(tempTrack);
					    if (songs.get(i).getTrackName().equals(tempTrack)) {
					        break;
					    }
					System.out.println(i);
					}
					if (i >= 0) {
					    songs.get(i).updateSong(tempLong);
					} else {
					    songs.add(new Song(tempTrack, tempAlbum, tempArtist, tempLong));
					}
				}
				
				
			}
			for (int i = 0; i < songs.size(); i++) {
				if ((songs.get(i)).getTotalPlayedFor()> maxLong) {
					maxLong = songs.get(i).getTotalPlayedFor();
					mostPlayed = songs.get(i);
				}
				if ((songs.get(i)).getTimesPlayed()> maxPlays) {
					mostTimesPlayed = songs.get(i);
				}
			}
			System.out.println("――――――――――― Highlights ―――――――――――");
			double maxTimeSec = mostPlayed.getTotalPlayedFor()/1000.0;
			double maxTimeMin = maxTimeSec/60.0;
			double maxTimeHr = maxTimeMin/60.0;
			System.out.println("Most listened to song by time: ");
			System.out.println("Song Name: " + mostPlayed.getTrackName());
			System.out.println("By: " + mostPlayed.getArtist());
			System.out.println("Playtime(ms): " + mostPlayed.getTotalPlayedFor());
			System.out.println("Playtime(s): " + maxTimeSec);
			System.out.println("Playtime(m): " + maxTimeMin);
			System.out.println("Playtime(hr): " + maxTimeHr);
			System.out.println("Most listened to song by play count: ");
			System.out.println("Song Name: " + mostTimesPlayed.getTrackName());
			System.out.println("By: " + mostTimesPlayed.getArtist());
			System.out.println("Times played: " + maxPlays);
			
		}
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
			}
		catch (IOException e) {
			e.printStackTrace();
			}
		catch (ParseException e) {
			e.printStackTrace();
			}
		
	}

}
