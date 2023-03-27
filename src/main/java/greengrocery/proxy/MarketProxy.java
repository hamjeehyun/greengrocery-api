package greengrocery.proxy;

import greengrocery.domain.dto.ProductDto;
import greengrocery.domain.dto.TokenDto;
import greengrocery.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class MarketProxy {
    @Value("${greengrocery.fruit.token}")
    private String FRUIT_TOKEN_URL = "";
    @Value("${greengrocery.fruit.prduct}")
    private String FRUIT_PRODUCT_URL = "";
    @Value("${greengrocery.vegetable.token}")
    private String VEGETABLE_TOKEN_URL = "";
    @Value("${greengrocery.vegetable.prduct}")
    private String VEGETABLE_PRODUCT_URL = "";

    public TokenDto getFruitToken() {
        String response = getBodyResponse(FRUIT_TOKEN_URL, null, null);

        return JsonUtil.fromJson(response, TokenDto.class);
    }

    public List<String> getFruitList(String token) {
        String response = getBodyResponse(FRUIT_PRODUCT_URL, null, token);

        return JsonUtil.fromJsonList(response, String.class);
    }

    public ProductDto getFruitPrice(String token, String name) {
        String response = getBodyResponse(FRUIT_PRODUCT_URL, name, token);

        return JsonUtil.fromJson(response, ProductDto.class);
    }

    public TokenDto getVegetableToken() {
        ClientResponse response = getHeaderResponse(VEGETABLE_TOKEN_URL);
        String cookieData = Objects.requireNonNull(response.headers().asHttpHeaders().get("Set-Cookie")).get(0);
        String[] cookieData1 = cookieData.split("=");
        String[] cookieData2 = cookieData1[1].split(";");

        return new TokenDto(cookieData2[0]);
    }

    public List<String> getVegetableList(String token) {
        String response = getBodyResponse(VEGETABLE_PRODUCT_URL, null, token);

        return JsonUtil.fromJsonList(response, String.class);
    }

    public ProductDto getVegetablePrice(String token, String name) {
        String response = getBodyResponse(VEGETABLE_PRODUCT_URL, name, token);

        return JsonUtil.fromJson(response, ProductDto.class);
    }

    private String getBodyResponse(String url, String param, String token) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(url);

        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .defaultHeader("Authorization", token)
                .baseUrl(url)
                .build();

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("name", param)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private ClientResponse getHeaderResponse(String url) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(url);

        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(url)
                .build();

        return webClient.get()
                .exchange()
                .block();
    }
}
