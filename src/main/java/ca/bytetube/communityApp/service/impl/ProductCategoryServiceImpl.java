package ca.bytetube.communityApp.service.impl;

import ca.bytetube.communityApp.dto.ProductCategoryExecution;
import ca.bytetube.communityApp.entity.ProductCategory;
import ca.bytetube.communityApp.exceptions.ProductCategoryOperationException;
import ca.bytetube.communityApp.service.ProductCategoryService;

import java.util.List;

public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return null;
    }

    @Override
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        return null;
    }

    @Override
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException {
        return null;
    }
}
