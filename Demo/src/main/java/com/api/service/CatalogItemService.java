package com.api.service;

import com.api.Exception.CatalogItemNotFoundException;
import com.api.entity.CatalogItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.repository.CatalogItemRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CatalogItemService {

    private final CatalogItemRepository catalogItemRepository;

    @Autowired
    public CatalogItemService(CatalogItemRepository catalogItemRepository) {
        this.catalogItemRepository = catalogItemRepository;
    }

    @Transactional
//    public CatalogItem createCatalogItem(CatalogItem catalogItem) {
//        return catalogItemRepository.save(catalogItem);
//    }

    public CatalogItem createCatalogItem(CatalogItem catalogItemDTO) throws CatalogItemNotFoundException {
//        Optional<CatalogItem> optional = CatalogItemRepository.findById(CatalogItemDTO.getId());
//        if (optional.isPresent())
//            throw new CatalogItemNotFoundException("Service.PRODUCT" +
//                    "_FOUND");
        CatalogItem product = new CatalogItem();
        product.setId(catalogItemDTO.getId());
        product.setName(catalogItemDTO.getName());
        product.setDescription(catalogItemDTO.getDescription());
//        CatalogItem s = CatalogItemRepository.save(product);
//        return s.getId();
        return catalogItemRepository.save(product);
    }

    public List<CatalogItem> getAllCatalogItems() {
        return catalogItemRepository.findAll();
    }

    public Optional<CatalogItem> getCatalogItemById(Long id) {
        return catalogItemRepository.findById(id);
    }

    // Update operation
    @Transactional
    public CatalogItem updateCatalogItem(Long id, CatalogItem updatedCatalogItem) {
        Optional<CatalogItem> existingCatalogItemOptional = catalogItemRepository.findById(id);

        if (existingCatalogItemOptional.isPresent()) {
            CatalogItem existingCatalogItem = existingCatalogItemOptional.get();
            existingCatalogItem.setName(updatedCatalogItem.getName());
            existingCatalogItem.setDescription(updatedCatalogItem.getDescription());
            return catalogItemRepository.save(existingCatalogItem);
        } else {
            // Handle not found case
            throw new CatalogItemNotFoundException("CatalogItem with id " + id + " not found.");
        }
    }

    // Delete operation
    @Transactional
    public void deleteCatalogItem(Long id) {
        catalogItemRepository.deleteById(id);
    }
}

