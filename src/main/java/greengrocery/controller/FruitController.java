package greengrocery.controller;

import greengrocery.domain.common.Message;
import greengrocery.service.FruitService;
import greengrocery.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value="과일 토큰 발급", notes="과일 목록 조회 및 가격 조회 시 필요한 토큰을 발급한다.")
    public ResponseEntity<Message> getToken() {
        return ResponseUtil.success(fruitService.getToken());
    }


    @GetMapping("list")
    @ApiOperation(value="과일 목록 조회", notes="과일 목록 조회를 한다. (Header에 token 필요)")
    public ResponseEntity<Message> getFruitList(@RequestHeader String token) {
        return ResponseUtil.success(fruitService.getFruitList(token));
    }


    @GetMapping("price")
    @ApiOperation(value="과일 가격 조회", notes="조회하고자하느 과일명을 parma으로 보내 가격을 조회한다. (Header에 token 필요)")
    public ResponseEntity<Message> getFruitPrice(@RequestHeader String token, @RequestParam String name) {
        return ResponseUtil.success(fruitService.getFruitPrice(token, name));

    }
}
