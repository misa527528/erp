package com.cqupt.mis.erp.manager.history;

import com.cqupt.mis.erp.model.enterpriseevaluate.AdminIncomeBean;
import com.cqupt.mis.erp.model.enterpriseevaluate.Member;
import com.cqupt.mis.erp.model.enterpriseevaluate.UserInputAndOutputOfAd;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("hisGameGroupMemberDao")
public interface HisGameGroupMemberDao {
    List<AdminIncomeBean> getUserUnique(@Param("groupname") String groupname,
                                        @Param("currentperiod") int currentperiod);

    List<UserInputAndOutputOfAd> getGroupMembers(String groupName);

    List<Member> getGroup_Members(String groupName);

    Member getGroup_Member(String userunique);

    /**
     * 查看历史记录中的时间.
     * @param userUnique
     * @return
     */
    Integer findHistoryCurrentTime(String userUnique);
}
