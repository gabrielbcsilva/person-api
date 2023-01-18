package com.github.gabrielbcsilva.personapi.model;

import java.math.BigDecimal;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_ADRESS"
, uniqueConstraints={@UniqueConstraint( name = "iX_CEP_PERSON",  columnNames ={"cep","person_id"})}
)
@Getter
@Setter 
public class Adress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;


    @Column(name = "STREET", length = 500, nullable = false)
    private String street;
    @Column(name = "CEP", length = 500, nullable = false)
    private String cep;
    @Column(name = "NUMBER", length = 500, nullable = false)
    private BigDecimal number;

    @Column(name = "CITY", length = 500, nullable = false)
    private String city;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="person_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Person person ;
}
