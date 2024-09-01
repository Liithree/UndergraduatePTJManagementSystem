package npu.software.code.service;

import npu.software.code.pojo.WorkBill;

public interface WorkBillService {

    /**
     * 新建工单
     * @param workBill
     * @return
     */
    void add(WorkBill workBill);
}
