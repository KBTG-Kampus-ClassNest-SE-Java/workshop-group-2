package com.kampus.kbazaar.product;

import com.kampus.kbazaar.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiResponse(
            responseCode = "200",
            description = "list all products",
            content = {
                @Content(
                        mediaType = "application/json",
                        array =
                                @ArraySchema(
                                        schema = @Schema(implementation = ProductResponse.class)))
            })
    @ApiResponse(
            responseCode = "500",
            description = "internal server error",
            content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NotFoundException.class)))
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getProducts(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit) {
        if (page == null || limit == null) {
            return ResponseEntity.ok(productService.getAll());
        }
        var productsPage = productService.getProductsByPage(page, limit);
        var productResponse = productsPage.stream().map(Product::toResponse).toList();
        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        responseHeaders.set(
                "x-next-page", String.valueOf(productsPage.nextPageable().getPageNumber()));
        responseHeaders.set("x-page", String.valueOf(productsPage.getPageable().getPageNumber()));
        responseHeaders.set(
                "x-prev-page",
                String.valueOf(productsPage.previousOrFirstPageable().getPageNumber()));
        responseHeaders.set("x-total", String.valueOf(productsPage.getTotalElements()));
        responseHeaders.set("x-per-page", String.valueOf(limit));
        responseHeaders.set("x-total-pages", String.valueOf(productsPage.getTotalPages()));

        return new ResponseEntity<>(productResponse, responseHeaders, HttpStatus.OK);
    }

    @ApiResponse(
            responseCode = "200",
            description = "get product by sku",
            content = {
                @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ProductResponse.class))
            })
    @ApiResponse(
            responseCode = "404",
            description = "product not found",
            content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NotFoundException.class)))
    @GetMapping("/products/{sku}")
    public ProductResponse getProductById(@PathVariable String sku) {
        return productService.getBySku(sku);
    }
}
