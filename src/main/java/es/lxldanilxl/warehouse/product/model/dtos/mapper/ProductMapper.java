package es.lxldanilxl.warehouse.product.model.dtos.mapper;

import java.util.List;

import es.lxldanilxl.warehouse.product.model.dtos.ProductRequest;
import es.lxldanilxl.warehouse.product.model.dtos.ProductResponse;
import es.lxldanilxl.warehouse.product.model.entities.Product;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductMapper {
    public static ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .unit(product.getUnit())
                .build();
    }

    /**
     * Converts a ProductResponse object to a Product entity.
     *
     * @param productResponse The ProductResponse object to convert.
     * @return A Product entity with fields populated from the ProductResponse.
     */

    /************* ✨ Codeium Command ⭐ *************/
    /****** 382325f4-d6f3-4f80-b47f-df3324ce9d7f *******/
    public static Product toProduct(ProductResponse productResponse) {
        return Product.builder()
                .id(productResponse.getId())
                .name(productResponse.getName())
                .description(productResponse.getDescription())
                .category(productResponse.getCategory())
                .unit(productResponse.getUnit())
                .build();
    }

    public static List<Product> toProductIterator(List<ProductResponse> productResponses) {
        return productResponses.stream().map(ProductMapper::toProduct).toList();
    }

    public static List<ProductResponse> toProductResponseIterator(List<Product> products) {
        return products.stream().map(ProductMapper::toProductResponse).toList();
    }

    public static Product toProduct(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .category(productRequest.getCategory())
                .unit(productRequest.getUnit())
                .build();
    }

    public static List<Product> toProduct(List<ProductRequest> productRequests) {
        return productRequests.stream().map(ProductMapper::toProduct).toList();
    }

}
