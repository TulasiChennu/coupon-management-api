
package com.example.coupon.factory;

import com.example.coupon.strategy.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CouponStrategyFactory {

    private final Map<String, CouponStrategy> strategies;

    public CouponStrategyFactory(
            CartWiseCouponStrategy cart
    ) {
        strategies = Map.of(
                "cart-wise", cart
        );
    }

    public CouponStrategy getStrategy(String type) {

        if (!strategies.containsKey(type))
            throw new RuntimeException("Unsupported coupon type");

        return strategies.get(type);
    }
}
