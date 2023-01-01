package com.example.spring08fpt.respository;

import com.example.spring08fpt.model.MayATM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MayATMRepository extends JpaRepository<MayATM, Long> {
}
