package ca.bytetube.communityApp.service;

import ca.bytetube.communityApp.dto.ImageHolder;
import ca.bytetube.communityApp.dto.ProductExecution;
import ca.bytetube.communityApp.entity.Product;
import ca.bytetube.communityApp.exceptions.ProductOperationException;

import java.util.List;

public interface ProductService {
    /**
     * Get and paging product list.
     * The available input condition: productName(fuzzy), productStatus, shopId, productCategory.
     */
    ProductExecution getProductList(Product productCondition,int pageIndex, int pageSize);

    /**
     * UPDATE product info and image resolver
     */
    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
        throws ProductOperationException;

    /**
     * GET unique product info by productId
     */
    Product getProductById(long productId);

    /**
     * INSERT product info and image resolver
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
        throws ProductOperationException;
}
