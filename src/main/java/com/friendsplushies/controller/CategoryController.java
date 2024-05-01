package com.friendsplushies.controller;

import java.util.*;
import com.friendsplushies.constant.ServicePath;
import com.friendsplushies.model.entity.Category;
import com.friendsplushies.model.request.CategoryRequest;
import com.friendsplushies.model.response.CategoryResponse;
import com.friendsplushies.service.CategoryService;
import com.friendsplushies.service.ProductService;
import com.friendsplushies.util.cruds.controller.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ServicePath.CATEGORY)
public class CategoryController extends AbstractController<CategoryRequest, CategoryResponse, Category>  {

    public static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    public CategoryController() {
    }
    @Autowired
    public CategoryController(CategoryService categoryService) {
        super(categoryService);
    }

    
}
