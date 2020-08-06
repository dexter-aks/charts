package hub.music.charts.track.controller;

import hub.music.charts.track.exception.InvalidLimitException;
import hub.music.charts.track.exception.LimitBoundException;
import hub.music.charts.track.model.Track;
import hub.music.charts.track.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/tracks/revenue")
public class RevenueController {

    @Autowired
    private RevenueService revenueService;

    @GetMapping(path = "/max", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Track>> topRevenueTracks(@RequestParam("limit") int limit) {

        try {
            List<Track> maxRevenueTracks = revenueService.getMaxRevenueTracks(limit);
            return ResponseEntity.ok(maxRevenueTracks);
        } catch (InvalidLimitException | LimitBoundException ex) {
            System.out.println("Exception while getting max revenue:" + ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
