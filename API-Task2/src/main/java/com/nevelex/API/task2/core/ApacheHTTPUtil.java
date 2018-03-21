package com.nevelex.API.task2.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class ApacheHTTPUtil {
	public static String getResponseString(HttpResponse response) throws IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line + "\n");
		}

//		System.out.println(result);
		return result.toString();
	}

	//For Success Scenario 200 ok. 
	public static HttpResponse sendAndReceiveMessageWithGETRequest(String url) throws IOException, ClientProtocolException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		return response;
	}
	
	//For Error 500 Scenario
	public static HttpResponse sendAndReceiveMessageWithGETRequest500(String url500) throws IOException, ClientProtocolException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url500);
		HttpResponse response = client.execute(request);
		return response;
	}

	//For Error 404 Scenario
	public static HttpResponse sendAndReceiveMessageWithGETRequest404(String url404) throws IOException, ClientProtocolException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url404);
		HttpResponse response = client.execute(request);
		return response;
	}	

}
