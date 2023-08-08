package com.api.controller;

import com.api.entity.CatalogItem;
import com.api.service.CatalogItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.api.Exception.CatalogItemNotFoundException;

@RestController
@RequestMapping("/catalog-items")
public class CatalogItemController {

    private final CatalogItemService catalogItemService;

    @Autowired
    public CatalogItemController(CatalogItemService catalogItemService) {
        this.catalogItemService = catalogItemService;
    }

    @PostMapping
    public ResponseEntity<CatalogItem> createCatalogItem(@RequestBody CatalogItem catalogItem) {
        CatalogItem createdCatalogItem = catalogItemService.createCatalogItem(catalogItem);
        return ResponseEntity.ok(createdCatalogItem);
    }

    @GetMapping
    public ResponseEntity<List<CatalogItem>> getAllCatalogItems() {
        List<CatalogItem> catalogItems = catalogItemService.getAllCatalogItems();
        return ResponseEntity.ok(catalogItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogItem> getCatalogItemById(@PathVariable Long id) {
        return catalogItemService.getCatalogItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCatalogItem(@PathVariable Long id, @RequestBody CatalogItem updatedCatalogItem) {
        try {
            CatalogItem updatedItem = catalogItemService.updateCatalogItem(id, updatedCatalogItem);
            return ResponseEntity.ok(updatedItem);
        } catch (CatalogItemNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCatalogItem(@PathVariable Long id) {
        catalogItemService.deleteCatalogItem(id);
        return ResponseEntity.noContent().build();
    }
}

