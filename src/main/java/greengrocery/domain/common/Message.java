package greengrocery.domain.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class Message {

    private HttpStatus status;
    private String message;
    private Object data;

    public Message() {
        this.status = HttpStatus.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }

    @Builder
    public Message(HttpStatus status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
