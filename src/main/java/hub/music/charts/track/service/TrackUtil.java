package hub.music.charts.track.service;

import hub.music.charts.track.configuration.Currency;
import hub.music.charts.track.model.Track;

public class TrackUtil {

    public static Track mapToTrack(String line) {
        String[] p = line.split(";");
        double rate = Currency.exchangeRate.get(p[5]);
        double totalAmount = Integer.parseInt(p[3]) * Double.parseDouble(p[4]) * rate ;
        return new Track(p[0], p[1], p[2], totalAmount);
    }
}
