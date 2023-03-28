package greengrocery.util;

import greengrocery.domain.common.Message;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.charset.StandardCharsets;

public class ResponseUtil {

    public static ResponseEntity<Message> success(Object data) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        Message message = Message.builder()
                .status(HttpStatus.OK)
                .message("성공")
                .data(data)
                .build();

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    public static ResponseEntity<Message> fail(HttpStatus code, String msg, Object data) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        Message message = Message.builder()
                .status(code)
                .message(msg)
                .data(data)
                .build();

        return new ResponseEntity<>(message, headers, code);
    }
}