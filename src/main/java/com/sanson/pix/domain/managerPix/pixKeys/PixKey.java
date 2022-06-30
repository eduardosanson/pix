package com.sanson.pix.domain.managerPix.pixKeys;

import com.sanson.pix.domain.Error;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class PixKey {
    private String value;

    private PixType type;

    private UUID id;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime disabledAt;

    public PixKey(String value, PixType type) {
        this.id = UUID.randomUUID();
        this.type = type;
        if(value!=null && is_valid(value)){
            this.value = value;
        } else {
            Error.invalid(type.getValue());
        }
    }

    public PixKey(String value, PixType type, UUID id) {
        this.value = value;
        this.type = type;
        this.id = id;
    }

    public PixKey(UUID id,PixType type,String value, LocalDateTime createdAt) {
        this.value = value;
        this.id = id;
        this.type = type;
        this.createdAt = createdAt;
    }

    abstract Boolean is_valid(String value);

    public String getValue() {
        return value;
    }

    public PixType getType() {
        return type;
    }

    public UUID getId() {
        return id;
    }

    public void disable(){
        this.disabledAt = LocalDateTime.now();
    }

    public LocalDateTime getDisabledAt() {
        return disabledAt;
    }

    public LocalDate getDisabledAtAsDate() {
        if (disabledAt == null) {
            return null;
        }
        return disabledAt.toLocalDate();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PixKey pixKey = (PixKey) o;

        return value.equals(pixKey.value);
    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + createdAt.hashCode();
        result = 31 * result + (disabledAt != null ? disabledAt.hashCode() : 0);
        return result;
    }
}
