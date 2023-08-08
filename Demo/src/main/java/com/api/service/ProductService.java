package com.api.service;

import com.api.Exception.HnDBankException;
import com.api.dto.ProductDTO;
import java.util.List;

public interface ProductService {
    public int addProduct(ProductDTO product) throws HnDBankException;
    public ProductDTO getProduct(Integer productId) throws HnDBankException;
    public List<ProductDTO> findAll() throws HnDBankException;
    public void updateProduct(Integer productId, ProductDTO productDTO) throws HnDBankException;
    public void deleteProduct(Integer productId)throws HnDBankException;
}
