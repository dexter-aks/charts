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

    private static Map<String, Double> rates;

    @PostConstruct
    public void loadCurrency(){
        rates = new HashMap<>();
        rates.putAll(currencies);
    }

    public static double getRate(String currency){
        return rates.get(currency);
    }
}
