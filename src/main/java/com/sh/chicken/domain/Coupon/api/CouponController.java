package com.sh.chicken.domain.Coupon.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {


    // 쿠폰 발급
    @PostMapping("/generate/{event}")
    public void generateCoupon(@PathVariable(value = "event") String event){


    }
}
