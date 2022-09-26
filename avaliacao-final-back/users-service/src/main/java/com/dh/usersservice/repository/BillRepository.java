package com.dh.usersservice.repository;

import com.dh.usersservice.model.Bill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-bills")
public interface BillRepository {

    @GetMapping("/bills/get-bills/{userId}")
    List<Bill> getBillsByUserId(@PathVariable String userId);
}
