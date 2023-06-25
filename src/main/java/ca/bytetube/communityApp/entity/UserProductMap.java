package ca.bytetube.communityApp.entity;

import java.util.Date;

public class UserProductMap {
    // Key id
    private Long userProductId;

    private Date createTime;

    // The points earned by purchasing products
    private Integer point;

    // User person info entity class
    private PersonInfo user;

    // Product entity class
    private Product product;

    // Shop entity class
    private Shop shop;

    // Operator person info entity class
    private PersonInfo operator;

    public Long getUserProductId() {
        return userProductId;
    }

    public void setUserProductId(Long userProductId) {
        this.userProductId = userProductId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public PersonInfo getUser() {
        return user;
    }

    public void setUser(PersonInfo user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public PersonInfo getOperator() {
        return operator;
    }

    public void setOperator(PersonInfo operator) {
        this.operator = operator;
    }
}