package hub.music.charts.track.service;

import hub.music.charts.track.configuration.DataFiles;
import hub.music.charts.track.exception.InvalidLimitException;
import hub.music.charts.track.exception.LimitBoundException;
import hub.music.charts.track.model.Track;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@WebMvcTest(value = RevenueService.class)
public class RevenueServiceImplTest {

    @Value("classpath:dsp_streaming_report_us.csv")
    private File usCharts;

    @Value("classpath:dsp_streaming_report_uk.csv")
    private File ukCharts;

    @Autowired
    private RevenueService revenueService;

    @MockBean
    private DataFiles dataFiles;

    @Test
    public void successfullyGetTop5MaxRevenueTracks() throws InvalidLimitException, LimitBoundException {
        int limit = 5;
        List<Track> expected = getTracks();

        when(dataFiles.getFiles()).thenReturn(getFiles());

        List<Track> actual = revenueService.getMaxRevenueTracks(limit);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @Test
    public void throwInvalidLimitExceptionIfLimitIsZero() throws InvalidLimitException, LimitBoundException {
        int limit = 0;
        when(dataFiles.getFiles()).thenReturn(getFiles());

        assertThrows(InvalidLimitException.class, () -> {
            revenueService.getMaxRevenueTracks(limit);
        });

    }

    @Test
    public void throwLimitBoundExceptionIfLimitIsGreaterThanDataSet() throws InvalidLimitException, LimitBoundException{
        int limit = 100;
        when(dataFiles.getFiles()).thenReturn(getFiles());

        assertThrows(LimitBoundException.class, () -> {
            revenueService.getMaxRevenueTracks(limit);
        });
    }

    private List<File> getFiles(){
        List<File> files = new ArrayList<>();
        files.add(usCharts);
        files.add(ukCharts);
        return files;
    }

    private List<Track> getTracks(){
        List<Track> tracks = new ArrayList<>();
        Track t1 = new Track("G853C3770233", "Let's Talk About Love", "Primal Scream", 5.550000000000001);
        Track t2 = new Track("W9P94183IN00", "The Eminem Show", "Fats Domino", 4.641);
        Track t3 = new Track("6635NZ24S04O", "Happy Nation", "Madonna", 4.44);
        Track t4 = new Track("17FJ32050304", "...Baby One More Time", "Joy Division", 3.8674999999999997);
        Track t5 = new Track("6495T70XV5S6", "X&Y", "Pete Townshend", 3.8674999999999997);

        tracks.add(t1);
        tracks.add(t2);
        tracks.add(t3);
        tracks.add(t4);
        tracks.add(t5);

        return tracks;
    }
}