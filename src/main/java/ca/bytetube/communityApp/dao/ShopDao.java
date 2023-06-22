package ca.bytetube.communityApp.dao;

import ca.bytetube.communityApp.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
    /**
     * Query stores by page. The conditions that can be entered are: store name (fuzzy), store status, store category, area Id, owner
     * @param shopCondition
     * @param rowIndex from which row to get data
     * @param pageSize number of rows returned
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * Return the total of queryShopList
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);

    Shop queryByShopId(long shopId);

    int insertShop(Shop shop);

    int updateShop(Shop shop);
}
