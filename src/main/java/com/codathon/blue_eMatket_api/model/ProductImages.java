package com.codathon.blue_eMatket_api.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class ProductImages extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prImageId;
    private String productImage;
    private int isPrimary;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "productCode", referencedColumnName = "productCode")
    private Product product;
}
