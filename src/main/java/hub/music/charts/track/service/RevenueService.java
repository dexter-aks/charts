package hub.music.charts.track.service;

import hub.music.charts.track.model.Track;

import java.util.List;

public interface RevenueService {
    List<Track> getMaxRevenueTracks(int limit);
}
