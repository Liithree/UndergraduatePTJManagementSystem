package npu.software.code.service;

import npu.software.code.pojo.Position;

import java.util.List;

public interface PositionService {
    /**
     * 查询所有职位数据
     * @return
     */
    List<Position> list();

    /**
     * 添加岗位信息
     * @param position
     */
    void add(Position position);
}
