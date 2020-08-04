package hub.music.charts.track.service;

import hub.music.charts.track.model.Track;

public class TrackUtil {

    public static Track mapToTrack(String line) {
        String[] p = line.split(";");
        return new Track(p[0], p[1], p[2], getTotalEuroAmount(Integer.parseInt(p[3]), Double.parseDouble(p[4]), p[5]));
    }

    private static double getTotalEuroAmount(int units, double amount, String currency) {

        if("USD".equalsIgnoreCase(currency)) {
            return units * amount * 0.85;
        }else if("GBP".equalsIgnoreCase(currency)) {
            return units * amount * 1.11;
        }else {
            return units * amount;
        }
    }
}
