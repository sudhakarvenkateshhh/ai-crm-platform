package com.venkatesh.ai_crm_platform.Service;

import com.venkatesh.ai_crm_platform.Repository.ProductRepository;
import com.venkatesh.ai_crm_platform.dto.product.ProductRequestDto;
import com.venkatesh.ai_crm_platform.dto.product.ProductResponseDto;
import com.venkatesh.ai_crm_platform.exception.ResourceNotFoundException;
import com.venkatesh.ai_crm_platform.mapper.ProductMapper;
import com.venkatesh.ai_crm_platform.models.Entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // CREATE
    public ProductResponseDto create(ProductRequestDto request) {

        Product product = ProductMapper.toEntity(request);

        Product savedProduct = productRepository.save(product);

        return ProductMapper.toResponse(savedProduct);
    }

    // GET ALL
    public List<ProductResponseDto> getAll() {

        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    // GET BY ID
    public ProductResponseDto getById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product Not Found"));

        return ProductMapper.toResponse(product);
    }

    // UPDATE
    public ProductResponseDto update(Long id,
                                     ProductRequestDto request) {

        Product existing = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product Not Found"));

        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setPrice(request.getPrice());
        existing.setStock(request.getStock());

        Product updatedProduct = productRepository.save(existing);

        return ProductMapper.toResponse(updatedProduct);
    }

    // DELETE
    public void delete(Long id) {

        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product Not Found");
        }

        productRepository.deleteById(id);
    }
}