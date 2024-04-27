package com.friendsplushies.service;

import com.friendsplushies.model.entity.Product;
import com.friendsplushies.model.request.ProductRequest;
import com.friendsplushies.model.response.ProductResponse;
import com.friendsplushies.util.cruds.service.IService;

public interface ProductService extends IService<ProductRequest, ProductResponse, Product> {
    
}
