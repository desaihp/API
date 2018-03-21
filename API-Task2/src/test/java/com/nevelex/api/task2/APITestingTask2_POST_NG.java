package com.nevelex.api.task2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.print.attribute.ResolutionSyntax;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.HandlerBase;

public class APITestingTask2_POST_NG implements APITestingTask2Constant {
	@Test
	public void testwithValidData() throws ClientProtocolException, IOException {

		StringBuilder xmlBuilder = new StringBuilder();
		// xmlBuilder.append("<?xml version="1.0" encoding="UTF-8" "?>");
		xmlBuilder.append("<Name>Harshil Desai</Name>");
		xmlBuilder.append("<ID>123</ID>");
		xmlBuilder.append("<Wherabouts>unknown</Wherabouts>");
		xmlBuilder.append("<Wherabouts>Gary, IN</Wherabouts>");
		String xmlString = xmlBuilder.toString();

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(SERVICE_URL201);
		HttpEntity entity = new ByteArrayEntity(xmlString.getBytes("UTF-8"));
		post.setEntity(entity);

		HttpResponse response = client.execute(post);
		String result = EntityUtils.toString(response.getEntity());

		// check status code
		//201: The request has been fulfilled and resulted in a new resource being created

		Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
   
		//check status message
		
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
	}
}
