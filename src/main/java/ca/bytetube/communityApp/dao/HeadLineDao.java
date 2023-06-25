package ca.bytetube.communityApp.dao;

import ca.bytetube.communityApp.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeadLineDao {
    /**
     * Search by input condition (Headline name search)
     */
    List<HeadLine> queryHeadLine(@Param("headLineConfition") HeadLine headLineCondition);

    /**
     * Return one headline info by headLine id
     */
    HeadLine queryHeadLineById(long lineId);

    /**
     * Search headLine info list by id (provide super admin to delete selected headline, mainly to process images)
     */
    List<HeadLine> queryHeadLineByIds(List<Long> lineIdList);

    /**
     * Insert a headLine
     */
    int insertHeadLine(HeadLine headLine);

    /**
     * Update headLine
     */
    int updateHeadLine(HeadLine headLine);

    /**
     * Delete headLine
     */
    int deleteHeadLine(long headLindId);

    /**
     * Delete multiple headLines
     */
    int batchDeleteHeadLine(List<Long> lineIdList);

}
