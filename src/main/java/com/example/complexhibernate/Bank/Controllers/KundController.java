package com.example.complexhibernate.Bank.Controllers;

import com.example.complexhibernate.Bank.Models.Kpi;
import com.example.complexhibernate.Bank.Models.Kund;
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

    KundController(KpiRepo kpiRepo, KundRepo kundRepo){
        this.kundRepo=kundRepo;
        this.kpiRepo=kpiRepo;
    }

    @RequestMapping("kunder")
    public List<Kund> getAllCustomers(){
        return kundRepo.findAll();
    }

    @RequestMapping("kunder/add")
    public String addCustomer(@RequestParam String namn, @RequestParam String fodelsenummer,@RequestParam Long id){
        Kpi kpi = kpiRepo.findById(id).get();
        kundRepo.save(new Kund(namn,fodelsenummer, kpi));
        return "kund " + namn + " added";
    }

    @RequestMapping("kunder/add2")
    public String addCustomer2(@RequestParam String namn, @RequestParam String fodelsenummer, @RequestParam int credit){
        Kpi kpi = new Kpi(credit);
        kpiRepo.save(kpi);
        kundRepo.save(new Kund(namn,fodelsenummer, kpi));
        return "kund " + namn + " added";
    }

    @RequestMapping("kunder/{id}")
    public Kund getCustomerById(@PathVariable long id){
        return kundRepo.findById(id).get();
    }
}
