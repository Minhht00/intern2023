package com.example.intern2023.comtroller;

import com.example.intern2023.entities.Sell;

import java.util.ArrayList;
import java.util.List;

import com.example.intern2023.repository.SellRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.intern2023.service.SellService;
import com.example.intern2023.service.ServiceResult;

@RestController
@RequestMapping("/api/v1")
public class SellController {
    static final int BAD_RICE_OF_CUP = 300;
    static final int PRICE_ONE_KG_BAD_RICE = 2000;
    static final int PRICE_ONE_G_BAD_RICE = PRICE_ONE_KG_BAD_RICE/1000;
    static final int PRICE_ONE_KG_GOOD_RICE = 20000;
    static final int PRICE_ONE_G_GOOD_RICE = PRICE_ONE_KG_GOOD_RICE/1000;
    @Autowired
    private SellService sellService;

    @GetMapping("/sell")
    public ResponseEntity<ServiceResult> findAllSell() {
        return new ResponseEntity<ServiceResult>(sellService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/sell/{id}")
    public ResponseEntity<ServiceResult> findById(@PathVariable int id) {
        return new ResponseEntity<ServiceResult>(sellService.findById(id), HttpStatus.OK);
    }

  @GetMapping("/quantity/{id}")
    public List<Float> findSellById(@PathVariable int id) {
        List<Float> list = new ArrayList<>();
        try {
            switch (id) {
                case 1:
                    list.addAll(calculateSellOfWeek(0));
                    break;
                case 2:
                    list.addAll(calculateSellOfWeek(7));
                    break;
                case 3:
                    list.addAll(calculateSellOfWeek(14));
                    break;
                case 4:
                    list.addAll(calculateSellOfWeek(21));
                    break;
                case 5:
                    list.addAll(calculateSellOfWeek(28));
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
      return list;
    }
    @GetMapping("/sellAllWeek")
    public List<Float> findSellAllWeek() {
        List<Float> list = new ArrayList<>();
        list.addAll(calculateSellAllWeek());
        return list;
    }

    @GetMapping("/sellAllDay")
    public List<Float> findSellAllDay() {
        List<Float> list = new ArrayList<>();
        list = calculateSellOfDay();
        return list;
    }
    @PostMapping("/sell")
    public ResponseEntity<ServiceResult> create(@RequestBody Sell sell) {
        return new ResponseEntity<ServiceResult>(sellService.create(sell), HttpStatus.OK);
    }

    public List<Float> calculateSellOfWeek(int id) {
        List<Float> list = new ArrayList<>();
        list = sellService.findSellById(id);
        float badOfCup = 0;
        float remainder = 0;
        float sell = 0;
        List<Float> sellOfWeek = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            badOfCup = list.get(i)/5;
            sell = badOfCup * BAD_RICE_OF_CUP * PRICE_ONE_G_GOOD_RICE - badOfCup * BAD_RICE_OF_CUP * PRICE_ONE_G_BAD_RICE;
            sellOfWeek.add(sell);
        }
        return sellOfWeek;
    }

    public List<Float> calculateSellAllWeek() {
        List<Float> listAllWeek = new ArrayList<>();
        List<Float> listOfWeek = calculateSellOfWeek(0);
        float sum = 0;
        for (int i = 0; i < 30; i = i + 7) {
            listOfWeek = calculateSellOfWeek(i);
            for(float list : listOfWeek) {
                sum += list;
            }
            listAllWeek.add(sum);
        }
        return listAllWeek;
    }

    public List<Float> calculateSellOfDay() {
        List<Float> list = new ArrayList<>();
        list = sellService.findAllQuantity();
        float badOfCup = 0;
        float remainder = 0;
        float sell = 0;
        List<Float> sellOfWeek = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            badOfCup = list.get(i)/5;
            sell = badOfCup * BAD_RICE_OF_CUP * PRICE_ONE_G_GOOD_RICE - badOfCup * BAD_RICE_OF_CUP * PRICE_ONE_G_BAD_RICE;
            sellOfWeek.add(sell);
        }
        return sellOfWeek;
    }


}
