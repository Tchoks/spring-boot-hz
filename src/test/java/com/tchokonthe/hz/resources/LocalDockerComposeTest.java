package com.tchokonthe.hz.resources;

import com.tchokonthe.hz.container.LocalDockerComposeContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author martin
 * @created on 21/11/2021 at 10:42
 * @project com.tchokonthe.hz
 * @email (martin.aurele12 @ gmail.com)
 */


@Testcontainers
public class LocalDockerComposeTest extends AbstractCarResourcesTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalDockerComposeTest.class);

    private static final int HAZELCAST_PORT = 5701;
    public static final String SERVICE_NAME = "hazelcast_1";

    @ClassRule
    public static LocalDockerComposeContainer container = new LocalDockerComposeContainer()
            .withExposedService(SERVICE_NAME, HAZELCAST_PORT)
            .withLogConsumer(SERVICE_NAME, new Slf4jLogConsumer(LOGGER));



//    @After
//    public void tearDown() {
//        container.stop();
//    }


}
