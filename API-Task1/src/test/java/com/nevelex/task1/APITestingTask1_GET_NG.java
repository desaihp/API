package com.nevelex.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nevelex.APITASK1.core.ApacheHTTPUtil;

public class APITestingTask1_GET_NG {
	JSONObject parseResponse;
	public static String SERVICE_URL = "http://localhost:80/v2/getdata";
    public static String SERVICE_URL_INVALID = "http://localhost:80/v2/getdata/XYZinvalid";
	@Test
	public void testwithSucessScenario() throws ClientProtocolException, IOException {

		HttpResponse response = ApacheHTTPUtil.sendAndReceiveMsgWithGETRequest(SERVICE_URL);
		Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK); // for
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
		// System.out.println(response.getStatusLine().getStatusCode());
		// System.out.println(response.getStatusLine().getReasonPhrase());

		String responseMessage = ApacheHTTPUtil.getResponseString(response);
		System.out.println(responseMessage);
		parseResponse = new JSONObject(responseMessage.toString());

		// for validation
		Assert.assertTrue(parseResponse.get("Name") instanceof String);
		Assert.assertTrue(parseResponse.get("ID") instanceof Integer);
		Assert.assertTrue(parseResponse.get("Name") instanceof String);
		Assert.assertTrue(parseResponse.get("FamousWords") instanceof String);
	}

	@Test
	public void testwith404Scenario() throws ClientProtocolException, IOException {

		HttpResponse response = ApacheHTTPUtil.sendAndReceiveMsgWithGETRequest(SERVICE_URL);

		Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND); // for
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");

	}
// when invalid endpoint request
	@Test
	public void testwith400Scenario() throws ClientProtocolException, IOException {

		HttpResponse response = ApacheHTTPUtil.sendAndReceiveMsgWithGETRequest(SERVICE_URL_INVALID);

		Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad request");

	}
//incase when user need authentication. 
	@Test
	public void testwith403Scenario() throws ClientProtocolException, IOException {

		HttpResponse response = ApacheHTTPUtil.sendAndReceiveMsgWithGETRequest(SERVICE_URL);

		Assert.assertEquals(response.getStatusLine().getStatusCode(), 403);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Forbidden");

	}
}
