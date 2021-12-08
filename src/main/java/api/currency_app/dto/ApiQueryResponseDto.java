package api.currency_app.dto;

import lombok.Data;

@Data
public class ApiQueryResponseDto {
    private String apikey;
    private Integer timestamp;
    private String base_currency;
}
