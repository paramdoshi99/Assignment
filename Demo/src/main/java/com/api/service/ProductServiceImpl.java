package com.api.service;

import com.api.Exception.HnDBankException;
import com.api.dto.ProductDTO;
import com.api.entity.Product;
import com.api.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRespository;

    @Override
    public int addProduct(ProductDTO productDto) throws HnDBankException {
        Optional<Product> optional = productRespository.findById(productDto.getProductId());
        if (optional.isPresent())
            throw new HnDBankException("Service.PRODUCT" +
                    "_FOUND");
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setProductName(productDto.getProductName());
        product.setProductDescription(productDto.getProductDescription());
        product.setExpiryDate(productDto.getExpiryDate());
        Product s = productRespository.save(product);
        return s.getProductId();
    }
    @Override
    public ProductDTO getProduct(Integer productId) throws HnDBankException {
        Optional<Product> optional = productRespository.findById(productId);
        Product product = optional.orElseThrow(() -> new HnDBankException("Service.PRODUCT_NOT_FOUND"));
        ProductDTO productDto = new ProductDTO();
        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setProductDescription(product.getProductDescription());
        productDto.setExpiryDate(product.getExpiryDate());
        return productDto;
    }
    @Override
    public List<ProductDTO> findAll() throws HnDBankException {
        Iterable<Product> products = productRespository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();
        products.forEach(product -> {
            ProductDTO productDto = new ProductDTO();
            productDto.setProductId(product.getProductId());
            productDto.setProductName(product.getProductName());
            productDto.setProductDescription(product.getProductDescription());
            productDto.setExpiryDate(product.getExpiryDate());
            productDTOs.add(productDto);
        });
        if (productDTOs.isEmpty())
            throw new HnDBankException("Service.PRODUCT_NOT_FOUND");
        return productDTOs;
    }

    @Override
    public void updateProduct(Integer productId, ProductDTO productDTO) throws HnDBankException {
        Optional<Product> optional = productRespository.findById(productId);
        Product product = optional.orElseThrow(() -> new HnDBankException("Service.PRODUCT" +
                "_NOT_FOUND"));
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductName(productDTO.getProductName());
        product.setExpiryDate(productDTO.getExpiryDate());
    }

    @Override
    public void deleteProduct(Integer productId) throws HnDBankException {
        Optional<Product> optional = productRespository.findById(productId);
        optional.orElseThrow(() -> new HnDBankException("Service.PRODUCT" +
                "_NOT_FOUND"));
        productRespository.deleteById(productId);
    }
}
