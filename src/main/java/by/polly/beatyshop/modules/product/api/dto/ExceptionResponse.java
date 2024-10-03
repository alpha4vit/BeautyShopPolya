package by.polly.beatyshop.modules.product.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@AllArgsConstructor
public class ExceptionResponse {

    private Map<String, Object> errors;

    private String message;

    private HttpStatus status;
}