package com.codeventlk.helloshoemanagementsystem.controller;

import com.codeventlk.helloshoemanagementsystem.dto.StockDTO;
import com.codeventlk.helloshoemanagementsystem.dto.SupplierDTO;
import com.codeventlk.helloshoemanagementsystem.exception.NotFoundException;
import com.codeventlk.helloshoemanagementsystem.service.StockService;
import com.codeventlk.helloshoemanagementsystem.service.SupplierService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/supplier")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
@Slf4j
public class Supplier {
    final private SupplierService supplierService;
    final private StockService stockService;

    @GetMapping("/health")
    public String healthCheck(){return "Supplier Health Check";}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveSupplier(@Validated @RequestBody SupplierDTO supplierDTO,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            supplierService.saveSupplier(supplierDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Supplier Details saved Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Supplier saved Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<?> getSupplier(@PathVariable ("id") String id){
        try {
            return ResponseEntity.ok(supplierService.getSupplier(id));
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Supplier Details fetched Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllSupplier(){
        try {
            return ResponseEntity.ok(supplierService.getAllSuppliers());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Supplier Details fetched Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @RolesAllowed("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable ("id") String id){
        try {
            supplierService.deleteSupplier(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Supplier Details deleted Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Supplier Details deleted Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @RolesAllowed("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateCustomer(@Validated @RequestBody SupplierDTO supplierDTO,
                                                 BindingResult bindingResult,
                                                 @PathVariable ("id") String id) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            supplierService.updateSupplier(id,supplierDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Supplier Details Updated Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Supplier Details Updated Unsuccessfully.\nMore Reason\n"+exception);
        }

    }

    @GetMapping("/nextSupId")
    public ResponseEntity<?> getSupplierId() {
        try {
            return ResponseEntity.ok(supplierService.getSupplierId());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Supplier Id fetched Unsuccessfully.\nMore Reason\n" + exception);
        }
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/nextStockId")
    public ResponseEntity<?> getStockId(){
        try {
            return ResponseEntity.ok(stockService.getStockId());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Stock Id fetched Unsuccessfully.\nMore Reason\n" + exception);
        }
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping(value = "/saveStock/{email}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveStock(@Validated @RequestBody StockDTO stockDTO,
                                       BindingResult bindingResult,
                                       @PathVariable("email") String email) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            stockService.saveStock(stockDTO, email);
            log.info("StockDto : "+stockDTO+" email : "+email);
            return ResponseEntity.status(HttpStatus.CREATED).body("Stock Details saved Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Stock saved Unsuccessfully.\nMore Details\n" + exception.getMessage());
        }
    }

    @GetMapping("/getAllStock")
    public ResponseEntity<?> getAllStock(){
        try {
            return ResponseEntity.ok(stockService.getAllStock());
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employees not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Employees Details fetched Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @RolesAllowed("ROLE_ADMIN")
    @PutMapping(value = "/updateStock/{stockId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> updateStock(@Validated @RequestBody StockDTO stockDTO,
                                       BindingResult bindingResult,
                                       @PathVariable("stockId") String stockId) {

        log.info("StockDto : " +stockDTO+" "+stockId);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            stockService.updateStock(stockDTO,stockId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Stock Details Updated Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found.");
        } catch (Exception exception) {
            log.info("Exception : "+exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Stock Details Updated Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @RolesAllowed("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/deleteStock/{stockId}")
    public ResponseEntity<String> deleteStock(@PathVariable ("stockId") String id){
        try {
            stockService.deleteStock(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Stock Details deleted Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Stock Details deleted Unsuccessfully.\nMore Reason\n"+exception);
        }
    }
}
