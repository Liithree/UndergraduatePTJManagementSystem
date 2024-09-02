package npu.software.code.service.impl;

import io.jsonwebtoken.Claims;
import npu.software.code.StaticValue;
import npu.software.code.mapper.WorkBillMapper;
import npu.software.code.pojo.WorkBill;
import npu.software.code.service.WorkBillService;
import npu.software.code.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkBillServiceImpl implements WorkBillService {
    @Autowired
    private WorkBillMapper workBillMapper;

    @Override
    public void add(WorkBill workBill, String token) {
        Claims claims = JwtUtils.parseJwt(token);
        String uid = (String) claims.get("uid");
        workBill.setUid(uid);
        LocalDateTime now = LocalDateTime.now();
        workBill.setCreateTime(now);
        workBill.setUpdateTime(now);
        workBill.setIdWb("WB" + now.format(StaticValue.dtf));
        workBill.setState(0);
        workBillMapper.add(workBill);
    }

    @Override
    public List<WorkBill> list() {
        return workBillMapper.list();
    }

    @Override
    public List<WorkBill> list(String token) {
        Claims claims = JwtUtils.parseJwt(token);
        String uid = (String) claims.get("uid");
        return workBillMapper.listByUid(uid);
    }

    @Override
    public void update(WorkBill workBill) {
        workBill.setUpdateTime(LocalDateTime.now());
        workBillMapper.update(workBill);
    }
}
