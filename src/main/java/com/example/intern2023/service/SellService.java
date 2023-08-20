package com.example.intern2023.service;

import com.example.intern2023.entities.Sell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.intern2023.repository.SellRepository;

import java.util.List;

@Service
public interface SellService {
    public ServiceResult findAll();
    public ServiceResult findById(int id);
    List<Float> findSellById(int id);
    List<Float> findAllQuantity();
    public ServiceResult create(Sell sell);

}
