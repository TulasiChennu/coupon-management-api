
package com.example.coupon.service;

import com.example.coupon.factory.CouponStrategyFactory;
import com.example.coupon.model.*;
import com.example.coupon.repository.CouponRepository;
import com.example.coupon.strategy.CouponStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository repo;
    private final CouponStrategyFactory factory;

    public Coupon create(Coupon coupon){
        return repo.save(coupon);
    }

    public List<Coupon> getAll(){
        return repo.findAll();
    }

    public Coupon get(Long id){
        return repo.findById(id).orElseThrow();
    }

    public List<Map<String,Object>> applicableCoupons(Cart cart){

        List<Map<String,Object>> result = new ArrayList<>();

        for(Coupon coupon: repo.findAll()){

            CouponStrategy strategy = factory.getStrategy(coupon.getType());

            if(strategy.isApplicable(coupon,cart)){

                double discount = strategy.calculateDiscount(coupon,cart);

                Map<String,Object> map = new HashMap<>();
                map.put("coupon_id",coupon.getId());
                map.put("type",coupon.getType());
                map.put("discount",discount);

                result.add(map);
            }
        }

        return result;
    }
}
