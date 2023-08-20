package com.example.intern2023.service;

import com.example.intern2023.entities.Sell;
import com.example.intern2023.repository.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SellServiceImpl implements  SellService{
    @Autowired
    SellRepository sellRepository;

    public ServiceResult findAll() {
        ServiceResult result = new ServiceResult();
        result.setData(sellRepository.findAll());
        return result;
    }

    public ServiceResult findById(int id) {
        ServiceResult result = new ServiceResult();
        Sell sell = sellRepository.findById(id).orElse(null);
        result.setData(sell);
        return result;
    }

    @Transactional
    public List<Float> findSellById(int id) {
        return sellRepository.findSellById(id);
    }

    @Transactional
    public List<Float> findAllQuantity() {
        return sellRepository.findAllQuantity();
    }

    public ServiceResult create(Sell sell) {
        ServiceResult result = new ServiceResult();
        result.setData(sellRepository.save(sell));
        return result;
    }
}
