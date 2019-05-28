package com.oc.climbingtoo.entity;

import com.oc.climbingtoo.enumeration.RoleUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "password")
    @Transient
    private String password;

    @Column(name = "user_name", unique = true, nullable = false)
    @NotEmpty(message = "Please provide your user name")
    private String userName;


    @Column(name = "enabled")
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;

    public List<GrantedAuthority> getRoleAsGrantedAuthorities () {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(this.getRoleUser().toString());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(grantedAuthority);
        return grantedAuthorities;
    }
}
