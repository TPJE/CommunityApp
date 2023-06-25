package ca.bytetube.communityApp.dao;

import ca.bytetube.communityApp.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {
    /**
     * Find and paging products, condition includes: product name(fuzzy), product status, and product category
     */
    List<Product> queryProductList(@Param("productCondition") Product productCondition,
                                   @Param("rowIndex") int rowIndex,
                                   @Param("pageSize") int pageSize);

    /**
     * Find total products count
     */
    int queryProductCount(@Param("productCondition") Product productCondition);

    /**
     * Find unique product info by product ID
     */
    Product queryProductById(long productId);

    /**
     * Insert product
     */
    int insertProduct(Product product);

    /**
     * Update product
     */
    int updateProduct(Product product);

    /**
     * Set product category ID to null before deleting it
     */
    int updateProductCategoryToNull(long productCategoryId);

    /**
     * Delete product
     */
    int deleteProduct(@Param("productId") long productId, @Param("shopId") long shopId);
}
