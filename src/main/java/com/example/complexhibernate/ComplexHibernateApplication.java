package com.example.complexhibernate;

import com.example.complexhibernate.Bank.Models.Kpi;
import com.example.complexhibernate.Bank.Models.Kund;
import com.example.complexhibernate.Bank.Repos.KpiRepo;
import com.example.complexhibernate.Bank.Repos.KundRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ComplexHibernateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComplexHibernateApplication.class, args);
    }

    /*

    //Denna Bootstrapping-metod används med 1-1 relationen
    @Bean
    public CommandLineRunner demo(KpiRepo kpirepo, KundRepo kundrepo){
        return (args )-> {
            Kpi k1 = new Kpi(45);
            Kpi k2 = new Kpi(76);
            Kpi k3 = new Kpi(3);

            kpirepo.save(k1);
            kpirepo.save(k2);
            kpirepo.save(k3);

            Kund kund1 = new Kund("Lilly", "435255", k1);
            Kund kund2 = new Kund("Jimmy", "436228", k2);
            Kund kund3 = new Kund("Timmy", "438211", k3);

            kundrepo.save(kund1);
            kundrepo.save(kund2);
            kundrepo.save(kund3);
        };
    }
    */

    //Denna Bootstrapping-metod passar med Cascade
    @Bean
    public CommandLineRunner demo(KundRepo kundrepo) {
        return (args) -> {
            Kpi k1 = new Kpi(45);
            Kpi k2 = new Kpi(76);
            Kpi k3 = new Kpi(3);

            Kund kund1 = new Kund("Lilly", "435255", k1);
            Kund kund2 = new Kund("Jimmy", "436228", k2);
            Kund kund3 = new Kund("Timmy", "438211", k3);

            kundrepo.save(kund1);
            kundrepo.save(kund2);
            kundrepo.save(kund3);
        };
    }

    /*
//Denna Bootstrapping-metod används med 1-N relationen
    @Bean
    public CommandLineRunner demo(KpiRepo kpiRepo, KundRepo kundRepo, KategoriRepo katRepo){
        return (args) -> {
            Kpi k1 = new Kpi(45);
            Kpi k2 = new Kpi(76);
            Kpi k3 = new Kpi(3);

            Kategori kat1 = new Kategori("guld");
            Kategori kat2 = new Kategori("silver");
            Kategori kat3 = new Kategori("brons");

            //Vill inte cascada kategorierna pga N-1, och pga "on delete cascade" inte önskvärt beteende
            katRepo.save(kat1);
            katRepo.save(kat2);
            katRepo.save(kat3);

            Kund kund1 = new Kund("Mimmi", "435255", k1, kat1);
            Kund kund2 = new Kund("Börje", "435255", k2, kat1);
            Kund kund3 = new Kund("Elvis", "435255", k3, kat3);
            
            kundRepo.save(kund1);
            kundRepo.save(kund2);
            kundRepo.save(kund3);
        };
    } */

}
