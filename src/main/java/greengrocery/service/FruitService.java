package greengrocery.service;

import greengrocery.domain.dto.ProductDto;
import greengrocery.domain.dto.TokenDto;
import greengrocery.proxy.MarketProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitService {
    @Autowired
    private MarketProxy marketProxy;

    public TokenDto getToken() {
        return marketProxy.getFruitToken();
    }

    public List<String> getFruitList(String token) {
        return marketProxy.getFruitList(token);
    }

    public ProductDto getFruitPrice(String token, String name) {
        return marketProxy.getFruitPrice(token, name);
    }

}
