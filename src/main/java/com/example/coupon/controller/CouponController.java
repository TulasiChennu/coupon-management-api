
package com.example.coupon.controller;

import com.example.coupon.model.*;
import com.example.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService service;

    @PostMapping
    public Coupon create(@RequestBody Coupon coupon){
        return service.create(coupon);
    }

    @GetMapping
    public List<Coupon> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Coupon get(@PathVariable Long id){
        return service.get(id);
    }

    @PostMapping("/applicable")
    public List<Map<String,Object>> applicable(@RequestBody Cart cart){
        return service.applicableCoupons(cart);
    }
}
