package com.lekarze.test4.model;


import lombok.*;
import org.hibernate.annotations.SQLDelete;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@SQLDelete(sql = "UPDATE doctor SET deleted = true WHERE id=?")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;

    @Column(unique = true)
    private long nip;

    @OneToMany(mappedBy = "doctor")
    private Set<Visit> visits;

    private boolean deleted = false;

}
