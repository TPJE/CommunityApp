package ca.bytetube.communityApp.service.impl;

import ca.bytetube.communityApp.dao.ProductCategoryDao;
import ca.bytetube.communityApp.dao.ProductDao;
import ca.bytetube.communityApp.dao.ProductImgDao;
import ca.bytetube.communityApp.dto.ProductCategoryExecution;
import ca.bytetube.communityApp.entity.ProductCategory;
import ca.bytetube.communityApp.enums.ProductCategoryStateEnum;
import ca.bytetube.communityApp.exceptions.ProductCategoryOperationException;
import ca.bytetube.communityApp.exceptions.ProductOperationException;
import ca.bytetube.communityApp.service.ProductCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ProductCategoryServiceImpl implements ProductCategoryService {

    private static Logger logger = LoggerFactory.getLogger(ProductCategoryServiceImpl.class);

    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Override
    @Transactional
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        // Check if null
        if(productCategoryList == null && productCategoryList.size() <= 0) return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);

        try {
            int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
            if(effectedNum <= 0) throw new ProductCategoryOperationException("Failed to create product category");

            return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
        } catch (Exception e) {
            logger.error("Batch add product category error: " + e.getMessage());
            throw new ProductCategoryOperationException("Batch add product category error: " + e.getMessage());
        }
    }

    @Override
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException {
        // Disconnect any product which relevant to this productCategoryId in tb_product
        try {
            int effectedNum = productDao.updateProductCategoryToNull(productCategoryId);
            if(effectedNum < 0) throw new ProductCategoryOperationException("Product category update failed");
        } catch (Exception e) {
            logger.error("Delete productCategory Error: " + e.getMessage());
            throw new ProductOperationException("Delete productCategory Error: " + e.getMessage());
        }

        // Delete the productCategory
        try {
            int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
            if(effectedNum <= 0) throw new ProductCategoryOperationException("Failed to delete the product category");

            return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);

        } catch (Exception e) {
            logger.error("Delete Product Category Error: " + e.getMessage());
            throw new ProductCategoryOperationException("Delete Product Category Error: " + e.getMessage());
        }
    }
}
