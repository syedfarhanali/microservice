package com.learning.repository;

import com.learning.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by amits on 24/09/15.
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
