package npu.software.code.service;

import npu.software.code.pojo.WorkBill;

import java.util.List;

public interface WorkBillService {

    /**
     * 新建工单
     * @param workBill
     * @return
     */
    void add(WorkBill workBill, String token);

    /**
     * 管理员查看所有工单
     * @return
     */
    List<WorkBill> list();

    /**
     * 用户查看自己的工单
     */
    List<WorkBill> list(String token);

    /**
     * 变更岗位状态
     * @param workBill
     */
    void update(WorkBill workBill);
}
