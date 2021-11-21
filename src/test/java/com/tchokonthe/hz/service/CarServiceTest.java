package com.tchokonthe.hz.service;


import com.tchokonthe.hz.common.AbstractTest;
import com.tchokonthe.hz.container.HazelcastContainer;
import com.tchokonthe.hz.model.Car;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.util.List;


/**
 * @author martin
 * @created on 20/11/2021 at 11:14
 * @project com.tchokonthe.hz
 * @email (martin.aurele12 @ gmail.com)
 */


@Testcontainers
public class CarServiceTest extends AbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceTest.class);

    @ClassRule
    public static HazelcastContainer container = HazelcastContainer.getInstance()
            .withExposedPorts(5701)
            .withLogConsumer(new Slf4jLogConsumer(LOGGER));

    @DynamicPropertySource
    public static void containerProperties(DynamicPropertyRegistry registry) throws IOException, InterruptedException {
        registry.add("HZ_NETWORK_PUBLICADDRESS", container::getHost);
        registry.add("HZ_NETWORK_PORT_PORT", container::getExposedPorts);
    }

    @Before
    public void setup() {
//        container.start();
        carMap.clear();
        carService.init();
    }

//    @After
//    public void tearDown() {
//        container.stop();
//    }


    @Test
    public void testFindAll() {
        final List<Car> cars = carService.findAllCars();
        Assertions.assertEquals(carMap.size(), cars.size());
    }

    @Test
    public void testFindCarByName() {
        final String cupra_formentor = "Cupra Formentor";
        final Car formentor = carService.findCarByName(cupra_formentor);
        Assertions.assertEquals(cupra_formentor, formentor.getName());
        Assertions.assertEquals("SUV", formentor.getModel());
    }

    @Test
    public void testFindCarByModel() {
        final List<Car> cars = carService.findCarByModel("SUV");
        Assertions.assertEquals(carMap.size(), cars.size());
    }

    @Test
    public void testAddCars() {
        carService.addCars(List.of(new Car("BMW Serie 1", "Berline"), new Car("Audi", "Berline")));
        Assertions.assertEquals(carMap.size(), carService.findAllCars().size());
    }

    @Test
    public void deleteCar() {
        carService.deleteCar("Cupra Formentor");
        Assertions.assertEquals(carMap.size(), carService.findAllCars().size());
    }


}
