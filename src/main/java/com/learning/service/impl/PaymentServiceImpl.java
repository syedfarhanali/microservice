package com.learning.service.impl;

import com.learning.entity.Payment;
import com.learning.repository.PaymentRepository;
import com.learning.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ArindamN on 29/09/2015.
 */
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }
}
