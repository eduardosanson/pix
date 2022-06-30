package com.sanson.pix.adapter.out.persistence;

import com.sanson.pix.adapter.out.persistence.entity.AccountEntity;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    @Query(value="select * from account a join pix_key pk on a.id = pk.account_id " +
                 "join holder h on a.holder_id = h.id " +
                 "where pk.id = :id and pk.disabled_at is null", nativeQuery=true)
    Optional<AccountEntity> findByPixKeyEntitiesId(UUID id);

    Optional<AccountEntity> findByAgencyAndNumber(Integer agency, Integer number);
}
