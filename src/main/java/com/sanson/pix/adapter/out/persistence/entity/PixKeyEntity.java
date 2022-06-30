package com.sanson.pix.adapter.out.persistence.entity;

import com.sanson.pix.domain.managerPix.pixKeys.PixType;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pix_key")
public class PixKeyEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String value;

    @Enumerated(EnumType.STRING)
    private PixType type;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "disabled_at")
    private LocalDateTime disabledAt;

    public PixKeyEntity(UUID id, String value, PixType type, LocalDateTime createdAt, LocalDateTime disabledAt) {
        this.id = id;
        this.value = value;
        this.type = type;
        this.createdAt = createdAt;
        this.disabledAt = disabledAt;
    }

    public PixKeyEntity(String value, PixType type) {
        this.id = UUID.randomUUID();
        this.value = value;
        this.type = type;
        this.createdAt = LocalDateTime.now();
    }

    protected PixKeyEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PixType getType() {
        return type;
    }

    public void setType(PixType type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDisabledAt() {
        return disabledAt;
    }

    public void setDisabledAt(LocalDateTime disabledAt) {
        this.disabledAt = disabledAt;
    }
}
