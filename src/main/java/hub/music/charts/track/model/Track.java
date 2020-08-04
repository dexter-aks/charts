package hub.music.charts.track.model;

public class Track {

    private final String isrc;
    private final String trackName;
    private final String artistName;
    private final double totalAmount;
    private final String currency;

    public Track(String isrc, String trackName, String artistName, double totalAmount) {
        super();
        this.isrc = isrc;
        this.trackName = trackName;
        this.artistName = artistName;
        this.totalAmount = totalAmount;
        currency = "EURO";
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getCurrency() {return currency; }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(totalAmount);
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
        if (Double.doubleToLongBits(totalAmount) != Double.doubleToLongBits(other.totalAmount))
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
        return "Track [isrc=" + isrc + ", trackName=" + trackName + ", artistName=" + artistName + ", revenue=" + totalAmount
                + "]\n";
    }
}
