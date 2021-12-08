package api.currency_app.controller;

import api.currency_app.dto.ApiDataResponseDto;
import api.currency_app.service.CurrencyService;
import api.currency_app.service.mapper.ApiInfoResponseMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@AllArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;
    private final ApiInfoResponseMapper apiInfoResponseMapper;

    @GetMapping("/exchanges")
    public List<ApiDataResponseDto> getAvailableCurrencyCodes() {
        return currencyService.findAll()
                .stream()
                .map(apiInfoResponseMapper::mapOfAvailableData)
                .collect(Collectors.toList());
    }
}
