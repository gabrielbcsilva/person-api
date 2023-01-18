package com.github.gabrielbcsilva.personapi.model;

import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_PERSON")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 50, nullable = false)
    private String name;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "BIRTHDATE",nullable = false)
    private LocalDate birthDate;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "principalAdress", nullable = true)
    private Adress principalAdress;
}
