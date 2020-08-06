package hub.music.charts.track.service;

import hub.music.charts.track.configuration.DataFiles;
import hub.music.charts.track.exception.InvalidLimitException;
import hub.music.charts.track.exception.LimitBoundException;
import hub.music.charts.track.model.Track;
import hub.music.charts.track.model.TrackComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class RevenueServiceImpl implements RevenueService {

    @Autowired
    private DataFiles dataFiles;

    @Override
    public List<Track> getMaxRevenueTracks(int limit) throws InvalidLimitException, LimitBoundException {

        if (limit <= 0) throw new InvalidLimitException();

        List<Track> tracks = new ArrayList<>();
        try {
            List<File> files = dataFiles.getFiles();

            for (File file : files) {
                InputStream inputStream = new FileInputStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                bufferedReader.lines().skip(1)
                        .map(TrackUtil::mapToTrack)
                        .forEach(track -> {
                            double amount = track.getTotalAmount();
                            if (tracks.size() == limit) {
                                tracks.sort(new TrackComparator());
                                double currMin = tracks.get(limit - 1).getTotalAmount();
                                if (amount > currMin) {
                                    tracks.remove(limit - 1);
                                    tracks.add(track);
                                }
                            } else {
                                tracks.add(track);
                            }
                        });

                bufferedReader.close();
                inputStream.close();
            }
            if (!tracks.isEmpty() && tracks.size() < limit) throw new LimitBoundException();

        } catch (IOException ioException) {
            System.out.println("Exception:" + ioException);
        }
        return tracks;
    }
}
