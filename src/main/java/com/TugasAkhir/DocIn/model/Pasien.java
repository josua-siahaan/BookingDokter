package com.TugasAkhir.DocIn.model;

import jakarta.persistence.*;

@Entity
@Table(name = "MstPasien")
public class Pasien {

    @Id
    @Column(name = "IDPasien")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
