package npu.software.code.service;

import npu.software.code.pojo.Position;

import java.time.LocalDate;
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

    /**
     * 修改岗位信息
     * @param position
     */
    void update(Position position);

    /**
     * 通过id查询岗位信息
     * @param idPos
     * @return
     */
    Position getById(String idPos);

    /**
     * 根据筛选信息查询岗位
     * @return
     */
    List<Position> search(String name, String description, Integer dept, LocalDate begin, LocalDate end, Integer state, Double workTimeLow, Double workTimeUp, Double salaryLow, Double salaryUp);
}
