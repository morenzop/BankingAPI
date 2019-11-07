package com.BankingAPI.BankingAPI.services;

import com.BankingAPI.BankingAPI.Bill;
import com.BankingAPI.BankingAPI.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillService billService;

    List<Bill> bills = new ArrayList<>(Arrays.asList(
            new Bill(97250L, "Del Marva", "Power", "08-26-2019", "11-7-2019", 7, "12-7-2019", 500d, "837924", "Pending")
    ));

    public List<Bill> getAllBills() {
        List<Bill> listOfBills = new ArrayList<>();
        billRepository.findAll().forEach(listOfBills::add);
        return listOfBills;
    }

    public Optional<Bill> getByBillId(Long id) {

        return billRepository.findById(id);
    }

    public List<Bill> getAllBillsForCus() {
        List<Bill> listOfBills = new ArrayList<>();
        billRepository.findAll().forEach(listOfBills::add);
        return listOfBills;
    }

    public void createBill(Bill bill) {
        billRepository.save(bill);
    }

    public void updateBill(Long id, Bill bill) {
        billRepository.save(bill);
    }

    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }

}
