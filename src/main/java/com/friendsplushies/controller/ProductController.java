package com.friendsplushies.controller;

import com.friendsplushies.constant.ServicePath;
import com.friendsplushies.model.entity.Product;
import com.friendsplushies.model.request.ProductRequest;
import com.friendsplushies.model.response.ProductResponse;
import com.friendsplushies.service.ProductService;
import com.friendsplushies.util.cruds.controller.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ServicePath.PRODUCT)
public class ProductController extends AbstractController<ProductRequest, ProductResponse, Product> {
    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    public ProductController() {
    }

    @Autowired
    public ProductController(ProductService productService) {
        super(productService);
    }
}
