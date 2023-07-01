package ca.bytetube.communityApp.service;

import ca.bytetube.communityApp.dto.ProductCategoryExecution;
import ca.bytetube.communityApp.entity.ProductCategory;
import ca.bytetube.communityApp.exceptions.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {
    /**
     * GET all product categories info by shopId
     */
    List<ProductCategory> getProductCategoryList(long shopId);

    /**
     * INSERT multiple productCategory
     */
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;

    /**
     * DELETE product category
     * Set relevant products' product category ID to null before DELETE the product category
     */

    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException;
}
