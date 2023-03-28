package greengrocery.service;

import greengrocery.domain.dto.ProductDto;
import greengrocery.domain.dto.TokenDto;
import greengrocery.proxy.MarketProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VegetableService {
    @Autowired
    private MarketProxy marketProxy;

    public TokenDto getToken() {
        return marketProxy.getVegetableToken();
    }

    public List<String> getVegetableList(String token) {
        return marketProxy.getVegetableList(token);
    }

    public ProductDto getVegetablePrice(String token, String name) {
        return marketProxy.getVegetablePrice(token, name);
    }
}
