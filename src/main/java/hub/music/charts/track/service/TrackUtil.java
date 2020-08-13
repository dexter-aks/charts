package hub.music.charts.track.service;

import hub.music.charts.track.configuration.Currency;
import hub.music.charts.track.model.Track;

public class TrackUtil {

    public static Track mapToTrack(String line) {
        String[] fields = line.split(";");
        double rate = Currency.getRate(fields[5]);
        double totalAmount = Integer.parseInt(fields[3]) * Double.parseDouble(fields[4]) * rate ;
        return new Track(fields[0], fields[1], fields[2], totalAmount);
    }
}
