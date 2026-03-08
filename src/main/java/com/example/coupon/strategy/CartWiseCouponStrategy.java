
package com.example.coupon.strategy;

import com.example.coupon.model.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class CartWiseCouponStrategy implements CouponStrategy {

    private final ObjectMapper mapper = new ObjectMapper();

    public boolean isApplicable(Coupon coupon, Cart cart) {
        try {
            JsonNode node = mapper.readTree(coupon.getDetails());
            double threshold = node.get("threshold").asDouble();

            double total = cart.getItems().stream()
                    .mapToDouble(i -> i.getPrice() * i.getQuantity())
                    .sum();

            return total > threshold;

        } catch (Exception e) {
            return false;
        }
    }

    public double calculateDiscount(Coupon coupon, Cart cart) {

        try {
            JsonNode node = mapper.readTree(coupon.getDetails());

            double percentage = node.get("discount").asDouble();

            double total = cart.getItems().stream()
                    .mapToDouble(i -> i.getPrice() * i.getQuantity())
                    .sum();

            return total * percentage / 100;

        } catch (Exception e) {
            return 0;
        }
    }

    public Cart applyCoupon(Coupon coupon, Cart cart) {
        double discount = calculateDiscount(coupon, cart);

        double total = cart.getItems().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        double ratio = discount / total;

        for (CartItem item : cart.getItems()) {

            double itemTotal = item.getPrice() * item.getQuantity();

            item.setTotalDiscount(itemTotal * ratio);
        }

        return cart;
    }
}
