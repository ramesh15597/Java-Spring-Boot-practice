package com.sai.products.service;

import com.sai.products.dto.ProductPurchaseRequest;
import com.sai.products.dto.ProductPurchaseResponse;
import com.sai.products.dto.ProductRequest;
import com.sai.products.dto.ProductResponse;
import com.sai.products.model.Category;
import com.sai.products.model.Product;
import com.sai.products.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Integer createProduct(ProductRequest request) {

        Product product = Product.builder()
                .id(request.getId())
                .name(request.getName())
                .price(request.getPrice())
                .category(Category.builder().
                        id(request.getCategory_id())
                        .build())
                .build();
        repository.save(product);

        return request.getId();

    }

    public List<ProductPurchaseResponse> purchaseProducts(@Valid List<ProductPurchaseRequest> request) throws Exception {

        List<Integer> productIdList = request.stream().map(ProductPurchaseRequest::getId)
                .toList();

        List<Product> storedProducts = repository.findAllByIdInOrderById(productIdList);

        if (storedProducts.size() != productIdList.size()) {
            throw new Exception("One or more product does not exists");
        }
        List<ProductPurchaseRequest> storedRequest = request.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::getId)).toList();

        List<ProductPurchaseResponse> purchaseResponseList = new ArrayList<>();

        for (int i = 0; i < storedProducts.size(); i++) {

            Product product = storedProducts.get(i);
            ProductPurchaseRequest productRequest = storedRequest.get(i);

            if (product.getAvailableQuantity()<productRequest.getQuantity()){
                throw new Exception("In Sufficient quantity for product with Id -->"+productRequest.getId());
            }

            product.setAvailableQuantity(product.getAvailableQuantity()-productRequest.getQuantity());

            repository.save(product);

            purchaseResponseList.add(ProductPurchaseResponse.builder()
                            .productId(product.getId())
                            .name(product.getName())
                            .description(product.getDescription())
                            .quantity(product.getAvailableQuantity())
                            .price(product.getPrice())
                    .build());
        }


        return null;

    }

    public ProductResponse findById(Integer productId) throws Exception {
        return repository.findById(String.valueOf(productId)).map(
                product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .availableQuantity(product.getAvailableQuantity())
                        .price(product.getPrice())
                        .category_id(product.getCategory().getId())
                        .categoryName(product.getCategory().getName())
                        .categoryDescription(product.getCategory().getDescription())
                        .build()).orElseThrow(() -> new Exception(
                format("product Id " + productId + " Not Found")

        ));
    }

    public List<ProductResponse> findAll() {
        return repository.findAll().stream().map(
                product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .availableQuantity(product.getAvailableQuantity())
                        .price(product.getPrice())
                        .category_id(product.getCategory().getId())
                        .categoryName(product.getCategory().getName())
                        .categoryDescription(product.getCategory().getDescription())
                        .build()).toList();

    }
}
