package com.BankingAPI.BankingAPI.controllers;

import com.BankingAPI.BankingAPI.Bill;
import com.BankingAPI.BankingAPI.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{accountID}/bills")
    public List<Bill> getAllBillsForAcc() {
        return billService.getAllBills();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/bills/{billId}")
    public Optional<Bill> getBill(@PathVariable Long id) {
        return billService.getByBillId(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers/{customerId}/bills")
    public List<Bill> getAllBillsForCus() {
        return billService.getAllBills();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/accounts/{accountId}/bills")
    public void createBill(@RequestBody Bill bill) {
        billService.createBill(bill);
    }

    @RequestMapping(method = RequestMethod.PUT, value =  "/bills/{billId}")
    public void updateBill(@PathVariable Long id, @RequestBody Bill bill) {
        billService.updateBill(id, bill);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/bills/{billId}")
    public void deleteBill(@RequestBody Long id) {
        billService.deleteBill(id);
    }


}
