package ca.bytetube.communityApp.service.impl;

import ca.bytetube.communityApp.dao.ProductDao;
import ca.bytetube.communityApp.dao.ProductImgDao;
import ca.bytetube.communityApp.dto.ImageHolder;
import ca.bytetube.communityApp.dto.ProductExecution;
import ca.bytetube.communityApp.entity.Product;
import ca.bytetube.communityApp.entity.ProductImg;
import ca.bytetube.communityApp.enums.ProductStateEnum;
import ca.bytetube.communityApp.exceptions.ProductOperationException;
import ca.bytetube.communityApp.service.ProductService;
import ca.bytetube.communityApp.util.ImageUtil;
import ca.bytetube.communityApp.util.PageCalculator;
import ca.bytetube.communityApp.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        // Convert pageIndex into rowIndex in database, and call dao to return product list on specify page
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);

        // Return the total product count and product list which met productCondition
        int count = productDao.queryProductCount(productCondition);
        ProductExecution pe = new ProductExecution();
        pe.setProductList(productList);
        pe.setCount(count);
        return null;
    }

    @Override
    @Transactional
    // 1. Process the thumbnail if the parameter has values,
    //    if the thumbnail already exist, then delete original one then get reference path and define to product

    // 2. Do the same process if product detail image's parameter has values.
    // 3. DELETE all exist product images under tb_product_img
    // 4. UPDATE tb_product_img and tb_product
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException {
        // Check if null
        if(product != null &&
           product.getShop() != null &&
           product.getShop().getShopId() != null) {
           product.setLastEditTime(new Date());

           // If thumbnail is not null and has value, DELETE all exist thumbnail then INSERT
            if(thumbnail != null) {
                // Get the path by querying original data
                Product tempProduct = productDao.queryProductById(product.getProductId());
                if(tempProduct.getImgAddr() != null) ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
            }
            addThumbnail(product, thumbnail);
        }

        // DELELTE original images
        return null;
    }

    @Override
    public Product getProductById(long productId) {
        return productDao.queryProductById(productId);
    }


    @Override
    @Transactional
    // 1. Handle thumbnail, get the reference path and define value to product
    // 2. INSERT product info into tb_product, get product ID.
    // 3. Merge product ID and process multiple product info images.
    // 4. INSERT product info image list into tb_product_img.
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException {
        // Check if null
        if(product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            // Set default attributes
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());

            // Default status is 1 (online/ enable)
            product.setEnableStatus(1);

            // Add thumbnail if it is not null
            if(thumbnail != null) addThumbnail(product, thumbnail);

            try {
                // Create product info
                int effectedNum = productDao.insertProduct(product);
                if(effectedNum <= 0) throw new ProductOperationException("Failed to create product");

            } catch (Exception e) {
                throw new ProductOperationException("Failed to create product: " + e.toString());
            }

            // Add product images if it is not null
            if(productImgHolderList != null && productImgHolderList.size() > 0) addProductImgList(product, productImgHolderList);

            return new ProductExecution(ProductStateEnum.SUCCESS, product);
        }

        // Throw exception if null
        return new ProductExecution(ProductStateEnum.EMPTY);
    }
    /**
     * INSERT thumbnail
     */
    private void addThumbnail(Product product, ImageHolder thumbnail){
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        product.setImgAddr(thumbnailAddr);
    }

    /**
     * INSERT multiple thumbnails
     */
    private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
        // Get save path and directly save to the relevant shop's folder
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<ProductImg>();

        // Loop into productImg List and process at once.
        for(ImageHolder productImgHolder : productImgHolderList) {
            String imgAddr = ImageUtil.generateNormalImg(productImgHolder, dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);
        }

        // Batch Insert if needed
        if(productImgList.size() > 0) {
            try {
                int effectNum = productImgDao.batchInsertProductImg(productImgList);
                if(effectNum <= 0) throw new ProductOperationException("Failed to add product image");
            } catch (Exception e) {
                throw new ProductOperationException("Failed to add product image: " + e.toString());
            }
        }
    }

    /**
     * DELETE all images of a product
     * @param productId
     */
    private void deleteProductImgList(long productId) {
        // Get original images by productId
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);

        // DELETE all original images by path in directory
        for(ProductImg productImg : productImgList) ImageUtil.deleteFileOrPath(productImg.getImgAddr());

        // DELETE rows from database
        productImgDao.deleteProductImgByProductId(productId);
    }
}
