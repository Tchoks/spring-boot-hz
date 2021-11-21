package com.tchokonthe.hz.service;

import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicates;
import com.tchokonthe.hz.model.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author martin
 * @created on 11/11/2021 at 17:00
 * @project com.tchokonthe.hz
 * @email (martin.aurele12 @ gmail.com)
 */


@Service
@RequiredArgsConstructor
public class CarService {

    private final IMap<String, Car> carMap;

    @PostConstruct
    public void init() {
        final List<Car> cars = List.of(new Car("Cupra Formentor", "SUV"), new Car("Ds Crossback 7", "SUV"));
        carMap.putAll(cars.stream().collect(Collectors.toMap(Car::getName, Function.identity())));
    }

    public List<Car> findAllCars() {
        return new ArrayList<>(carMap.values());
    }

    public Car findCarByName(String name) {
        return carMap.get(name);
    }

    public List<Car> findCarByModel(String model) {
        return new ArrayList<>(carMap.values(Predicates.equal("model", model)));
    }

    public void addCars(List<Car> cars) {
        cars.forEach(car -> carMap.put(car.getName(), car));
    }

    public Car deleteCar(String name) {
        return carMap.remove(name);
    }

}
