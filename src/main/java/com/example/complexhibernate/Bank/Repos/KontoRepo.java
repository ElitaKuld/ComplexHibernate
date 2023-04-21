package com.example.complexhibernate.Bank.Repos;


import com.example.complexhibernate.Bank.Models.Konto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KontoRepo extends JpaRepository<Konto, Long> {


}
