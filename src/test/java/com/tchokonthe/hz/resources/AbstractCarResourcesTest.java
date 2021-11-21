package com.tchokonthe.hz.resources;

import com.tchokonthe.hz.common.AbstractTest;
import com.tchokonthe.hz.model.Car;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author martin
 * @created on 21/11/2021 at 11:58
 * @project com.tchokonthe.hz
 * @email (martin.aurele12 @ gmail.com)
 */

public class AbstractCarResourcesTest extends AbstractTest {

    @Before
    public void setup() {
//        container.start();
        carMap.clear();
        carService.init();
    }

    @Test
    public void testFindAll() {
        ResponseEntity<List<Car>> entities = testRestTemplate.exchange(url("/api/cars"), HttpMethod.GET, getEntity(), getTypeReference());
        assertThat(entities.getStatusCodeValue()).isEqualTo(200);
        assertThat(entities.getBody().size()).isEqualTo(2);
    }

    @Test
    public void testFindCarByName() {
        final String cupra_formentor = "Cupra Formentor";
        ResponseEntity<Car> entities = testRestTemplate.exchange(url("/api/cars/" + cupra_formentor), HttpMethod.GET, getEntity(), Car.class);
        assertThat(entities.getStatusCodeValue()).isEqualTo(200);
        assertThat(entities.getBody().getName()).isEqualTo(cupra_formentor);
        assertThat(entities.getBody().getModel()).isEqualTo("SUV");
    }

    @Test
    public void testFindCarByModel() {
        ResponseEntity<List<Car>> entities = testRestTemplate.exchange(url("/api/cars/all?model=" + "SUV"), HttpMethod.GET, getEntity(), getTypeReference());
        assertThat(entities.getStatusCodeValue()).isEqualTo(200);
        assertThat(entities.getBody().size()).isEqualTo(2);
    }

    @Test
    public void testAddCars() {
        final HttpEntity<List<Car>> body = new HttpEntity<>(List.of(new Car("BMW Serie 1", "Berline"), new Car("Audi", "Berline")), httpHeaders());
        final ResponseEntity<List<Car>> entities = testRestTemplate.exchange(url("/api/cars"), HttpMethod.POST, body, getTypeReference());
        assertThat(entities.getStatusCodeValue()).isEqualTo(200);
        assertThat(carMap.size()).isEqualTo(4);
    }

    @Test
    public void deleteCar() {
        final String cupra_formentor = "Cupra Formentor";
        ResponseEntity<Car> entity = testRestTemplate.exchange(url("/api/cars/" + cupra_formentor), HttpMethod.DELETE, getEntity(), Car.class);
        assertThat(entity.getStatusCodeValue()).isEqualTo(200);
        assertThat(entity.getBody().getName()).isEqualTo(cupra_formentor);
    }
}
