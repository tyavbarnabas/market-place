package com.codeafrica.markeplace.controller;

import com.codeafrica.markeplace.common.ApiResponse;
import com.codeafrica.markeplace.dto.ProductDto;
import com.codeafrica.markeplace.model.Category;
import com.codeafrica.markeplace.service.CategoryService;
import com.codeafrica.markeplace.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/product")
public class ProductController {



    private ProductService productService;
    @Autowired
    private CategoryService categoryService;


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto){
        Optional<Category>optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if(!optionalCategory.isPresent()){
          return new ResponseEntity<ApiResponse>(new ApiResponse(false,"category is invalid"), HttpStatus.CONFLICT);
        }
        Category category =  optionalCategory.get();
        productService.addProduct(productDto,category);
        return  new ResponseEntity<>(new ApiResponse(true, "product has beeen added "),HttpStatus.CREATED);
    }



    @GetMapping("/")
    public ResponseEntity<List<ProductDto>>getProducts(){
        List<ProductDto>productDtos = productService.listProducts();
        return new ResponseEntity<>(productDtos,HttpStatus.OK);
    }


    public ResponseEntity<ApiResponse>updateProduct(@PathVariable("productID") Integer productID, @RequestBody @Valid
                                                    ProductDto productDto){

        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if(!optionalCategory.isPresent()){
            return  new ResponseEntity<ApiResponse>(new ApiResponse(false,"category is invalid"),HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.updateProduct(productID,productDto,category);
        return new ResponseEntity<>(new ApiResponse(true,"product has been updated"),HttpStatus.OK);
    }




}
