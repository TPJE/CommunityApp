package ca.bytetube.communityApp.dto;

import ca.bytetube.communityApp.entity.Shop;
import ca.bytetube.communityApp.enums.ShopStateEnum;

import java.util.List;

public class ShopExecution {
    // Status result
    private int state;

    // Status info
    private String stateInfo;

    // Number of shops
    private int count;

    // For POST/UPDATE/DELETE shop
    private Shop shop;

    // Shop list (for GET)
    private List<Shop> shopList;

    public ShopExecution(){};

    // Request Shop failed
    public ShopExecution(ShopStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    // Request Shop success
    public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
    }

    public int getState(){
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo){
        this.stateInfo = stateInfo;
    }

    public int getCount(){
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }

    public Shop getShop(){
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList(){
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
