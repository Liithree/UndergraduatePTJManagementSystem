package npu.software.code.mapper;

import npu.software.code.pojo.Position;
import org.apache.ibatis.annotations.Insert;
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

    /**
     * 新增岗位
     * @param position
     */
    @Insert("insert into position (id_pos, name, description, image, dept, create_time, update_time, state, work_time, salary) values (#{idPos}, #{name}, #{description}, #{image}, #{dept}, #{createTime}, #{updateTime}, #{state}, #{workTime}, #{salary})")
    void insert(Position position);

    /**
     * 修改岗位信息
     * @param position
     */
    void update(Position position);
}
