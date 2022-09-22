package com.example.demo.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Entity
@Table(name = "cart")
@Data
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Long id;

    @ManyToMany
    @JsonProperty
    @Column
    private List<ItemEntity> items;

    @OneToOne(mappedBy = "cart")
    @JsonProperty
    private UserEntity user;

    @Column
    @JsonProperty
    private BigDecimal total;

    public void addItem(ItemEntity item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
        if (total == null) {
            total = new BigDecimal(0);
        }
        total = total.add(item.getPrice());
    }

    public void removeItem(ItemEntity item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.remove(item);
        if (total == null) {
            total = new BigDecimal(0);
        }
        total = total.subtract(item.getPrice());
    }
}
