package ca.bytetube.communityApp.service;

import ca.bytetube.communityApp.dto.ShopExecution;
import ca.bytetube.communityApp.entity.Shop;

public interface ShopService {

    /**
     * Return shop list depends on shopCondition paging
     */

    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

    /**
     * Get shop by shop ID
     */
    Shop getByShopId(long shopId);


}
