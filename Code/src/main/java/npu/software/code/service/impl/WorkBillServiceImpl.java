package npu.software.code.service.impl;

import io.jsonwebtoken.Claims;
import npu.software.code.StaticValue;
import npu.software.code.mapper.PositionMapper;
import npu.software.code.mapper.WorkBillMapper;
import npu.software.code.pojo.Position;
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

    @Autowired
    private PositionMapper positionMapper;

    @Override
    public WorkBill add(WorkBill workBill, String token) {
        String uid = StaticValue.getUid(token);
        // 查询要申请的岗位状态是否为1
        Position p = positionMapper.getById(workBill.getIdPos());
        if (p.getState() != 1){
            return null;
        }

        // 设置工单的信息
        workBill.setUid(uid);
        LocalDateTime now = LocalDateTime.now();
        workBill.setCreateTime(now);
        workBill.setUpdateTime(now);
        workBill.setIdWb("WB" + now.format(StaticValue.dtf));
        workBill.setState(0);

        // 将新的工单添加至数据库
        workBillMapper.add(workBill);

        // 更新岗位状态
        p.setIdPos(workBill.getIdPos());
        p.setState(0);
        p.setUpdateTime(LocalDateTime.now());
        positionMapper.update(p);

        return workBill;
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

    @Override
    public WorkBill getById(String idWb) {
        return workBillMapper.getById(idWb);
    }
}
