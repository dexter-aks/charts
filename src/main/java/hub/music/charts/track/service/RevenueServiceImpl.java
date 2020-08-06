package hub.music.charts.track.service;

import hub.music.charts.track.exception.InvalidLimitException;
import hub.music.charts.track.exception.LimitBoundException;
import hub.music.charts.track.model.Track;
import hub.music.charts.track.model.TrackComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class RevenueServiceImpl implements RevenueService {

    @Autowired
    private FileService fileService;

    @Override
    public List<Track> getMaxRevenueTracks(int limit) throws InvalidLimitException, LimitBoundException {

        if (limit <= 0) throw new InvalidLimitException();

        List<Track> tracks = new ArrayList<>();
        try {
            List<File> files = fileService.getFiles();

            for (File file : files) {
                InputStream is = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                br.lines().skip(1)
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

                br.close();
                is.close();
            }
            if (!tracks.isEmpty() && tracks.size() < limit) throw new LimitBoundException();

        } catch (IOException ioException) {
            System.out.println("Exception:" + ioException);
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return tracks;
    }
}
