package com.codathon.blue_eMatket_api.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Vendor  extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String vendorId;
    private String email;
    private String phoneNumber;
    private String address;
    private String NIDA;
    private String ZanId;
    private int status;
    @OneToMany(mappedBy = "vendor",fetch = FetchType.LAZY)
    private List<Product> products;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "vendorTypeId", referencedColumnName = "vendorTypeId")
    private VendorType vendorType;
}
