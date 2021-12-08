package api.currency_app.util;


import api.currency_app.config.HttpClient;
import api.currency_app.dto.ApiInfoResponseDto;
import api.currency_app.model.Currency;
import api.currency_app.service.CurrencyService;
import api.currency_app.service.mapper.ApiInfoResponseMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer {
    private static final Logger logger = LogManager.getLogger(AppInitializer.class);
    private final CurrencyService currencyService;
    private final ApiInfoResponseMapper apiInfoResponseMapper;

    public AppInitializer(CurrencyService currencyService, ApiInfoResponseMapper apiInfoResponseMapper) {
        this.currencyService = currencyService;
        this.apiInfoResponseMapper = apiInfoResponseMapper;
    }

    @PostConstruct
    public void initializeDb() {
        try {
            String apikey = "ce04af60-4e4b-11ec-b25c-bf66353b96dc";
            String url = "https://freecurrencyapi.net/api/v2/latest";
            URL urlForGetRequest = new URL(url);
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
                System.out.println(response.toString());
            } else {
                throw new Exception("Error in API Call");
            }
        } catch (Exception ex) {
            logger.error("Can't login", ex);
        }
        HttpClient httpClient = new HttpClient();
        ApiInfoResponseDto apiInfoResponseDto = httpClient.get("https://freecurrencyapi.net/api/v2/latest?"
                + "apikey=ce04af60-4e4b-11ec-b25c-bf66353b96dc");
        Currency currency = apiInfoResponseMapper.getInfoResponseDto(apiInfoResponseDto);
        currencyService.save(currency);
    }
}
