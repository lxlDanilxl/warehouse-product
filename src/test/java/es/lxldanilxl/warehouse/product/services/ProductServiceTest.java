package es.lxldanilxl.warehouse.product.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import es.lxldanilxl.warehouse.product.model.dtos.ProductRequest;
import es.lxldanilxl.warehouse.product.model.dtos.ProductResponse;
import es.lxldanilxl.warehouse.product.model.entities.Product;
import es.lxldanilxl.warehouse.product.repositories.ProductRepository;

@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        // Limpiar la base de datos antes de cada prueba
        productRepository.deleteAll();
    }

    @Test
    public void testAddProduct() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Test Product");
        productRequest.setDescription("Test Description");
        productRequest.setCategory("Test Category");
        productRequest.setUnit("Test Unit");

        productService.addProduct(productRequest);

        List<Product> products = productRepository.findAll();
        assertEquals(1, products.size());
        Product product = products.get(0);
        assertEquals("Test Product", product.getName());
        assertEquals("Test Description", product.getDescription());
        assertEquals("Test Category", product.getCategory());
        assertEquals("Test Unit", product.getUnit());
    }

    @Test
    public void testUpdateProduct() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setCategory("Test Category");
        product.setUnit("Test Unit");
        productRepository.save(product);

        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Updated Test Product");
        productRequest.setDescription("Updated Test Description");
        productRequest.setCategory("Updated Test Category");
        productRequest.setUnit("Updated Test Unit");

        // Act
        productService.updateProduct(product.getId(), productRequest);

        // Assert
        Optional<Product> updatedProduct = productRepository.findById(product.getId());
        assertTrue(updatedProduct.isPresent());
        Product updated = updatedProduct.get();
        assertEquals("Updated Test Product", updated.getName());
        assertEquals("Updated Test Description", updated.getDescription());
        assertEquals("Updated Test Category", updated.getCategory());
        assertEquals("Updated Test Unit", updated.getUnit());
    }

    @Test
    public void testDeleteProduct() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setCategory("Test Category");
        product.setUnit("Test Unit");
        productRepository.save(product);

        // Act
        productService.deleteProduct(product.getId());

        // Assert
        Optional<Product> deletedProduct = productRepository.findById(product.getId());
        assertTrue(deletedProduct.isEmpty());
    }

    @Test
    public void testGetProduct() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setCategory("Test Category");
        product.setUnit("Test Unit");
        productRepository.save(product);

        // Act
        ProductResponse productResponse = productService.getProduct(product.getId());

        // Assert
        assertEquals(product.getId(), productResponse.getId());
        assertEquals(product.getName(), productResponse.getName());
        assertEquals(product.getDescription(), productResponse.getDescription());
        assertEquals(product.getCategory(), productResponse.getCategory());
        assertEquals(product.getUnit(), productResponse.getUnit());
    }

    @Test
    public void testGetAllProducts() {
        // Arrange
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Test Product 1");
        product1.setDescription("Test Description 1");
        product1.setCategory("Test Category 1");
        product1.setUnit("Test Unit 1");
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Test Product 2");
        product2.setDescription("Test Description 2");
        product2.setCategory("Test Category 2");
        product2.setUnit("Test Unit 2");
        productRepository.save(product2);

        // Act
        List<ProductResponse> products = productService.getAllProducts();

        // Assert
        assertEquals(2, products.size());
        ProductResponse productResponse1 = products.get(0);
        assertEquals(product1.getId(), productResponse1.getId());
        assertEquals(product1.getName(), productResponse1.getName());
        assertEquals(product1.getDescription(), productResponse1.getDescription());
        assertEquals(product1.getCategory(), productResponse1.getCategory());
        assertEquals(product1.getUnit(), productResponse1.getUnit());

        ProductResponse productResponse2 = products.get(1);
        assertEquals(product2.getId(), productResponse2.getId());
        assertEquals(product2.getName(), productResponse2.getName());
        assertEquals(product2.getDescription(), productResponse2.getDescription());
        assertEquals(product2.getCategory(), productResponse2.getCategory());
        assertEquals(product2.getUnit(), productResponse2.getUnit());
    }

}