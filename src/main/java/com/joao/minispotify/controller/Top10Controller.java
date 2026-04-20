package com.joao.minispotify.controller;

import com.joao.minispotify.dto.Top10;
import com.joao.minispotify.service.Top10Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class Top10Controller {

    private final Top10Service service;

    public Top10Controller(Top10Service service) {
        this.service = service;
    }

    @GetMapping("/top-musicas")
    public ResponseEntity<List<Top10>> top10() {
        return ResponseEntity.ok(service.top10());
    }
}