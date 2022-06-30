package com.sanson.pix.domain.managerPix;

import com.sanson.pix.domain.Error;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isEmpty;

public class Holder {

    private UUID id;

    private String name;

    private String lastName;

    private HolderType type;

    public Holder(String name, String lastName, HolderType type) {
        if (isEmpty(name) || name.length() > 30) {
            Error.invalid("holder name");
        }
        if (isNull(type)){
            Error.invalid("holder type");
        }
        if ( !isNull(lastName) && lastName.length() > 45){
            Error.invalid("holder last name");
        }
        this.id = UUID.randomUUID();
        this.name = name;
        this.lastName = lastName;
        this.type = type;
    }
    public Holder(UUID id, String name, String lastName, HolderType type) {
        this(name, lastName, type);
        this.id = id;
    }
    public HolderType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName==null ? "" : lastName;
    }

    public UUID getId() {
        return id;
    }

}
