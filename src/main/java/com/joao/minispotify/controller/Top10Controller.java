package com.joao.minispotify.controller;

import com.joao.minispotify.entidades.Top10;
import com.joao.minispotify.service.Top10Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class Top10Controller {

    private Top10Service service;

    public Top10Controller(Top10Service service) {
        this.service = service;
    }

    @GetMapping("/top-musicas")
    public List<Top10> top10() {
        return service.top10();
    }
}