package com.jio.pe.app.test;
import java.net.URI;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;

import com.jio.pe.app.model.UserMaster;
public class SpringRestClient {
	  
    public static final String REST_SERVICE_URI = "http://localhost:8080/RestJioPEService";
  
    /*
     * Add HTTP Authorization header, using Basic-Authentication to send UserMaster-credentials.
     */
    private static HttpHeaders getHeaders(){
        String plainCredentials="bill:abc1234";
        String base64Credentials = new String(Base64.encode(plainCredentials.getBytes()));
         
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }
     
    /*
     * Send a GET request to get list of all UserMasters.
     */
    @SuppressWarnings("unchecked")
    private static void listAllUserMasters(){
        System.out.println("\nTesting listAllUserMasters API-----------");
        RestTemplate restTemplate = new RestTemplate(); 
         
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        ResponseEntity<List> response = restTemplate.exchange(REST_SERVICE_URI+"/users/", HttpMethod.GET, request, List.class);
        List<LinkedHashMap<String, Object>> UserMastersMap = (List<LinkedHashMap<String, Object>>)response.getBody();
         
        if(UserMastersMap!=null){
            for(LinkedHashMap<String, Object> map : UserMastersMap){
                System.out.println("UserMaster : id="+map.get("userId")+", Name="+map.get("userName"));
            }
        }else{
            System.out.println("No UserMaster exist----------");
        }
    }
      
    /*
     * Send a GET request to get a specific UserMaster.
     */
    private static void getUserMaster(){
        System.out.println("\nTesting getUserMaster API----------");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        ResponseEntity<UserMaster> response = restTemplate.exchange(REST_SERVICE_URI+"/users/1", HttpMethod.GET, request, UserMaster.class);
        UserMaster UserMaster = response.getBody();
        System.out.println(UserMaster.getUserId() + ":" + UserMaster.getUserName());
    }
      
    /*
     * Send a POST request to create a new UserMaster.
     */
    private static void createUserMaster() {
        System.out.println("\nTesting create UserMaster API----------");
        RestTemplate restTemplate = new RestTemplate();
        UserMaster UserMaster = new UserMaster();
        
        HttpEntity<Object> request = new HttpEntity<Object>(UserMaster, getHeaders());
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/users/", request, UserMaster.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
  
    /*
     * Send a PUT request to update an existing UserMaster.
     */
    private static void updateUserMaster() {
        System.out.println("\nTesting update UserMaster API----------");
        RestTemplate restTemplate = new RestTemplate();
        UserMaster UserMaster  = new UserMaster();
        HttpEntity<Object> request = new HttpEntity<Object>(UserMaster, getHeaders());
        ResponseEntity<UserMaster> response = restTemplate.exchange(REST_SERVICE_URI+"/users/1", HttpMethod.PUT, request, UserMaster.class);
        System.out.println(response.getBody());
    }
  
    /*
     * Send a DELETE request to delete a specific UserMaster.
     */
    private static void deleteUserMaster() {
        System.out.println("\nTesting delete UserMaster API----------");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        restTemplate.exchange(REST_SERVICE_URI+"/users/3", HttpMethod.DELETE, request, UserMaster.class);
    }
  
  
    /*
     * Send a DELETE request to delete all UserMasters.
     */
    private static void deleteAllUserMasters() {
        System.out.println("\nTesting all delete UserMasters API----------");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        restTemplate.exchange(REST_SERVICE_URI+"/users/", HttpMethod.DELETE, request, UserMaster.class);
    }
  
 
    public static void main(String args[]){
         
        listAllUserMasters();
 
        getUserMaster();
 
        /*
        createUserMaster();
        listAllUserMasters();
 
        updateUserMaster();
        listAllUserMasters();
 
        deleteUserMaster();
        listAllUserMasters();
 
        deleteAllUserMasters();
        listAllUserMasters();
		*/
    }
}
