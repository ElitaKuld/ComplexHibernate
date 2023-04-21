package com.example.complexhibernate.Bank.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class Konto {
    @Id
    @GeneratedValue
    private @Getter @Setter Long Id;
    private @Getter @Setter int saldo;
    private @Getter @Setter int ranta;

    public Konto(int saldo, int ranta) {
        this.saldo = saldo;
        this.ranta = ranta;
    }
}
