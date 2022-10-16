package com.codathon.blue_eMatket_api.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Product extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String productCode;
    private String productName;
    private String price;
    private int status;
    private int rateId;
    private String productDescription;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "prCategoryId",referencedColumnName = "prCategoryId")
    private ProductCategory productCategory;

//    @ManyToOne(cascade = CascadeType.REFRESH)
//    @JoinColumn(name = "discountId", referencedColumnName = "discountId")
//    private Discount discount;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    private List<ProductImages> productImages;
}
