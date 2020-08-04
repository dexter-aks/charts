package hub.music.charts.track.service;

import hub.music.charts.track.model.Track;
import hub.music.charts.track.model.TrackComparator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class RevenueServiceImpl implements RevenueService{

    @Value("classpath:dsp_streaming_report_us.csv")
    private File usCharts;

    @Value("classpath:dsp_streaming_report_uk.csv")
    private File ukCharts;

    @Value("${track.header}")
    private String header;

    @Override
    public List<Track> getMaxRevenueTracks(int limit) {
        List<Track> tracks = new ArrayList<>();
        try {
            List<File> files = new ArrayList<>();
            files.add(usCharts);
            files.add(ukCharts);
            for(File file: files) {

                InputStream is = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String line;
                while((line = br.readLine()) != null) {
                    if(!line.contains(header)) {
                        Track track = TrackUtil.mapToTrack(line);
                        double amount = track.getRevenue();
                        if(tracks.size() == limit) {
                            tracks.sort(new TrackComparator());
                            double currMin = tracks.get(limit -1).getRevenue();
                            if(amount > currMin) {
                                tracks.remove(limit-1);
                                tracks.add(track);
                            }
                        }else {
                            tracks.add(track);
                        }
                    }
                }
                br.close();
            }
            if(!tracks.isEmpty() && tracks.size()<limit)
                tracks.sort(new TrackComparator());
        }catch(Exception e) {
            System.out.println("Exception:"+e);
        }
        return tracks;
    }
}
