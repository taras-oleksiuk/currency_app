package api.currency_app.dto;

import lombok.Data;

@Data
public class ApiInfoResponseDto {
    private ApiDataResponseDto data;
    private ApiQueryResponseDto query;
}
