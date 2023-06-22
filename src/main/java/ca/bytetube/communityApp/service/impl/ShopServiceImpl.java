package ca.bytetube.communityApp.service.impl;

import ca.bytetube.communityApp.dao.ShopDao;
import ca.bytetube.communityApp.dto.ShopExecution;
import ca.bytetube.communityApp.entity.Shop;
import ca.bytetube.communityApp.enums.ShopStateEnum;
import ca.bytetube.communityApp.service.ShopService;
import ca.bytetube.communityApp.util.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        // Convert pageIndex to rowIndex
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        // Return related shops by search condition from dao level
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        // Return number of shops by the same search condition
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if(shopList != null) {
            se.setShopList(shopList);
            se.setCount(count);
        } else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }

    @Override
    public Shop getByShopId(long shopId) {
        return null;
    }
}
