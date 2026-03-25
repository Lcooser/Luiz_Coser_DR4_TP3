package br.com.infnet.devcalc.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CalculatorControllerTest {
  private final HttpClient httpClient = HttpClient.newHttpClient();

  @Value("${local.server.port}")
  private int port;

  @Test
  void shouldAddNumbers() throws IOException, InterruptedException {
    HttpResponse<String> response = sendGet("/api/calc/add?a=7&b=5");

    assertEquals(200, response.statusCode());
    assertEquals("{\"operation\":\"add\",\"result\":12.0}", response.body());
  }

  @Test
  void shouldReturnSquareRoot() throws IOException, InterruptedException {
    HttpResponse<String> response = sendGet("/api/calc/sqrt?x=16");

    assertEquals(200, response.statusCode());
    assertEquals("{\"operation\":\"sqrt\",\"result\":4.0}", response.body());
  }

  @Test
  void shouldRejectNegativeSquareRoot() throws IOException, InterruptedException {
    HttpResponse<String> response = sendGet("/api/calc/sqrt?x=-1");

    assertEquals(400, response.statusCode());
    assertEquals("{\"error\":\"Square root requires a non-negative value.\"}", response.body());
  }

  private HttpResponse<String> sendGet(String path) throws IOException, InterruptedException {
    HttpRequest request =
        HttpRequest.newBuilder().uri(URI.create("http://localhost:" + port + path)).GET().build();

    return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
  }
}
