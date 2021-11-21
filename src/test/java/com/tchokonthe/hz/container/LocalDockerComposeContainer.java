package com.tchokonthe.hz.container;

import org.testcontainers.containers.DockerComposeContainer;

import java.io.File;

/**
 * @author martin
 * @created on 21/11/2021 at 11:11
 * @project com.tchokonthe.hz
 * @email (martin.aurele12 @ gmail.com)
 */

public class LocalDockerComposeContainer extends DockerComposeContainer<LocalDockerComposeContainer> {

    private static LocalDockerComposeContainer container;

    public LocalDockerComposeContainer() {
        super(new File("src/test/resources/compose-test.yml"));
    }

    public LocalDockerComposeContainer getInstance() {
        if (container == null) {
            container = new LocalDockerComposeContainer();
        }
        return container;
    }


    @Override
    public void close() {
        super.close();
    }
}
