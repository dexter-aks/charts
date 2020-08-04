package hub.music.charts.track.model;

import java.util.Comparator;

public class TrackComparator implements Comparator<Track> {

    @Override
    public int compare(Track o1, Track o2) {
        if(o1.getTotalAmount() < o2.getTotalAmount()) return 1;
        else if(o1.getTotalAmount() > o2.getTotalAmount()) return -1;
        return 0;
    }

}
