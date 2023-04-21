package com.example.complexhibernate.Bank.Controllers;

import com.example.complexhibernate.Bank.Models.Kategori;
import com.example.complexhibernate.Bank.Models.Konto;
import com.example.complexhibernate.Bank.Models.Kpi;
import com.example.complexhibernate.Bank.Models.Kund;
import com.example.complexhibernate.Bank.Repos.KategoriRepo;
import com.example.complexhibernate.Bank.Repos.KontoRepo;
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
    private final KontoRepo kontoRepo;

    KundController(KpiRepo kpiRepo, KundRepo kundRepo, KategoriRepo kategoriRepo, KontoRepo kontoRepo) {
        this.kundRepo = kundRepo;
        this.kpiRepo = kpiRepo;
        this.kategoriRepo = kategoriRepo;
        this.kontoRepo = kontoRepo;
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

    //Nedanstående hör till N-M-relationen
    @RequestMapping("kunder/addKonto")
    public String addKonto(@RequestParam Long kundId, @RequestParam int saldo,
                           @RequestParam int ranta) {
        Kund kund = kundRepo.findById(kundId).get();
        if (kund != null) {
            kund.addKonto(new Konto(saldo, ranta));
            kundRepo.save(kund);
        }
        return "konto lades till hos kund med id " + kundId;
    }

    @RequestMapping("kunder/addKonto2")
    public String addKonto(@RequestParam Long kundId, @RequestParam Long kontoId) {
        Kund kund = kundRepo.findById(kundId).get();
        Konto konto = kontoRepo.findById(kontoId).get();
        if (kund != null && konto != null) {
            kund.addKonto(konto);
            kundRepo.save(kund);
        }
        return "konto lades till hos kund med id " + kundId;
    }

    @RequestMapping("kunder/changeKonto")
    public String changeKonto(@RequestParam Long kundId, @RequestParam Long kontoId, @RequestParam int saldo) {
        Kund kund = kundRepo.findById(kundId).get();
        Konto konto = kontoRepo.findById(kontoId).get();
        if (kund != null && konto != null) {
            kund.getKonto().stream().filter(kundKonto -> kundKonto.getId() == konto.getId()).findAny().get().setSaldo(saldo);
            kundRepo.save(kund);
        }
        return "konto (saldo) ändrades hos kund med id " + kundId;
    }

    @RequestMapping("kunder/changeKonto2")
    public String changeKonto2(@RequestParam Long kundId, @RequestParam Long kontoId, @RequestParam int ranta) {
        Kund kund = kundRepo.findById(kundId).get();
        Konto konto = kontoRepo.findById(kontoId).get();
        if (kund != null && konto != null) {
            kund.getKonto().stream().filter(kundKonto -> kundKonto.getId() == konto.getId()).findAny().get().setRanta(ranta);
            kundRepo.save(kund);
        }
        return "konto (ränta) ändrades hos kund med id " + kundId;
    }
}
