package com.tchokonthe.hz.resources;

import com.hazelcast.spring.cache.HazelcastCacheManager;
import com.tchokonthe.hz.model.Car;
import com.tchokonthe.hz.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author martin
 * @created on 11/11/2021 at 17:26
 * @project com.tchokonthe.hz
 * @email (martin.aurele12 @ gmail.com)
 */



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cars")
public class CarResources {

    private final CarService carService;
    private final HazelcastCacheManager hazelcastCacheManager;


    @GetMapping
    public List<Car> getCars() {
        return carService.findAllCars();
    }

    @GetMapping("/{name:.+}")
    public Car getCar(@PathVariable("name") String name) {
        return carService.findCarByName(name);
    }

    @GetMapping("/all")
    public List<Car> getCarsByModel(@RequestParam("model") String model) {
        return carService.findCarByModel(model);
    }

    @PostMapping
    public void addCars(@RequestBody List<Car> cars) {
        carService.addCars(cars);
    }

    @DeleteMapping("/{name}")
    public Car deleteCar(@PathVariable("name") String name) {
        return carService.deleteCar(name);
    }

}
