package greengrocery.controller;

import greengrocery.domain.common.Message;
import greengrocery.domain.common.StatusEnum;
import greengrocery.domain.dto.ProductDto;
import greengrocery.domain.dto.TokenDto;
import greengrocery.service.VegetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("vegetables")
public class VegetableController {
    @Autowired
    private VegetableService vegetableService;


    @GetMapping("token")
    public ResponseEntity<Message> getToken() {
        TokenDto token = vegetableService.getToken();
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        message.setStatus(StatusEnum.OK);
        message.setMessage("성공");
        message.setData(token);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    @GetMapping("list")
    public ResponseEntity<Message> getVegetableList(@RequestHeader String token) {
        List<String> vegetable = vegetableService.getVegetableList(token);
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        message.setStatus(StatusEnum.OK);
        message.setMessage("성공");
        message.setData(vegetable);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }


    @GetMapping("price")
    public ResponseEntity<Message> getVegetablePrice(@RequestHeader String token, @RequestParam String name) {
        ProductDto price = vegetableService.getVegetablePrice(token, name);
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        message.setStatus(StatusEnum.OK);
        message.setMessage("성공");
        message.setData(price);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
