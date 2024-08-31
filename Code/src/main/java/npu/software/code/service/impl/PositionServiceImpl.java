package npu.software.code.service.impl;

import npu.software.code.mapper.PositionMapper;
import npu.software.code.pojo.Position;
import npu.software.code.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionMapper positionMapper;

    @Override
    public List<Position> list() {
        return positionMapper.list();
    }

    @Override
    public void add(Position position) {
        position.setCreateTime(LocalDateTime.now());
        position.setUpdateTime(LocalDateTime.now());
        positionMapper.insert(position);
    }
}
