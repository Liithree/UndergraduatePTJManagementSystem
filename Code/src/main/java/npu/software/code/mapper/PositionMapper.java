package npu.software.code.mapper;

import npu.software.code.pojo.Position;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PositionMapper {
    /**
     * 查询所有职位数据
     * @return
     */
    @Select("select * from position")
    List<Position> list();

    void insert(Position position);
}
