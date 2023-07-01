package ca.bytetube.communityApp.service.impl;

import ca.bytetube.communityApp.cache.JedisUtil;
import ca.bytetube.communityApp.dao.ShopCategoryDao;
import ca.bytetube.communityApp.entity.ShopCategory;
import ca.bytetube.communityApp.exceptions.ShopCategoryOperationException;
import ca.bytetube.communityApp.service.ShopCategoryService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;

    private static Logger logger = LoggerFactory.getLogger(ShopCategoryServiceImpl.class);

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        // Set prefix of Redis key
        String key = SCLISTKEY;
        // Set receive object
        List<ShopCategory> shopCategoryList = null;
        // Set Json converter class
        ObjectMapper mapper = new ObjectMapper();

        // Generate Redis key
        // List all first level shop category if condition is null
        // First level == shop category WITHOUT parentId
        if (shopCategoryCondition == null) key = key + "_allfirstlevel";

        // If parentId is not null, list all child categories under the parentId
        else if (shopCategoryCondition != null &&
                shopCategoryCondition.getParent() != null &&
                shopCategoryCondition.getParent().getShopCategoryId() != null) {
            key = key + "_parent" + shopCategoryCondition.getParent().getShopCategoryId();
        }

        // List all child categories no matter in which class;
        else if (shopCategoryCondition != null) key = key + "_allsecondlevel";

        // Check if key exist (valid)
        if(!jedisKeys.exists(key)) {
            // If not exist, get relative data from database
            shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition);

            // Convert the related entity class collection into string and store it in the corresponding key in Redis
            String jsonString;
            try {
                jsonString = mapper.writeValueAsString(shopCategoryList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new ShopCategoryOperationException(e.getMessage());
            }

            jedisStrings.set(key, jsonString);
        } else {
            // If exist, get related data from Redis
            String jsonString = jedisStrings.get(key);
            // Specify the collection type to convert from String
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ShopCategory.class);

            try {
                // Convert the string of relevant key's value into an entity class collection of objects
                shopCategoryList = mapper.readValue(jsonString, javaType);
            } catch (JsonParseException e) {
                e.printStackTrace();
                logger.error("Json parse Error: " + e.getMessage());
                throw new ShopCategoryOperationException(e.getMessage());
            } catch (JsonMappingException e) {
                e.printStackTrace();
                logger.error("Json mapping Error: " + e.getMessage());
                throw new ShopCategoryOperationException(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("IOE Error: " + e.getMessage());
                throw new ShopCategoryOperationException(e.getMessage());
            }
        }

        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }
}
