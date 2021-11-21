package com.tchokonthe.hz.container;

import org.testcontainers.containers.GenericContainer;

/**
 * @author martin
 * @created on 20/11/2021 at 11:42
 * @project com.tchokonthe.hz
 * @email (martin.aurele12 @ gmail.com)
 */

public class HazelcastContainer extends GenericContainer<HazelcastContainer> {

    private static final String IMAGE_VERSION = "hazelcast/hazelcast";
    private static HazelcastContainer container;

    public  HazelcastContainer() {
        super(IMAGE_VERSION);
    }

    public static HazelcastContainer getInstance() {
        if (container == null) {
            container = new HazelcastContainer();
        }
        return container;
    }


    @Override
    public void start() {
        super.start();
        System.setProperty("HZ_NETWORK_PUBLICADDRESS", container.getHost());
        System.setProperty("HZ_NETWORK_PORT_PORT", String.valueOf(container.getFirstMappedPort()));
    }

    @Override
    public void stop() {
        super.stop();
        //do nothing, JVM handles shut down
    }


}
