package com.codeafrica.markeplace.service;

import com.codeafrica.markeplace.dto.ProductDto;
import com.codeafrica.markeplace.exceptions.ProductNotExistException;
import com.codeafrica.markeplace.model.Category;
import com.codeafrica.markeplace.model.Product;
import com.codeafrica.markeplace.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public void addProduct(ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto,category);
        productRepository.save(product);
    }

    private Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setCategory(category);
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrL());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        return product;
    }

    public List<ProductDto> listProducts() {
        List<Product>products= productRepository.findAll();
        List<ProductDto>productDtos = new ArrayList<>();

        for(Product product : products){
            productDtos.add(new ProductDto(product));
        }

        return productDtos;
    }

    public void updateProduct(Integer productID, ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto,category);
        product.setId(productID);
        productRepository.save(product);
    }

    public Product getProductById(Integer productId) throws ProductNotExistException {
        Optional<Product>optionalProduct = productRepository.findById(productId);
        if(!optionalProduct.isPresent()) {
            throw new ProductNotExistException("product id is valid" + productId);
        }
        return optionalProduct.get();
    }
}
