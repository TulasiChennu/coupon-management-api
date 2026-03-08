
package com.example.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.coupon.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
