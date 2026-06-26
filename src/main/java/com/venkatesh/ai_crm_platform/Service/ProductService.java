package com.venkatesh.ai_crm_platform.Service;



import com.venkatesh.ai_crm_platform.Repository.ProductRepository;
import com.venkatesh.ai_crm_platform.exception.ResourceNotFoundException;
import com.venkatesh.ai_crm_platform.models.Entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product create(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product getById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product Not Found"));
    }

    public Product update(Long id,
                          Product product){

        Product existing = getById(id);

        existing.setName(product.getName());
        existing.setDescription(
                product.getDescription());

        existing.setPrice(product.getPrice());

        existing.setStock(product.getStock());

        return productRepository.save(existing);
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }
}