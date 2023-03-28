package greengrocery.controller;

import greengrocery.domain.common.Message;
import greengrocery.service.VegetableService;
import greengrocery.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("vegetables")
public class VegetableController {
    @Autowired
    private VegetableService vegetableService;


    @GetMapping("token")
    public ResponseEntity<Message> getToken() {
        return ResponseUtil.success(vegetableService.getToken());
    }

    @GetMapping("list")
    public ResponseEntity<Message> getVegetableList(@RequestHeader String token) {
        return ResponseUtil.success(vegetableService.getVegetableList(token));
    }


    @GetMapping("price")
    public ResponseEntity<Message> getVegetablePrice(@RequestHeader String token, @RequestParam String name) {
        return ResponseUtil.success(vegetableService.getVegetablePrice(token, name));
    }
}
