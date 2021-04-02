package it.opensource.ecompany.web.utils;

import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.domain.ProductPrice;
import it.opensource.ecompany.service.ProductPriceService;
import it.opensource.ecompany.web.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

public class ProductConverter {

    private ProductPriceService productPriceService;

    private List<ProductDto> productDtos = new ArrayList<>();

    public ProductConverter(ProductPriceService productPriceService) {

        this.productPriceService = productPriceService;
    }

    public List<ProductDto> converter(List<Product> products) {

        productDtos.clear();
        for (Product product : products) {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setProductCode(product.getProductCode());
            productDto.setName(product.getName());
            productDto.setSubtitle(product.getSubtitle());
            productDto.setDescription(product.getDescription());
            productDto.setCategory(product.getCategory());
            productDto.setImageProduct(product.getImageProduct());
            productDto.setVersion(product.getVersion());

            ProductPrice productPrice = productPriceService.getProductPriceByPriceListNameAndProductCode("base", product.getProductCode());
            productDto.setPrice(productPrice.getPrice());

            productDtos.add(productDto);
        }

        return productDtos;
    }
}
