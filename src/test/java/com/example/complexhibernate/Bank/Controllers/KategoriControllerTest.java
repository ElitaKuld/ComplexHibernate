package com.example.complexhibernate.Bank.Controllers;

import com.example.complexhibernate.Bank.Models.Kategori;
import com.example.complexhibernate.Bank.Repos.KategoriRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class KategoriControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private KategoriRepo mockRepo;

    @BeforeEach
    public void init() {
        Kategori k1 = new Kategori(1L, "guld", null);
        Kategori k2 = new Kategori(2L, "silver", null);
        Kategori k3 = new Kategori(3L, "brons", null);

        when(mockRepo.findById(1L)).thenReturn(Optional.of(k1));
        when(mockRepo.findById(2L)).thenReturn(Optional.of(k2));
        when(mockRepo.findById(3L)).thenReturn(Optional.of(k3));

        when(mockRepo.findAll()).thenReturn(Arrays.asList(k1, k2, k3));
    }

    @Test
    void getAllKategorier() throws Exception {
        this.mvc.perform(get("/kategorier")).andExpect(status().isOk()).
                andExpect(content().json("[{\"name\":\"guld\",\"id\":1,\"kunder\":null}," +
                        "{\"name\":\"silver\",\"kunder\":null,\"id\":2}," +
                        "{\"name\":\"brons\",\"kunder\":null,\"id\":3}]"));
    }

    @Test
    void addKategori() throws Exception {
        this.mvc.perform(get("/kategorier/add?namn=platinum")).andExpect(status().isOk()).
                andExpect(content().string(equalTo("Kategori platinum lades till")));
    }

    @Test
    void deleteKategori() throws Exception {
        this.mvc.perform(get("/kategorier/delete/1")).andExpect(status().isOk()).
                andExpect(content().string(equalTo("kategori 1 togs bort")));
    }

    @Test
    void getCategoryById() throws Exception {
        this.mvc.perform(get("/kategorier/1")).andExpect(status().isOk()).
                andExpect(content().json("{\"name\":\"guld\",\"id\":1,\"kunder\":null}"));
    }

    @Test
    void addBookByPOST() throws Exception {
        this.mvc.perform(post("/kategorier/addByPost")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"platinum\",\"id\":4,\"kunder\":null}")).andExpect(status().isOk()).
                andExpect(content().string(equalTo("Kategori platinum lades till")));
    }
}