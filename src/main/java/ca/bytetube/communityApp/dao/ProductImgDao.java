package ca.bytetube.communityApp.dao;

import ca.bytetube.communityApp.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {
    /**
     * List the info image of the product
     */
    List<ProductImg> queryProductImgList(long productId);

    /**
     * Insert multiple product info images
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     * Delete all info images of the product
     */
    int deleteProductImgByProductId(long productId);
}
