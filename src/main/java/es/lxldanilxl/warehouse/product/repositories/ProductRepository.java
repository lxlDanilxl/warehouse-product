package es.lxldanilxl.warehouse.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.lxldanilxl.warehouse.product.model.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
