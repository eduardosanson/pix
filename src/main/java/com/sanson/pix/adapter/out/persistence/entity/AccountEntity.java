package com.sanson.pix.adapter.out.persistence.entity;

import com.sanson.pix.domain.managerPix.AccountType;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "account")
@NamedQuery(name = "AccountEntity.findByPixKeyEntitiesId",
        query = "select a from AccountEntity a, PixKeyEntity p " +
                "where p.id = ?1")
public class AccountEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "holder_id", referencedColumnName = "id")
    private HolderEntity holderEntity;

    @Column
    private Integer number;

    @Column
    private Integer agency;

    @Enumerated(EnumType.STRING)
    @Column
    private AccountType type;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private List<PixKeyEntity> pixKeyEntities;

    public List<PixKeyEntity> getPixKeys() {
        return pixKeyEntities;
    }

    public void setPixKeys(List<PixKeyEntity> pixKeyEntities) {
        this.pixKeyEntities = pixKeyEntities;
    }

    public AccountEntity(UUID id, HolderEntity holderEntity, Integer number,
                         Integer agency, AccountType type, List<PixKeyEntity> pixKeyEntities) {
        this.id = id;
        this.holderEntity = holderEntity;
        this.number = number;
        this.agency = agency;
        this.type = type;
        this.pixKeyEntities = pixKeyEntities;
    }

    public AccountEntity(HolderEntity holderEntity, Integer number,
                         Integer agency, AccountType type, List<PixKeyEntity> pixKeyEntities) {
        this.id = UUID.randomUUID();
        this.holderEntity = holderEntity;
        this.number = number;
        this.agency = agency;
        this.type = type;
        this.pixKeyEntities = pixKeyEntities;
    }

    protected AccountEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public HolderEntity getHolder() {
        return holderEntity;
    }

    public void setHolder(HolderEntity holderEntity) {
        this.holderEntity = holderEntity;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getAgency() {
        return agency;
    }

    public void setAgency(Integer agency) {
        this.agency = agency;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
}
