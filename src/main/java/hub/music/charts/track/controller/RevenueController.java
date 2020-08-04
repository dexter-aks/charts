package hub.music.charts.track.controller;

import hub.music.charts.track.model.Track;
import hub.music.charts.track.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/tracks/revenue")
public class RevenueController {

    @Autowired
    private RevenueService revenueService;

    @GetMapping(path = "/max", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Track>> topRevenueTracks(@RequestParam("limit") int limit){
        List<Track> maxRevenueTracks = revenueService.getMaxRevenueTracks(limit);
        return ResponseEntity.ok(maxRevenueTracks);
    }
}
