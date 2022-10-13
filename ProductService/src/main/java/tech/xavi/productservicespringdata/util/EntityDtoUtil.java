package tech.xavi.productservicespringdata.util;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import tech.xavi.productservicespringdata.dto.ProductDto;
import tech.xavi.productservicespringdata.entity.Product;

public class EntityDtoUtil {

    public static ProductDto toDto(Product product){
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(product,dto);
        return dto;
    }

    public static Product toEntity(ProductDto dto){
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        return product;
    }
}
