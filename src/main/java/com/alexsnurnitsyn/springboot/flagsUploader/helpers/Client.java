package com.alexsnurnitsyn.springboot.flagsUploader.helpers;

import com.alexsnurnitsyn.springboot.flagsUploader.model.CountryModel;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component
public class Client {

    @Value("${api.url}")
    private String URL;
    private final String STUB = "{}";

    private final HttpClient client;

    @Autowired
    public Client(HttpClient client) {
        this.client = client;
    }


    public List<CountryModel> getCountryInfo(String codes) throws IOException, InterruptedException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL.replace(STUB, codes)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return mapper.readerForListOf(CountryModel.class).readValue(response.body());
    }

    public byte[] downloadFile(String downloadUrl) throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(downloadUrl))
                .build();

        CompletableFuture<HttpResponse<byte[]>> response =
                client.sendAsync(request, HttpResponse.BodyHandlers.ofByteArray());

        return response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
    }
}


