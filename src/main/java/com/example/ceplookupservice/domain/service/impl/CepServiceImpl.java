package com.example.ceplookupservice.domain.service.impl;

import com.example.ceplookupservice.api.dto.AddressResponse;
import com.example.ceplookupservice.domain.model.CepLog;
import com.example.ceplookupservice.domain.repository.CepLogRepository;
import com.example.ceplookupservice.domain.service.CepService;
import com.example.ceplookupservice.infrastructure.client.ViaCepClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CepServiceImpl implements CepService {

    private final ViaCepClient viaCepClient;
    private final CepLogRepository cepLogRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public AddressResponse searchCep(String cep) {
        log.info("Searching for CEP: {}", cep);
        
        AddressResponse response = viaCepClient.getAddressByCep(cep);
        
        saveLog(cep, response);
        
        return response;
    }

    private void saveLog(String cep, AddressResponse response) {
        try {
            String jsonData = objectMapper.writeValueAsString(response);
            
            CepLog cepLog = CepLog.builder()
                    .cep(cep)
                    .responseData(jsonData)
                    .queryTimestamp(LocalDateTime.now())
                    .build();
            
            cepLogRepository.save(cepLog);
            log.info("Log saved for CEP: {}", cep);
        } catch (Exception e) {
            log.error("Failed to save log for CEP: {}", cep, e);
        }
    }
}
