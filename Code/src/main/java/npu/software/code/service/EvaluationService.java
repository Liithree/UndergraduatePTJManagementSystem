package npu.software.code.service;

import npu.software.code.pojo.Evaluation;

public interface EvaluationService {
    /**
     * 用户给岗位添加评价
     */
    void add(String uid, String idPos, Integer stars, String content);

    /**
     * 管理员删除不当评价
     */
    void delete(String idEvl);
}
