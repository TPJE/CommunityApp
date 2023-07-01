package ca.bytetube.communityApp.dto;

import ca.bytetube.communityApp.entity.Product;
import ca.bytetube.communityApp.enums.ProductStateEnum;

import java.util.List;

public class ProductExecution {
    // Result state
    private int state;

    // State info
    private String stateInfo;

    // product count
    private int count;

    // Operate product (POST, PUT, DELETE product)
    private Product product;

    // Get product list (for GET product list)
    private List<Product> productList;

    public ProductExecution(){};

    // Constructor for Fail
    public ProductExecution(ProductStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    // Single product Constructor for Success
    public ProductExecution(ProductStateEnum stateEnum, Product product) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.product = product;
    }

    // Multiple products constructor for Success
    public ProductExecution(ProductStateEnum stateEnum, List<Product> productList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productList = productList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
