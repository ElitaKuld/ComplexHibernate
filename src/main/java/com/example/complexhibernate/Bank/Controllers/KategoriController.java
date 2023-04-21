package com.example.complexhibernate.Bank.Controllers;

import com.example.complexhibernate.Bank.Models.Kategori;
import com.example.complexhibernate.Bank.Models.Kund;
import com.example.complexhibernate.Bank.Repos.KategoriRepo;
import com.example.complexhibernate.Bank.Repos.KundRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KategoriController {
    private final KategoriRepo kategoriRepo;
    private final KundRepo kundRepo;


    public KategoriController(KategoriRepo kategoriRepo, KundRepo kundRepo) {
        this.kategoriRepo = kategoriRepo;
        this.kundRepo = kundRepo;
    }

    @RequestMapping("kategorier")
    public List<Kategori> getAllKategorier() {
        return kategoriRepo.findAll();
    }

    @RequestMapping("kategorier/add")
    public String addKategori(@RequestParam String namn) {
        kategoriRepo.save(new Kategori(namn));
        return "Kategori " + namn + " lades till";
    }

    @RequestMapping("kategorier/delete/{id}")
    public String deleteKategori(@PathVariable Long id) {
        kategoriRepo.deleteById(id);
        return "kategori " + id + " togs bort";
    }

    @RequestMapping("kategorier/{id}")
    public Kategori getCategoryById(@PathVariable long id) {
        return kategoriRepo.findById(id).get();
    }

    /*
    @PostMapping("/kategorier/addByPost")
    public String addBookByPOST(@RequestBody Kategori k){
        kategoriRepo.save(k);
        return "Kategori "+k.getName()+" lades till";
    }*/

}
