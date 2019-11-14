package com.BankingAPI.BankingAPI.controllers;

import com.BankingAPI.BankingAPI.models.Bill;
import com.BankingAPI.BankingAPI.models.Deposit;
import com.BankingAPI.BankingAPI.models.Response;
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
        HttpStatus statusCode;
        if (!billService.existsById(id)) {
            response.setCode(404);
            response.setMessage("error fetching bills");
            statusCode = HttpStatus.NOT_FOUND;
        }else{
            // List<Deposit> billServiceAllByPayeeId = billService.findAllByPayeeId(id);
            response.setCode(200);
            // response.setData(billServiceAllByPayeeId);
            statusCode = HttpStatus.OK;
        }

        return new ResponseEntity<>(response, statusCode);
    }

    @GetMapping("/bills/{billId}")
    public ResponseEntity<?> getBillById(@PathVariable("billId") Long id) {
        HttpStatus statusCode;
        Response response = new Response();
        if (!billService.existsById(id)) {
            response.setCode(404);
            response.setMessage("error fetching bill with id: " + id);
            statusCode = HttpStatus.NOT_FOUND;
        } else {
            // Optional<Deposit> bill = billService.findById(id);
            response.setCode(200);
            // response.setData(new ArrayList<>(Collections.singleton(bill)));
            statusCode = HttpStatus.OK;
        }
        return new ResponseEntity<>(response, statusCode);
    }



    @GetMapping("/customers/{customerId}/bills")
    public ResponseEntity<?> getAllBillsForCus(@PathVariable("customerId") Long id) {
        Response response = new Response();
        HttpStatus statusCode;
        if (!billService.existsById(id)) {
            response.setCode(404);
            response.setMessage("error fetching bills");
            statusCode = HttpStatus.NOT_FOUND;
        }else{
            // Optional<Deposit> bill = billService.findById(id);
            response.setCode(200);
            // response.setData(new ArrayList<>(Collections.singleton(bill)));
            statusCode = HttpStatus.OK;
        }

        return new ResponseEntity<>(response, statusCode);
    }

    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> createBill(@RequestBody Bill bill, @PathVariable("accountId") Long id) {
        Response response = new Response();
        HttpStatus statusCode;
        if (!billService.existsById(id)) {
            response.setCode(404);
            response.setMessage("Error creating bill: Account not found");
            statusCode = HttpStatus.NOT_FOUND;
        }else{
            response.setCode(201);
            response.setData(new ArrayList<>(Collections.singleton(bill)));
            billService.createBill(bill);
            statusCode = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(response, statusCode);
    }


    @PutMapping("/bills/{billId}")
    public ResponseEntity<?> updateBill(@PathVariable("billId") Long id, @RequestBody Bill bill) {
        Response response = new Response();
        HttpStatus statusCode;
        if (!billService.existsById(id)) {
            response.setCode(404);
            response.setMessage("Bill ID does not exist");
            statusCode = HttpStatus.NOT_FOUND;
        }else{
            billService.updateBill(id, bill);
            response.setCode(202);
            response.setMessage("Accepted deposit modification");
            statusCode = HttpStatus.ACCEPTED;
        }

        return new ResponseEntity<>(response, statusCode);
    }

    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<?> deleteBill(@PathVariable("billId") Long id) {
        Response response = new Response();
        HttpStatus statusCode;
        if (billService.existsById(id)) {
            response.setCode(404);
            response.setMessage("This id does not exist in bills");
            statusCode = HttpStatus.NOT_FOUND;
        }else{
            billService.deleteBill(id);
            statusCode = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity<>(response, statusCode);
    }
}
