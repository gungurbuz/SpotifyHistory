import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MainTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input path to .json file:");
        String path = scanner.nextLine();
        scanner.close();
        path = path.replace("\"", "\\");
        processJsonFile(path);
    }

    private static void processJsonFile(String filePath) {
        JSONParser jsonParser = new JSONParser();
        Map<String, Song> songMap = new HashMap<>();
        Song mostPlayed = null;
        Song mostTimesPlayed = null;
        long maxLong = 0;
        int maxPlays = 0;

        try (FileReader reader = new FileReader(filePath)) {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
            @SuppressWarnings("unchecked")
			Iterator<Object> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                JSONObject jsonObject = (JSONObject) iterator.next();
                String tempTrack = (String) jsonObject.get("master_metadata_track_name");
                if (tempTrack == null) continue;
                String tempAlbum = (String) jsonObject.get("master_metadata_album_album_name");
                String tempArtist = (String) jsonObject.get("master_metadata_album_artist_name");
                long tempLong = (long) jsonObject.get("ms_played");

                if (songMap.containsKey(tempTrack)) {
                    songMap.get(tempTrack).updateSong(tempLong);
                } else {
                    Song newSong = new Song(tempTrack, tempAlbum, tempArtist, tempLong);
                    songMap.put(tempTrack, newSong);
                }
            }

            for (Song song : songMap.values()) {
                if (song.getTotalPlayedFor() > maxLong) {
                    maxLong = song.getTotalPlayedFor();
                    mostPlayed = song;
                }
                int timesPlayed = song.getTimesPlayed();
                if (timesPlayed > maxPlays) {
                    maxPlays = timesPlayed;
                    mostTimesPlayed = song;
                }
            }

            printHighlights(mostPlayed, mostTimesPlayed, maxPlays);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void printHighlights(Song mostPlayed, Song mostTimesPlayed, int maxPlays) {
        if (mostPlayed != null && mostTimesPlayed != null) {
            System.out.println("____________ Highlights ____________");
            double maxTimeSec = mostPlayed.getTotalPlayedFor() / 1000.0;
            double maxTimeMin = maxTimeSec / 60.0;
            double maxTimeHr = maxTimeMin / 60.0;
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
        } else {
            System.out.println("No song data found.");
        }
    }
}
