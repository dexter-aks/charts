package hub.music.charts;

import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class MaxRevenueIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void successfullyGetMaxRevenueTracks() throws Exception {
        String content = "[{\"isrc\":\"G853C3770233\",\"trackName\":\"Let's Talk About Love\",\"artistName\":\"Primal Scream\",\"revenue\":5.550000000000001},{\"isrc\":\"W9P94183IN00\",\"trackName\":\"The Eminem Show\",\"artistName\":\"Fats Domino\",\"revenue\":4.641},{\"isrc\":\"6635NZ24S04O\",\"trackName\":\"Happy Nation\",\"artistName\":\"Madonna\",\"revenue\":4.44},{\"isrc\":\"17FJ32050304\",\"trackName\":\"...Baby One More Time\",\"artistName\":\"Joy Division\",\"revenue\":3.8674999999999997},{\"isrc\":\"6495T70XV5S6\",\"trackName\":\"X&Y\",\"artistName\":\"Pete Townshend\",\"revenue\":3.8674999999999997}]";
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/tracks/revenue/max?limit=5"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(content));
    }
}
