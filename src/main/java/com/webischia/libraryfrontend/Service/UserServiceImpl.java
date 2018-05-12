package com.webischia.libraryfrontend.Service;

import com.webischia.libraryfrontend.Model.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private RestTemplate restTemplate;

    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserToken loginUser(String uname, String password){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization","Basic TGlicmFyeUZyb250ZW5kOlhZN2ttem9OemwxMDA=");
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("username", uname);
        map.add("grant_type","password");
        map.add("password",password);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString("http://localhost:8090/oauth/token");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        Token token = restTemplate.postForObject(uriBuilder.toUriString(),request,Token.class);
        UserToken userToken = new UserToken();
        userToken.setUsername(uname);
        userToken.setPassword(password);
        userToken.setToken(token.getAccess_token());
        return userToken;
    }

    public List<Items> getItems(String token) {

        String url="http://localhost:8090/api/v1/management/item/get/all";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);

        HttpEntity entity = new HttpEntity(headers);
/*
        ListTickets liste = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,ListTickets.class).getBody();
*/

        List<Items> liste = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,ListItems.class).getBody().getItemsList();
        //  System.out.println(restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,ListTickets.class).getStatusCode());

        return liste;

    }

    @Override
    public City[] getAllCities() {
        String url="http://localhost:8090/api/v1/register/city/get/all";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);

        HttpHeaders headers = new HttpHeaders();
        //headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
/*
        ListTickets liste = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,ListTickets.class).getBody();
*/
        //System.out.println(restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,Author[].class).getBody());

        return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,City[].class).getBody();

    }

    @Override
    public UserAdressDTO register(UserAdressDTO dto) {
        String url="http://localhost:8090/api/v1/register/new";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserAdressDTO> request= new HttpEntity<>(dto);
        return restTemplate.postForObject(uriBuilder.toUriString(), request, UserAdressDTO.class);
    }
}
