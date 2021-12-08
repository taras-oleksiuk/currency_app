package api.currency_app.controller;

import static org.hibernate.internal.CoreLogging.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UtilController {
    private static final Logger logger = LogManager.getLogger(UtilController.class);

    @GetMapping()
    public String greeting() {
        logger("The user logged");
        return "You are welcome!";
    }
}
