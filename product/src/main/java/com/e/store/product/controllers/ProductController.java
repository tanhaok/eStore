package com.e.store.product.controllers;

import com.e.store.product.services.IProductService;
import com.e.store.product.viewmodel.req.ProductReqVm;
import com.e.store.product.viewmodel.res.PagingResVm;
import com.e.store.product.viewmodel.res.ProductDetailResVm;
import com.e.store.product.viewmodel.res.ProductResVm;
import com.e.store.product.viewmodel.res.ResVm;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

  private final IProductService iProductService;

  @Autowired
  public ProductController(IProductService iProductService) {
    this.iProductService = iProductService;
  }

  @PostMapping()
  public ResponseEntity<ResVm> createNewProduct(@RequestBody ProductReqVm productReqVm) {
    return iProductService.createNewProduct(productReqVm);
  }

  @GetMapping("")
  public ResponseEntity<PagingResVm<ProductResVm>> getProductsWithPaging(
      @RequestParam @Min(1) int page) {
    return iProductService.getProductsWithPaging(page);
  }

  @GetMapping("/{slug}")
  public ResponseEntity<ProductDetailResVm> getDetailProductById(@PathVariable String slug) {
    return this.iProductService.getDetailProductBySlug(slug);
  }

  @PutMapping("/{prodId}")
  public ResponseEntity<?> updateProductById(
      @PathVariable String prodId, @RequestBody ProductReqVm productReqVm) {
    return null;
  }

  @PatchMapping("/{prodId}/{action}")
  public ResponseEntity<ResVm> updateStatus(
      @PathVariable String prodId, @PathVariable String action) {
    return this.iProductService.updateStatus(prodId, action);
  }
}
