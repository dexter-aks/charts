package hub.music.charts.track.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService{

    @Value("classpath:dsp_streaming_report_us.csv")
    private File usCharts;

    @Value("classpath:dsp_streaming_report_uk.csv")
    private File ukCharts;

    public List<File> getFiles(){
        List<File> files = new ArrayList<>();
        files.add(usCharts);
        files.add(ukCharts);
        return files;
    }

}
