package com.example.ceplookupservice.api.controller;

import com.example.ceplookupservice.api.dto.AddressResponse;
import com.example.ceplookupservice.domain.service.CepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cep")
@RequiredArgsConstructor
public class CepController {

    private final CepService cepService;

    @GetMapping("/{cep}")
    public ResponseEntity<AddressResponse> getCep(@PathVariable String cep) {
        return ResponseEntity.ok(cepService.searchCep(cep));
    }
}
