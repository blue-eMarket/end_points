package com.codathon.blue_eMatket_api.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
@Data
public class Role extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;
    private String roleName;
    private int status;
    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
    private List<Users> userList;


}
