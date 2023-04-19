package com.example.complexhibernate.Bank.Controllers;

import com.example.complexhibernate.Bank.Models.Kpi;
import com.example.complexhibernate.Bank.Models.Kund;
import com.example.complexhibernate.Bank.Repos.KpiRepo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KpiController {
    private final KpiRepo repo;

    KpiController(KpiRepo repo){
        this.repo=repo;
    }

    @RequestMapping("kpis")
    public List<Kpi> getAllKpis(){
        return repo.findAll();
    }

    @RequestMapping("kpis/add")
    public String addKpi(@RequestParam int credit){
        repo.save(new Kpi(credit));
        return "kpi " + credit + " added";
    }

    @RequestMapping("kpis/{id}")
    public Kpi getKpiById(@PathVariable long id){
        return repo.findById(id).get();
    }
}