package com.nutriapp.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class HttpUtils {

    private final String API_URL = "https://api.nal.usda.gov/fdc/v1/";

    private final WebClient webClient;

    public HttpUtils(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(API_URL).build();
    }

    public Mono<?> someRestCall(String food) {
        return this.webClient.get().uri("foods/search?query={food}", food)
                .retrieve().bodyToMono(String.class);
    }

    @Scheduled(cron = "*/10 * * * * *")
    private void call() {
        someRestCall("apple");
    }


    public void sendSimpleHttpRequest() {
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("X-Api-Key", "Yg33W693ydvAIwHNfq9HBMEuKUc49ussf32bVIBb")
                .defaultUriVariables(Collections.singletonMap("url", API_URL /*+ "foods/search?query=apple&pageSize=2" + "&api_key=Yg33W693ydvAIwHNfq9HBMEuKUc49ussf32bVIBb"*/))
                .build();

        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/food/search?query=apple&pageSize=2");

        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue("data"); // for add body

        Mono<String> response = headersSpec.retrieve()
                .bodyToMono(String.class);
    }


}
