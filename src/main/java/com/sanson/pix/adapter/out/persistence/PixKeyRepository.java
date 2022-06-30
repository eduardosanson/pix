package com.sanson.pix.adapter.out.persistence;

import com.sanson.pix.adapter.out.persistence.entity.PixKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PixKeyRepository extends JpaRepository<PixKeyEntity, UUID> {
}
