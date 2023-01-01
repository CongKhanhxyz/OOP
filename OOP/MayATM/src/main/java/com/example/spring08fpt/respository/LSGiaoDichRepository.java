package com.example.spring08fpt.respository;

import com.example.spring08fpt.model.LSGiaoDich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LSGiaoDichRepository extends JpaRepository<LSGiaoDich, Long> {
    List<LSGiaoDich> findAllByCustomerId(Long customerId);
}
