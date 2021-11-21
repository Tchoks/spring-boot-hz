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
 * @created on 11/11/2021 at 20:27
 * @project com.tchokonthe.hz
 * @email (martin.aurele12 @ gmail.com)
 */


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Customer implements DataSerializable {

    private String name;
    private String address;
    private String profession;

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeString(name);
        out.writeString(address);
        out.writeString(profession);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        name = in.readString();
        address = in.readString();
        profession = in.readString();
    }
}
