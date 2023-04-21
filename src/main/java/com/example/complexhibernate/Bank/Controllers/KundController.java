package com.example.complexhibernate.Bank.Controllers;

import com.example.complexhibernate.Bank.Models.Kategori;
import com.example.complexhibernate.Bank.Models.Kpi;
import com.example.complexhibernate.Bank.Models.Kund;
import com.example.complexhibernate.Bank.Repos.KategoriRepo;
import com.example.complexhibernate.Bank.Repos.KpiRepo;
import com.example.complexhibernate.Bank.Repos.KundRepo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KundController {
    private final KundRepo kundRepo;
    private final KpiRepo kpiRepo;
    private final KategoriRepo kategoriRepo;

    KundController(KpiRepo kpiRepo, KundRepo kundRepo, KategoriRepo kategoriRepo) {
        this.kundRepo = kundRepo;
        this.kpiRepo = kpiRepo;
        this.kategoriRepo = kategoriRepo;
    }

    @RequestMapping("kunder")
    public List<Kund> getAllCustomers() {
        return kundRepo.findAll();
    }

    @RequestMapping("kunder/add")
    public String addCustomer(@RequestParam String namn, @RequestParam String fodelsenummer, @RequestParam Long id) {
        Kpi kpi = kpiRepo.findById(id).get();
        kundRepo.save(new Kund(namn, fodelsenummer, kpi));
        return "kund " + namn + " added";
    }

    @RequestMapping("kunder/add2")
    public String addCustomer2(@RequestParam String namn, @RequestParam String fodelsenummer, @RequestParam int credit) {
        Kpi kpi = new Kpi(credit);
        //    kpiRepo.save(kpi); // Behöver inte när vi har lagt in cascading
        kundRepo.save(new Kund(namn, fodelsenummer, kpi));
        return "kund " + namn + " added";
    }

    @RequestMapping("kunder/{id}")
    public Kund getCustomerById(@PathVariable long id) {
        return kundRepo.findById(id).get();
    }

    @RequestMapping("kunder/delete/{id}")
    public String deleteCustomerById(@PathVariable long id) {
        kundRepo.deleteById(id);
        return "kund " + id + " togs bort";
    }

    @RequestMapping("kunder/add3")
    public String addCustomer3(@RequestParam String namn, @RequestParam String fodelsenummer, @RequestParam int credit, @RequestParam Long kategoriid) {
        Kategori kategori = kategoriRepo.findById(kategoriid).get();
        kundRepo.save(new Kund(namn, fodelsenummer, new Kpi(credit), kategori));
        return "kund " + namn + " added";
    }
}
