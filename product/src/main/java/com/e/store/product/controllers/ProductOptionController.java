package com.e.store.product.controllers;

import com.e.store.product.services.IProductOptionService;
import com.e.store.product.viewmodel.req.ProductOptionCreateReqVm;
import com.e.store.product.viewmodel.res.ListProductOptionResVm;
import com.e.store.product.viewmodel.res.ResVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product/option")
public class ProductOptionController {
    @Autowired
    IProductOptionService iProductOptionService;

    @PostMapping()
    public ResponseEntity<ResVm> createNewOption(@RequestBody ProductOptionCreateReqVm req) {
        return this.iProductOptionService.createNewProductOption(req);
    }

    @GetMapping()
    public ResponseEntity<ListProductOptionResVm> getAllOptions(@RequestParam int page) {
        return this.iProductOptionService.getAllOption(page);
    }

    @PutMapping("{optionId}")
    public ResponseEntity<ResVm> updateOption(@PathVariable String optionId, @RequestBody ProductOptionCreateReqVm req) {
        return this.iProductOptionService.updateOption(optionId, req);
    }

    @DeleteMapping("{optionId}")
    public ResponseEntity<ResVm> deleteOption(@PathVariable String optionId) {
        return this.iProductOptionService.deleteOption(optionId);
    }

}
