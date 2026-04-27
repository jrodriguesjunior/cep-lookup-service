package com.example.ceplookupservice.domain.service;

import com.example.ceplookupservice.api.dto.AddressResponse;

public interface CepService {
    AddressResponse searchCep(String cep);
}
