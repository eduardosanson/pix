package com.sanson.pix.adapter.out.persistence.entity;

import com.sanson.pix.domain.managerPix.HolderType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "holder")
public class HolderEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private HolderType type;

    public HolderEntity(UUID id, String name, String lastName, HolderType type) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.type = type;
    }

    public HolderEntity(String name, String lastName, HolderType type) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.lastName = lastName;
        this.type = type;
    }

    protected HolderEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public HolderType getType() {
        return type;
    }

    public void setType(HolderType type) {
        this.type = type;
    }
}
