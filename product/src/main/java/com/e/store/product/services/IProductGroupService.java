package com.e.store.product.services;

import com.e.store.product.viewmodel.req.ProductGroupCreateReqVm;
import com.e.store.product.viewmodel.req.ProductGroupUpdateReqVm;
import com.e.store.product.viewmodel.res.CommonProductResVm;
import com.e.store.product.viewmodel.res.PagingResVm;
import com.e.store.product.viewmodel.res.ProductGroupResVm;
import com.e.store.product.viewmodel.res.ResVm;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface IProductGroupService {

  ResponseEntity<ResVm> createNewGroup(ProductGroupCreateReqVm groupData);

  ResponseEntity<ResVm> updateProductGroup(ProductGroupUpdateReqVm data);

  ResponseEntity<PagingResVm<ProductGroupResVm>> getAllGroup(int page);

  ResponseEntity<ResVm> deleteProductGroup(String groupId);

  ResponseEntity<List<CommonProductResVm>> getAllGroup();
}
