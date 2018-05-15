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

        String url="http://localhost:8090/api/v1/management/author/delete/"+id;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
    
        restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,String.class).getBody();

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
    
        Author[] authors = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,Author[].class).getBody();
        return authors;
    }

    @Override
    public void addSubject(Subject subject, String token) {
        String url="http://localhost:8090/api/v1/management/subject/add";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("subjectName",subject.getSubjectName());
        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(postMap, headers);
        restTemplate.postForObject(uriBuilder.toUriString(), request, Subject.class);
        //

    }

    @Override
    public void deleteSubject(int id, String token) {

        String url="http://localhost:8090/api/v1/management/subject/delete/"+id;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
    
        restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,String.class).getBody();
    }

    @Override
    public Subject showSubject(int id, String token) {
        String url="http://localhost:8090/api/v1/management/subject/get/"+id;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
    
        return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,Subject.class).getBody();    }

    @Override
    public Subject[] getAllSubjects(String token) {
        String url="http://localhost:8090/api/v1/management/subject/get/all";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
    
        Subject[] subjects = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,Subject[].class).getBody();
        return subjects;
    }

    @Override
    public void addItemType(ItemType itemType, String token) {
        String url="http://localhost:8090/api/v1/management/itemtype/add";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("typeName",itemType.getTypeName());
        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(postMap, headers);
        restTemplate.postForObject(uriBuilder.toUriString(), request, ItemType.class);
    }

    @Override
    public void deleteItemType(int id, String token) {
        String url="http://localhost:8090/api/v1/management/itemtype/delete/"+id;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,ItemType.class).getBody();
    }

    @Override
    public ItemType showItemType(int id, String token) {

        String url="http://localhost:8090/api/v1/management/itemtype/get/"+id;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
    
        return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,ItemType.class).getBody();
    }

    @Override
    public ItemType[] getAllTypes(String token) {

        String url="http://localhost:8090/api/v1/management/itemtype/get/all";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
    
        return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,ItemType[].class).getBody();

    }

    @Override
    public void addItem(Items items, String token) {
        String url="http://localhost:8090/api/v1/management/item/add";
        System.out.println(url) ;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);
        HttpHeaders headers = new HttpHeaders();
        System.out.println("TOKEN \n"+token);
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        //Map<Items> postMap = new HashMap<>(items);
        //HttpEntity<Items> request = new HttpEntity<Items>(items);
        HttpEntity<Items> request = new HttpEntity<Items>(items, headers);
        restTemplate.postForObject(uriBuilder.toUriString(), request, Items.class);

    }

    @Override
    public void deleteItem(int id, String token) {
        String url="http://localhost:8090/api/v1/management/item/delete/"+id;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);

        restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,Items.class).getBody();
    }

    @Override
    public Items showItem(int id, String token) {

        String url="http://localhost:8090/api/v1/management/item/get/"+id;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);

        return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,Items.class).getBody();
    }

    @Override
    public Items[] getAllItems(String token) {
        String url="http://localhost:8090/api/v1/management/item/get/all";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
  
        return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,Items[].class).getBody();    }

    @Override
    public void addPublisher(Publisher publisher, String token) {

        String url="http://localhost:8090/api/v1/management/publisher/add";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("publisherName",publisher.getPublisherName());
        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(postMap, headers);
        restTemplate.postForObject(uriBuilder.toUriString(), request, Publisher.class);

    }

    @Override
    public void deletePublisher(int id, String token) {
        String url="http://localhost:8090/api/v1/management/publisher/delete/"+id;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
   
        restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,Publisher.class).getBody();

    }

    @Override
    public Publisher showPublisher(int id, String token) {
        String url="http://localhost:8090/api/v1/management/publisher/get/"+id;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
    
        return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,Publisher.class).getBody();
    }

    @Override
    public Publisher[] getAllPublisher(String token) {
        String url="http://localhost:8090/api/v1/management/publisher/get/all";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
    
        Publisher[] publishers = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,Publisher[].class).getBody();
        return publishers;
    }


    @Override
    public void updateAuthor(Author author, String token) {
        String url="http://localhost:8090/api/v1/management/author/edit/"+author.getAuthorID();
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("authorName",author.getAuthorName());
        postMap.put("authorLastName",author.getAuthorLastName());
        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(postMap, headers);
        restTemplate.postForObject(uriBuilder.toUriString(), request, Publisher.class);
    }

    @Override
    public void updateSubject(Subject subject, String token) {

        String url="http://localhost:8090/api/v1/management/subject/edit/"+subject.getSubjectID();
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("subjectName",subject.getSubjectName());
        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(postMap, headers);
        restTemplate.postForObject(uriBuilder.toUriString(), request, Subject.class);

    }

    @Override
    public void updateItemType(ItemType itemType, String token) {

        String url="http://localhost:8090/api/v1/management/itemtype/edit/"+itemType.getTypeID();
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("typeName",itemType.getTypeName());
        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(postMap, headers);
        restTemplate.postForObject(uriBuilder.toUriString(), request, ItemType.class);
    }

    @Override
    public void updateItem(Items items, String token) {

    }

    @Override
    public void updatePublisher(Publisher publisher,String token) {
        String url="http://localhost:8090/api/v1/management/publisher/edit/"+publisher.getPublisherID();
        System.out.println(url) ;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("publisherName",publisher.getPublisherName());
        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(postMap, headers);
        restTemplate.postForObject(uriBuilder.toUriString(), request, Publisher.class);
    }

    @Override
    public Items[] searchItemKeyword(String keyword, String token) {
        String url="http://localhost:8090/api/v1/management/item/get/search/"+keyword;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);

        Items[] items = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,entity,Items[].class).getBody();
        return items;
    }


    @Override
    public Items[] searchbyPost(Search search, String token) {
        String url="http://localhost:8090/api/v1/management/item/search/post";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(url);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> postMap = new HashMap<>();
        HttpEntity<Search> request = new HttpEntity<>(search, headers);
        return restTemplate.postForObject(uriBuilder.toUriString(), request, Items[].class);
        }
}
