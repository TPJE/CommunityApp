package ca.bytetube.communityApp.dao;

import ca.bytetube.communityApp.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {
    /**
     * Find product category by shop ID
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

    /**
     * Insert multiple product categories
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    /**
     * Delete selected product category
     */
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}
