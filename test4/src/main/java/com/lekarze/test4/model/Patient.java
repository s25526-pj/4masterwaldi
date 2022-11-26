package com.lekarze.test4.model;


import lombok.*;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@SQLDelete(sql = "UPDATE patient SET deleted = true WHERE id=?")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String surname;

    @Column(unique = true)
    private long pesel;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "patient")
    private Set<Visit> visits;

    private boolean deleted = false;

}
