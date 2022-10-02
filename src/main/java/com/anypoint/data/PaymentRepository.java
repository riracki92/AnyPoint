package com.anypoint.data;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

    @Query("SELECT p FROM Payment p WHERE p.date >= ?1 AND p.date <= ?2")
    List<Payment> getSales(Date startDatetime, Date endDatetime);
}
