package com.example.ceplookupservice.infrastructure.client;

import com.example.ceplookupservice.api.dto.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viaCepClient", url = "${external.api.viacep.url}")
public interface ViaCepClient {

    @GetMapping("/{cep}/json")
    AddressResponse getAddressByCep(@PathVariable("cep") String cep);
}
