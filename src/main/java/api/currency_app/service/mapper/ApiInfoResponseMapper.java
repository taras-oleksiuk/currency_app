package api.currency_app.service.mapper;


import api.currency_app.dto.ApiDataResponseDto;
import api.currency_app.dto.ApiInfoResponseDto;
import api.currency_app.model.Currency;
import org.springframework.stereotype.Component;

@Component
public class ApiInfoResponseMapper {
    public Currency getInfoResponseDto(ApiInfoResponseDto apiInfoResponseDto) {
        Currency currency = new Currency();
        currency.setApikey(apiInfoResponseDto.getQuery().getApikey());
        currency.setBase_currency(apiInfoResponseDto.getQuery().getBase_currency());
        currency.setTimestamp(apiInfoResponseDto.getQuery().getTimestamp());
        currency.setCNY(apiInfoResponseDto.getData().getCNY());
        currency.setJPY(apiInfoResponseDto.getData().getJPY());
        return currency;
    }

    public ApiDataResponseDto mapOfAvailableData(Currency currency) {
        ApiDataResponseDto responseDto = new ApiDataResponseDto();
        responseDto.setCNY(currency.getCNY());
        responseDto.setJPY(currency.getJPY());
        return responseDto;
    }
}
