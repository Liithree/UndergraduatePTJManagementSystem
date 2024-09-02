package npu.software.code.service.impl;

import npu.software.code.mapper.PositionMapper;
import npu.software.code.pojo.Position;
import npu.software.code.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        position.setIdPos("POS" + now.format(dtf));
        position.setCreateTime(now);
        position.setUpdateTime(now);
        position.setState(1);
        positionMapper.insert(position);
    }

    @Override
    public void update(Position position) {
        position.setUpdateTime(LocalDateTime.now());
        positionMapper.update(position);
    }

    @Override
    public Position getById(String idPos) {
        return positionMapper.getById(idPos);
    }

    @Override
    public List<Position> search(String name, String description, Integer dept, LocalDate begin, LocalDate end, Integer state, Double workTimeLow, Double workTimeUp, Double salaryLow, Double salaryUp) {
        return positionMapper.search(name, description, dept, begin, end, state, workTimeLow, workTimeUp, salaryLow, salaryUp);
    }
}
