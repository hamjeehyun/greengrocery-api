package greengrocery.controller;

import greengrocery.domain.common.Message;
import greengrocery.service.FruitService;
import greengrocery.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fruits")
public class FruitController {
    @Autowired
    private FruitService fruitService;


    @GetMapping("token")
    public ResponseEntity<Message> getToken() {
        return ResponseUtil.success(fruitService.getToken());
    }


    @GetMapping("list")
    public ResponseEntity<Message> getFruitList(@RequestHeader String token) {
        return ResponseUtil.success(fruitService.getFruitList(token));
    }


    @GetMapping("price")
    public ResponseEntity<Message> getFruitPrice(@RequestHeader String token, @RequestParam String name) {
        return ResponseUtil.success(fruitService.getFruitPrice(token, name));

    }
}
