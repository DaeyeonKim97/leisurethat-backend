package com.steady.leisurethatapi.project.manage.dto;

import com.steady.leisurethatapi.database.entity.Attachment;
import com.steady.leisurethatapi.database.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private int productId;
    private String productName;
    private Attachment productAttachment;
    private String productDetail;
    public ProductResponseDTO(Product product){
        this.productId = product.getId();
        this.productName = product.getName();
        this.productAttachment = product.getAttachment();
        this.productDetail = product.getDetail();
    }
}
