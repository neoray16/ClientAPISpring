package com.example.clientapispring;

import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ClientApplication {

	public static void main(String[] args) {
		String path="http://localhost:8080/getImage";
		HttpClient client=HttpClient.newHttpClient();
		try {

		HttpRequest httpRequest=HttpRequest.newBuilder()
				.uri(new URI(path))
				.header("Content-Type","application/json")
				.GET().build();
		HttpResponse<byte[]> response=client.send(httpRequest,HttpResponse.BodyHandlers.ofByteArray());
		System.out.println(response.body().length);
		}catch (Exception e){
			System.out.println(e);
		}



		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response1 = restTemplate.getForEntity("http://localhost:8080/api/hello", String.class);
		String jsonResponse = response1.getBody();

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Map<String, String> responseData = objectMapper.readValue(jsonResponse, Map.class);

			if (responseData != null) {
				String ipAddress = responseData.get("ipAddress");
				String date = responseData.get("date");
				String message = responseData.get("message");

				System.out.println("Response from server:" +message);
				System.out.println("IP Address: " + ipAddress);
				System.out.println("Date: " + date);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}


