package com.tchokonthe.hz.resources;

import com.tchokonthe.hz.container.HazelcastContainer;
import com.tchokonthe.hz.service.CarServiceTest;
import org.junit.ClassRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * @author martin
 * @created on 20/11/2021 at 12:55
 * @project com.tchokonthe.hz
 * @email (martin.aurele12 @ gmail.com)
 */


@Testcontainers
public class CarResourcesTest extends AbstractCarResourcesTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceTest.class);

    @ClassRule
    public static HazelcastContainer container = HazelcastContainer.getInstance()
            .withExposedPorts(5701)
            .withLogConsumer(new Slf4jLogConsumer(LOGGER));

//    @DynamicPropertySource
//    public static void containerProperties(DynamicPropertyRegistry registry) throws IOException, InterruptedException {
//        registry.add("HZ_NETWORK_PUBLICADDRESS", container::getHost);
//        registry.add("HZ_NETWORK_PORT_PORT", container::getExposedPorts);
//    }



}
