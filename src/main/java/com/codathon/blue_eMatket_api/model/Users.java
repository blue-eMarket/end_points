package com.codathon.blue_eMatket_api.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Users extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String userId;
    private String userName;
    private int status;
    private String password;
    private String profile;
    private String vendorId;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "roleId", referencedColumnName = "roleId")
    private Role role;

}