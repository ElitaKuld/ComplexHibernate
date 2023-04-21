package com.example.complexhibernate.Bank.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Kund {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String födelsenummer;

    @OneToOne(cascade = CascadeType.ALL)
    //Om man vill ange något annat namn till sina foreign keys: @JoinColumn(name="myFK", referencedColumnName = "kreditvärdighet")
    @JoinColumn//Default: FK-kolumn kommer att heta kpi_id
    private Kpi kpi;

    public Kund(String name, String födelsenummer, Kpi kpi) {
        this.name = name;
        this.födelsenummer = födelsenummer;
        this.kpi = kpi;
    }

    //Nedanstående hör till N-1-relationen
    @ManyToOne
    @JoinColumn
    private Kategori kategori;

    public Kund(String name, String födelsenummer, Kpi kpi, Kategori kategori) {
        this.name = name;
        this.födelsenummer = födelsenummer;
        this.kpi = kpi;
        this.kategori = kategori;
    }

    //Nedanstående hör till N-M-relationen
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<Konto> konto = new ArrayList<>();

    public void addKonto(Konto k){
        konto.add(k);
    }
}
