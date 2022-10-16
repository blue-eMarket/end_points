package com.codathon.blue_eMatket_api.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Discount extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int discountId;
    private String amount;
    private String fromDate;
    private String toDate;
    private int status;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "discountTypeId", referencedColumnName = "discountTypeId")
    private DiscountTypes discountTypes;

//    @OneToMany(mappedBy = "discount",fetch = FetchType.LAZY)
//    private List<Product> products;
}
