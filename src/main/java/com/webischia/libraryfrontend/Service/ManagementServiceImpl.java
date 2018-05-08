package com.webischia.libraryfrontend.Service;

import com.webischia.libraryfrontend.Model.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagementServiceImpl implements ManagementService{

    RestTemplate restTemplate;

    public ManagementServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void addAuthor(Author author, String token) {

        String url="http://localhost:8090/api/v1/management/author/add";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);
        //System.out.println("buradayım");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("authorName",author.getAuthorName());
        postMap.put("authorLastName",author.getAuthorLastName());
        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(postMap, headers);
        restTemplate.postForObject(uriBuilder.toUriString(), request, Author.class);
        //

    }

    @Override
    public void deleteAuthor(int id, String token) {

    }

    @Override
    public Author showAuthor(int id, String token) {
        String url="http://localhost:8090/api/v1/management/author/get/"+id;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
/*
        ListTickets liste = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,ListTickets.class).getBody();
*/
        //System.out.println(restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,Author[].class).getBody());

        return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,Author.class).getBody();

    }

    @Override
    public Author[] getAllAuthors(String token) {
        String url="http://localhost:8090/api/v1/management/author/get/all";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
/*
        ListTickets liste = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,ListTickets.class).getBody();
*/
        //System.out.println(restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,Author[].class).getBody());

        Author[] authors = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,Author[].class).getBody();
        return authors;
    }

    @Override
    public void addSubject(Subject subject, String token) {

    }

    @Override
    public void deleteSubject(int id, String token) {

    }

    @Override
    public Subject showSubject(int id, String token) {
        return null;
    }

    @Override
    public Subject[] getAllSubjects(String token) {
        return new Subject[0];
    }

    @Override
    public void addItemType(ItemType itemType, String token) {

    }

    @Override
    public void deleteItemType(int id, String token) {

    }

    @Override
    public ItemType showItemType(int id, String token) {
        return null;
    }

    @Override
    public ItemType[] getAllTypes(String token) {
        return new ItemType[0];
    }

    @Override
    public void addItem(Items items, String token) {

    }

    @Override
    public void deleteItem(int id, String token) {

    }

    @Override
    public Items showItem(int id, String token) {
        return null;
    }

    @Override
    public Items[] getAllItems(String token) {
        return new Items[0];
    }

    @Override
    public void addPublisher(Publisher publisher, String token) {

    }

    @Override
    public void deletePublisher(int id, String token) {

    }

    @Override
    public Publisher showPublisher(int id, String token) {
        return null;
    }

    @Override
    public Publisher[] getAllPublisher(String token) {
        return new Publisher[0];
    }
}
