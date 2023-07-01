package ca.bytetube.communityApp.service.impl;

import ca.bytetube.communityApp.cache.JedisUtil;
import ca.bytetube.communityApp.dao.HeadLineDao;
import ca.bytetube.communityApp.entity.HeadLine;
import ca.bytetube.communityApp.exceptions.HeadLineOperationException;
import ca.bytetube.communityApp.service.HeadLineService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    private HeadLineDao headLineDao;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    private static Logger logger = LoggerFactory.getLogger(HeadLineServiceImpl.class);

    @Override
    @Transactional
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) {
        // Define prefix of Redis key
        String key = HLLISTKEY;

        // Define receive object
        List<HeadLine> headLineList = null;

        // Define Json converter class
        ObjectMapper mapper = new ObjectMapper();

        // Append Redis key
        if(headLineCondition != null && headLineCondition.getEnableStatus() != null) key = key + "_" + headLineCondition.getEnableStatus();

        // Check if key exist
        if(!jedisKeys.exists(key)) {
            // Query database if key does not exist
            headLineList = headLineDao.queryHeadLine(headLineCondition);
            // Convert entity collection into String then store into relevant Redis key
            String jsonString;
            try {
                jsonString = mapper.writeValueAsString(headLineList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                logger.error("Json processing Error: " + e.getMessage());
                throw new HeadLineOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        }
        else {
            // If key exist, query the data from Redis
            String jsonString = jedisStrings.get(key);

            // Convert string into specify collection type
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class);

            try {
                // Convert relevant Redis key value into specific object entity collection
                headLineList = mapper.readValue(jsonString, javaType);
            } catch (JsonParseException e) {
                e.printStackTrace();
                logger.error("Json Parse Error: " + e.getMessage());
                throw new HeadLineOperationException(e.getMessage());
            } catch (JsonMappingException e) {
                e.printStackTrace();
                logger.error("Json Mapping Error: " + e.getMessage());
                throw new HeadLineOperationException(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("IO Error: " + e.getMessage());
                throw new HeadLineOperationException(e.getMessage());
            }
        }
        return headLineList;
    }
}
