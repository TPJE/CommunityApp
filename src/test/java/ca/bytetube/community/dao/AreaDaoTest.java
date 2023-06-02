package ca.bytetube.community.dao;

import ca.bytetube.community.BaseTest;
import ca.bytetube.communityApp.dao.AreaDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.geom.Area;
import java.util.List;

import static org.junit.Assert.assertEquals;
public class AreaDaoTest extends BaseTest {
    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea(){
        List<Area> areaList = areaDao.queryArea();
        assertEquals(8 , areaList.size());
        assertEquals("montreal", areaList.get(0));

    }
}
