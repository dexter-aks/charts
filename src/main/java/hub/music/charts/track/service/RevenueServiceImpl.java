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

        InputStream inputStream = null;
        BufferedReader bufferedReader = null;

        List<Track> tracks = new ArrayList<>();
        try {
            List<File> files = dataFiles.getFiles();

            for (File file : files) {
                inputStream = new FileInputStream(file);
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
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
            }
            if (!tracks.isEmpty() && (tracks.size() < limit)) throw new LimitBoundException();

        } catch (IOException ioException) {
            System.out.println("Exception:" + ioException.getMessage());
        } finally {
            close(inputStream, bufferedReader);
        }
        return tracks;
    }

    public void close(InputStream inputStream, BufferedReader bufferedReader) {
        try {
            if (bufferedReader != null) bufferedReader.close();
            if (inputStream != null) inputStream.close();
        } catch (IOException e) {
            System.out.println("Exception while closing the resources" + e.getMessage());
        }
    }
}
