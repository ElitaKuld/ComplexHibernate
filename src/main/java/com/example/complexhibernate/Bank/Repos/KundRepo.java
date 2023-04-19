package com.example.complexhibernate.Bank.Repos;

import com.example.complexhibernate.Bank.Models.Kund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KundRepo extends JpaRepository<Kund, Long> {
}
