package com.example.intern2023.repository;

import com.example.intern2023.entities.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellRepository extends JpaRepository<Sell, Integer> {

    @Query(value = "SELECT quantity FROM sell LIMIT ?1,7", nativeQuery = true)
    List<Float> findSellById(@Param("id") int id);

    @Query(value = "SELECT quantity FROM sell ORDER BY quantity DESC", nativeQuery = true)
    List<Float> findAllQuantity();
}
