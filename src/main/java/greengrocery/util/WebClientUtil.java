package greengrocery.util;

import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

public class WebClientUtil {

    public static String getBodyResponse(String url, String param, String token) {
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

    public static ClientResponse getHeaderResponse(String url) {
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
