package hub.music.charts.track.model;

public class Track {

    private final String isrc;
    private final String trackName;
    private final String artistName;
    private final double revenue;

    public Track(String isrc, String trackName, String artistName, double revenue) {
        super();
        this.isrc = isrc;
        this.trackName = trackName;
        this.artistName = artistName;
        this.revenue = revenue;
    }

    public String getIsrc() {
        return isrc;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public double getRevenue() {
        return revenue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(revenue);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((artistName == null) ? 0 : artistName.hashCode());
        result = prime * result + ((isrc == null) ? 0 : isrc.hashCode());
        result = prime * result + ((trackName == null) ? 0 : trackName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Track other = (Track) obj;
        if (Double.doubleToLongBits(revenue) != Double.doubleToLongBits(other.revenue))
            return false;
        if (artistName == null) {
            if (other.artistName != null)
                return false;
        } else if (!artistName.equals(other.artistName))
            return false;
        if (isrc == null) {
            if (other.isrc != null)
                return false;
        } else if (!isrc.equals(other.isrc))
            return false;
        if (trackName == null) {
            return other.trackName == null;
        } else return trackName.equals(other.trackName);
    }

    @Override
    public String toString() {
        return "Track [isrc=" + isrc + ", trackName=" + trackName + ", artistName=" + artistName + ", revenue=" + revenue
                + "]\n";
    }
}
