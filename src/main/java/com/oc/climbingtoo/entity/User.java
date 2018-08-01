package com.oc.climbingtoo.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter @Setter
    private Integer id;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
    @Getter @Setter
    private String email;

    @Column(name = "password")
    @Transient
    @Getter @Setter
    private String password;

    @Column(name = "first_name")
    @NotEmpty(message = "Please provide your first name")
    @Getter @Setter
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Please provide your last name")
    @Getter @Setter
    private String lastName;

    @Column(name = "enabled")
    @Getter @Setter
    private boolean enabled;

    @Column(name = "confirmation_token")
    @Getter @Setter
    private String confirmationToken;

}
