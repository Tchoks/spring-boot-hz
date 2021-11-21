package com.tchokonthe.hz.listeners;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.EntryListener;
import com.hazelcast.map.MapEvent;
import com.tchokonthe.hz.model.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author martin
 * @created on 11/11/2021 at 19:50
 * @project com.tchokonthe.hz
 * @email (martin.aurele12 @ gmail.com)
 */


@Slf4j
@Component
public class CarEventListener implements EntryListener<String, Car> {


    @Override
    public void entryAdded(EntryEvent<String, Car> event) {
        log.info(event.getKey() + " added");
    }

    @Override
    public void entryEvicted(EntryEvent<String, Car> event) {
        log.info(event.getKey() + " evicted");
    }

    @Override
    public void entryExpired(EntryEvent<String, Car> event) {
        log.info(event.getKey() + " expired");
    }

    @Override
    public void entryRemoved(EntryEvent<String, Car> event) {
        log.info(event.getKey() + " removed");
    }

    @Override
    public void entryUpdated(EntryEvent<String, Car> event) {
        log.info(event.getKey() + " updated");
    }

    @Override
    public void mapCleared(MapEvent event) {
        log.info(event.getName() + " cleared");
    }

    @Override
    public void mapEvicted(MapEvent event) {
        log.info(event.getName() + " evicted");
    }
}
