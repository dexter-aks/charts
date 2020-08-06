package hub.music.charts.track.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource(name = "currency", value = "classpath:currency.properties")
@ConfigurationProperties
public class Currency {

    @Value("#{${currencies}}")
    private Map<String, Double> currencies;

    public static Map<String, Double> exchangeRate;

    @PostConstruct
    public void loadCurrency(){
        exchangeRate = new HashMap<>();
        exchangeRate.putAll(currencies);
    }
}
