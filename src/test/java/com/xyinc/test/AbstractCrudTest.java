package com.xyinc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xyinc.XyIncApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = XyIncApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractCrudTest {

	@Value("${local.server.port}")
	private int port;

	
	private static final String CLIENT_ID = "xy_inc";
	private static final String SECRET = "@xy_inc@";
	
    final String USUARIO = "admin";
    final String SENHA = "admin";
    
    private String accessToken;

	protected String getBaseUrl() {
		return "http://localhost:" + port;
	}
	
	@Before
    public void setUp() throws Exception {
		accessToken = getAccessToken();
    }
	
    @SuppressWarnings("rawtypes")
	protected String getAccessToken() throws JsonParseException, JsonMappingException, IOException{
    	String url = getBaseUrl() + "/oauth/token?grant_type=password&username=" + USUARIO +"&password=" + SENHA;
    	ResponseEntity<String> response = new TestRestTemplate(CLIENT_ID, SECRET).postForEntity(url, null, String.class);
        String responseText = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        HashMap jwtMap = new ObjectMapper().readValue(responseText, HashMap.class);

        assertEquals("bearer", jwtMap.get("token_type"));
        assertEquals("read write", jwtMap.get("scope"));
        assertTrue(jwtMap.containsKey("access_token"));
        assertTrue(jwtMap.containsKey("expires_in"));
        assertTrue(jwtMap.containsKey("jti"));
        String accessToken = (String) jwtMap.get("access_token");
        
        return accessToken;
    	
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected <T> List<T> getList(final String requestMappingUrl, 
								  final Class<T> serviceListReturnTypeClass) {
		

		final ObjectMapper mapper = new ObjectMapper();

		HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

		final TestRestTemplate restTemplate = new TestRestTemplate();
		final HttpEntity<?> requestEntity = new HttpEntity<>(null, headers);
		final ResponseEntity<List> entity = restTemplate.exchange(getBaseUrl() + requestMappingUrl, 
																  HttpMethod.GET, 
																  requestEntity, 
																  List.class);
		final List<Map<String, String>> entries = entity.getBody();
		final List<T> returnList = new ArrayList<T>();
		for (final Map<String, String> entry : entries) {
			returnList.add(mapper.convertValue(entry, serviceListReturnTypeClass));
		}
		return returnList;
	}

	protected <T> T getEntity(final String requestMappingUrl, final Class<T> classReturn) {
		HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        
		final TestRestTemplate restTemplate = new TestRestTemplate();
		final HttpEntity<?> requestEntity = new HttpEntity<>(null, headers);

		final ResponseEntity<T> responseEntity = restTemplate.exchange(getBaseUrl() + requestMappingUrl, 
															   HttpMethod.GET, 
															   requestEntity, 
															   classReturn);
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		return responseEntity.getBody();
	}

	protected <T> T postEntity(final String requestMappingUrl,final Object objectToPost, final Class<T> classReturn) throws JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        
		final TestRestTemplate restTemplate = new TestRestTemplate();
		final ObjectMapper mapper = new ObjectMapper();
		
		final HttpEntity<String> requestEntity = new HttpEntity<String>(mapper.writeValueAsString(objectToPost), headers);

		final ResponseEntity<T> responseEntity = restTemplate.exchange(getBaseUrl() + requestMappingUrl, 
				   HttpMethod.POST, 
				   requestEntity, 
				   classReturn);

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		
		return responseEntity.getBody();
	}
	
	protected <T> T putEntity(final String requestMappingUrl, final Object objectToPost, final Class<T> classReturn) throws JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        
		final TestRestTemplate restTemplate = new TestRestTemplate();
		final ObjectMapper mapper = new ObjectMapper();
		
		final HttpEntity<String> requestEntity = new HttpEntity<String>(mapper.writeValueAsString(objectToPost), headers);
		
		ResponseEntity<Void> responseEntity = restTemplate.exchange(getBaseUrl() + requestMappingUrl, 
				   HttpMethod.PUT, 
				   requestEntity, 
				   Void.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		T entity = getEntity(requestMappingUrl, classReturn);
		
		return entity;
	}
	
	protected void deleteEntity(final String requestMappingUrl) {
		HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        
		final TestRestTemplate restTemplate = new TestRestTemplate();
		final HttpEntity<?> requestEntity = new HttpEntity<>(null, headers);

		ResponseEntity<Void> responseEntity = restTemplate.exchange(getBaseUrl() + requestMappingUrl, 
				   HttpMethod.DELETE, 
				   requestEntity, 
				   Void.class);

		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
	}
}