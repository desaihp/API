package com.nevelex.api.task2;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nevelex.API.task2.core.ApacheHTTPUtil;
import com.nevelex.api.task2.core.NevelexServiceBaseTest;

public class APITestingTask2_GET_NG extends NevelexServiceBaseTest implements APITestingTask2Constant {

	// we will check 200 Sucess scenario and data will found/OK.
	@Test(priority=1)
	public void testwithSucessScenario() throws ClientProtocolException, IOException {

		HttpResponse response = ApacheHTTPUtil.sendAndReceiveMessageWithGETRequest(SERVICE_URL);

		// using validation
		Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK); // for 200
																							
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
		String responseMessage = ApacheHTTPUtil.getResponseString(response);
		System.out.println(responseMessage);
		// validation
		Assert.assertTrue(responseMessage.contains("John Doe"));
		Assert.assertTrue(responseMessage.contains("12345"));
		Assert.assertTrue(responseMessage.contains("Wherabouts"));
        Assert.assertFalse(responseMessage.contains("ERRORCODE"));
	}

	// we will check 500 scenario so i change our url and check the services
	// that perform Error 500 Internal Server Error.
	@Test(priority=2)
	public void testwith500Scenario() throws ClientProtocolException, IOException {

		HttpResponse response = ApacheHTTPUtil.sendAndReceiveMessageWithGETRequest500(SERVICE_URL500);

		// using validation
		Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_INTERNAL_SERVER_ERROR);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Internal Server Error");
	}

	// we will check 404 scenario so i change our url and check the services
	// that perform Error 404 data not found.
	@Test(priority=3)
	public void testwith404Scenario() throws ClientProtocolException, IOException {

		HttpResponse response = ApacheHTTPUtil.sendAndReceiveMessageWithGETRequest404(SERVICE_URL404);
		// using validation
		Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	}
}
