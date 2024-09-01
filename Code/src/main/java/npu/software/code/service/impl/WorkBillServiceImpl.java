package npu.software.code.service.impl;

import npu.software.code.mapper.WorkBillMapper;
import npu.software.code.pojo.WorkBill;
import npu.software.code.service.WorkBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class WorkBillServiceImpl implements WorkBillService {
    @Autowired
    private WorkBillMapper workBillMapper;

    @Override
    public void add(WorkBill workBill) {
        LocalDateTime now = LocalDateTime.now();
        workBill.setCreateTime(now);
        workBill.setUpdateTime(now);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String id = "wb" + now.format(dtf);


    }
}
