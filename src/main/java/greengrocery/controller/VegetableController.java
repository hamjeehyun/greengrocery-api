package greengrocery.controller;

import greengrocery.domain.common.Message;
import greengrocery.service.VegetableService;
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
@RequestMapping("vegetables")
public class VegetableController {
    @Autowired
    private VegetableService vegetableService;


    @GetMapping("token")
    @ApiOperation(value = "채소 토큰 발급", notes = "채소 목록 조회 및 가격 조회 시 필요한 토큰을 발급한다.")
    public ResponseEntity<Message> getToken() {
        return ResponseUtil.success(vegetableService.getToken());
    }

    @GetMapping("list")
    @ApiOperation(value = "채소 목록 조회", notes = "채소 목록 조회를 한다. (Header에 token 필요)")
    public ResponseEntity<Message> getVegetableList(@RequestHeader String token) {
        return ResponseUtil.success(vegetableService.getVegetableList(token));
    }


    @GetMapping("price")
    @ApiOperation(value = "채소 가격 조회", notes = "조회하고자하느 채소명을 parma으로 보내 가격을 조회한다. (Header에 token 필요)")
    public ResponseEntity<Message> getVegetablePrice(@RequestHeader String token, @RequestParam String name) {
        return ResponseUtil.success(vegetableService.getVegetablePrice(token, name));
    }
}
