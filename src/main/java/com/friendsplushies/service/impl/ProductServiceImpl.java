package com.friendsplushies.service.impl;

import com.friendsplushies.model.entity.Product;
import com.friendsplushies.model.mapper.ProductMapper;
import com.friendsplushies.model.request.ProductRequest;
import com.friendsplushies.model.response.ProductResponse;
import com.friendsplushies.repository.ProductRepository;
import com.friendsplushies.service.ProductService;
import com.friendsplushies.util.cruds.service.impl.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends AbstractServiceImpl< ProductRequest, ProductResponse, Product>
implements ProductService {
    public ProductServiceImpl() {
    }

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository, ProductMapper.INSTANCE, Product.class);
    }
    
}
