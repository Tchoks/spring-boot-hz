package com.tchokonthe.hz.common;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.tchokonthe.hz.model.Car;
import com.tchokonthe.hz.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author martin
 * @created on 21/11/2021 at 11:24
 * @project com.tchokonthe.hz
 * @email (martin.aurele12 @ gmail.com)
 */



@Slf4j
@ActiveProfiles("test")
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractTest {


    @Autowired
    protected TestRestTemplate testRestTemplate;

    @Autowired
    protected CarService carService;

    @Autowired
    protected IMap<String, Car> carMap;


    @Autowired
    protected HazelcastInstance hazelcastInstance;

    @LocalServerPort
    protected int port;


    @Test
    public void isUp() {
        log.info("Launching Tests");
    }


    protected String url(String path) {
        return "http://localhost:" + port + path;
    }


    protected HttpEntity<Void> getEntity() {
        return new HttpEntity<>(httpHeaders());
    }

    protected HttpHeaders httpHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }

    @NotNull
    protected ParameterizedTypeReference<List<Car>> getTypeReference() {
        return new ParameterizedTypeReference<>() {};
    }
}
