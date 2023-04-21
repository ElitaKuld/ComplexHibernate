package com.example.complexhibernate.Bank.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn
    private Kategori kategori;

    public Kund(String name, String födelsenummer, Kpi kpi, Kategori kategori) {
        this.name = name;
        this.födelsenummer = födelsenummer;
        this.kpi = kpi;
        this.kategori = kategori;
    }
}
