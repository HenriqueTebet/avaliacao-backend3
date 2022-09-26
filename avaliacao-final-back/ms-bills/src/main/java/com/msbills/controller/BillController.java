package com.msbills.controller;

import com.msbills.models.Bill;
import com.msbills.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService service;

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Bill>> getAll() {
        return ResponseEntity.ok().body(service.getAllBill());
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Bill> getBillById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.getBillById(id));
    }

    @GetMapping("/get-bills/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Bill>> getBillsByUserId(@PathVariable String userId){
        return ResponseEntity.ok().body(service.getBillsByUserId(userId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> save(@RequestBody Bill bill) {
        return ResponseEntity.ok().body(service.saveBill(bill));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.deleteBillById(id);
        return ResponseEntity.ok().body("Bill deleted successfully");
    }

}
