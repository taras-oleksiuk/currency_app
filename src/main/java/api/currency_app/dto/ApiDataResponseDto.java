package api.currency_app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApiDataResponseDto {
   @JsonProperty("JPY")
    private float JPY;
    @JsonProperty("CNY")
    private float CNY;
}
