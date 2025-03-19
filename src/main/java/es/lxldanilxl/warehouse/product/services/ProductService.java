package es.lxldanilxl.warehouse.product.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.lxldanilxl.warehouse.product.model.dtos.ProductRequest;
import es.lxldanilxl.warehouse.product.model.dtos.ProductResponse;
import es.lxldanilxl.warehouse.product.model.dtos.mapper.ProductMapper;
import es.lxldanilxl.warehouse.product.model.entities.Product;
import es.lxldanilxl.warehouse.product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public void addProduct(ProductRequest productRequest) {
        Product product = ProductMapper.toProduct(productRequest);
        productRepository.save(product);
        log.info("Product added: {}", product);
    }

    public ProductResponse getProduct(Long id) {
        Optional<Product> opt = productRepository.findById(id);

        if (opt.isPresent()) {
            Product product = opt.get();
            return ProductMapper.toProductResponse(product);
        } else {
            return null;
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
        log.info("Product deleted: {}", id);
    }

    public void updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElse(null);
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setCategory(productRequest.getCategory());
        product.setUnit(productRequest.getUnit());
        productRepository.save(product);
        log.info("Product updated: {}", product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ProductMapper.toProductResponseIterator(products);
    }

}
