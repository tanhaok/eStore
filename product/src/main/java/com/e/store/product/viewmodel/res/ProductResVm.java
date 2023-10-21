package com.e.store.product.viewmodel.res;

import java.util.List;

public record ProductResVm(String id, String name, int quantity, int price, String lastUpdateDate, List<ProductVariationsResVm> variations) {
}