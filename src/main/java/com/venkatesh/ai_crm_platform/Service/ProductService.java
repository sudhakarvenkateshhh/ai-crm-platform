package com.venkatesh.ai_crm_platform.Service;

import com.venkatesh.ai_crm_platform.Repository.ProductRepository;
import com.venkatesh.ai_crm_platform.dto.product.ProductRequestDto;
import com.venkatesh.ai_crm_platform.dto.product.ProductResponseDto;
import com.venkatesh.ai_crm_platform.exception.ResourceNotFoundException;
import com.venkatesh.ai_crm_platform.mapper.ProductMapper;
import com.venkatesh.ai_crm_platform.models.Entities.Product;
import com.venkatesh.ai_crm_platform.response.ApiResponse;
import com.venkatesh.ai_crm_platform.response.PageResponse;
import com.venkatesh.ai_crm_platform.response.ResponseBuilder;
import com.venkatesh.ai_crm_platform.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import com.venkatesh.ai_crm_platform.Service.storage.FileStorageService;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final FileStorageService fileStorageService;

    // CREATE
    @PreAuthorize("hasAnyRole('ADMIN','SALES')")
    public ProductResponseDto create(ProductRequestDto request) {

        Product product = ProductMapper.toEntity(request);

        Product savedProduct = productRepository.save(product);

        return ProductMapper.toResponse(savedProduct);
    }

    // GET ALL
    @PreAuthorize("hasAnyRole('ADMIN','SALES','MANAGER')")
    public PageResponse<ProductResponseDto> getAll(
            int page,
            int size,
            String sortBy,
            String direction,
            String keyword,
            Integer stock
    ) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Product> specification =
                Specification.where(ProductSpecification.search(keyword))
                        .and(ProductSpecification.stock(stock));

        Page<Product> productPage =
                productRepository.findAll(specification, pageable);

        List<ProductResponseDto> productDtos =
                productPage.getContent()
                        .stream()
                        .map(ProductMapper::toResponse)
                        .toList();

        return new PageResponse<>(
                productDtos,
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.isLast()
        );
    }



    // GET BY ID
    @PreAuthorize("hasAnyRole('ADMIN','SALES','MANAGER')")
    public ProductResponseDto getById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product Not Found"));

        return ProductMapper.toResponse(product);
    }

    // UPDATE
    @PreAuthorize("hasAnyRole('ADMIN','SALES')")
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
    @PreAuthorize("hasAnyRole('ADMIN','SALES')")
    public ProductResponseDto uploadImage(Long id, MultipartFile file){

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product Not Found"));

        String fileName = fileStorageService.uploadFile(file);

        product.setImageUrl(fileName);

        return ProductMapper.toResponse(
                productRepository.save(product)
        );
    }
    // DELETE
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {

        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product Not Found");
        }

        productRepository.deleteById(id);
    }
}