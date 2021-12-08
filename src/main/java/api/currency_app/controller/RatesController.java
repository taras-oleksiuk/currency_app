package api.currency_app.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/search")
@Controller
public class RatesController {
    @RequestMapping(value = "/{base_currency}", method = RequestMethod.GET)
    public void method(HttpServletResponse response, @PathVariable String base_currency) {
        String externalUrl = "https://freecurrencyapi.net/api/v2/latest?"
                + "apikey=ce04af60-4e4b-11ec-b25c-bf66353b96dc&base_currency=" + base_currency;
        response.setStatus(302);
        response.setHeader("Location", externalUrl);
    }

    @RequestMapping(value = "{date_from}/{date_to}", method = RequestMethod.GET)
    public void method2(HttpServletResponse response, @PathVariable String date_from,
                        @PathVariable String date_to) {
        String externalUrl = "https://freecurrencyapi.net/api/v2/historical?"
                + "apikey=ce04af60-4e4b-11ec-b25c-bf66353b96dc&base_currency=USD"
                + "&date_from=" + date_from + "&date_to=" + date_to;
        response.setStatus(302);
        response.setHeader("Location", externalUrl);
    }
}
