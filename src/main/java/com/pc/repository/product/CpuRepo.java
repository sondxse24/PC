package com.pc.repository.product;

import com.pc.model.product.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuRepo extends JpaRepository<Cpu,Integer> {
}
