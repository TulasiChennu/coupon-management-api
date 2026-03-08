
package com.example.coupon.strategy;

import com.example.coupon.model.*;

public interface CouponStrategy {

    boolean isApplicable(Coupon coupon, Cart cart);

    double calculateDiscount(Coupon coupon, Cart cart);

    Cart applyCoupon(Coupon coupon, Cart cart);
}
