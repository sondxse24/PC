package com.laptop.repository.order;

import com.laptop.model.order.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepo extends JpaRepository<Detail,Integer> {
}
