package com.friendsplushies.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.friendsplushies.constant.ServicePath;
import com.friendsplushies.model.entity.Product;
import com.friendsplushies.model.request.ProductRequest;
import com.friendsplushies.model.response.ProductResponse;
import com.friendsplushies.util.cruds.controller.AbstractController;

@RestController
@RequestMapping(ServicePath.PRODUCT)
public class ProductController extends AbstractController<ProductRequest, ProductResponse, Product> {
    
}
