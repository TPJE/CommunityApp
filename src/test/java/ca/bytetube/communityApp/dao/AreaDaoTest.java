package ca.bytetube.communityApp.dao;

import ca.bytetube.communityApp.CommunityappApplicationTests;
import ca.bytetube.communityApp.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
public class AreaDaoTest extends CommunityappApplicationTests {
    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea(){
        List<Area> areaList = areaDao.queryArea();
        areaList.forEach(area -> {
        System.out.println(area);

        });

    }
}
