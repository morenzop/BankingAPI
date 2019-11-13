package com.BankingAPI.BankingAPI.controllers;

import com.BankingAPI.BankingAPI.models.Bill;
import com.BankingAPI.BankingAPI.models.Response;
import com.BankingAPI.BankingAPI.repositories.BillRepository;
import com.BankingAPI.BankingAPI.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/accounts/{accountID}/bills")
    public ResponseEntity<?> getAllBillsForAcc(@PathVariable("accountID") Long id) {
        Response response = new Response();
        response.setCode(200);
        response.setData(billService.getAllBillsByAcc(id));
        if (!billService.existsById(id)) {
            response.setCode(404);
            response.setMessage("error fetching bills");
            HttpStatus statusCode = HttpStatus.NOT_FOUND;

        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/bills/{billId}")
    public ResponseEntity<?> getBillById(@PathVariable("billId") Long id) {
        Response response = new Response();
        response.setCode(200);
        Optional<Bill> bill = billService.getBillById(id);
        response.setData(new ArrayList<>(Collections.singleton(bill)));
        if (!billService.existsById(id)) {
            response.setCode(404);
            response.setMessage("error fetching bill with id: " + id);
            HttpStatus statusCode = HttpStatus.NOT_FOUND;

        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/customers/{customerId}/bills")
    public ResponseEntity<?> getAllBillsForCus(@PathVariable("customerId") Long id) {
        Response response = new Response();
        response.setCode(200);
        response.setData(billService.getAllBillsForCus(id));
        if (!billService.existsById(id)) {
            response.setCode(404);
            response.setMessage("error fetching bills");
            HttpStatus statusCode = HttpStatus.NOT_FOUND;

        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> createBill(@RequestBody Bill bill, @PathVariable("accountId") Long id) {
        Response response = new Response();
        response.setCode(201);
        response.setData(new ArrayList<>());
        if (!billService.existsById(id)) {
            response.setCode(404);
            response.setMessage("Error creating bill: Account not found");
            HttpStatus statusCode = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/bills/{billId}")
    public ResponseEntity<?> updateBill(@PathVariable("billId") Long id, @RequestBody Bill bill) {
        Response response = new Response();
        response.setCode(202);
        if (billService.existsById(id)) {
            response.setCode(404);
            response.setMessage("Bill ID does not exist");
            HttpStatus statusCode = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<?> deleteBill(@PathVariable("billId") Long id) {
        Response response = new Response();
        response.setCode(204);
        if (billService.existsById(id)) {
            response.setCode(404);
            response.setMessage("This id does not exist in bills");
            HttpStatus statusCode = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
