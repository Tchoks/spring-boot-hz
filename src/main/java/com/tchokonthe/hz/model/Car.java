package com.tchokonthe.hz.model;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

/**
 * @author martin
 * @created on 11/11/2021 at 16:58
 * @project com.tchokonthe.hz
 * @email (martin.aurele12 @ gmail.com)
 */


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Car implements DataSerializable {

    private String name;
    private String model;

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeString(name);
        out.writeString(model);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        name = in.readString();
        model = in.readString();

    }
}
