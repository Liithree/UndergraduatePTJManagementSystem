package npu.software.code.mapper;

import npu.software.code.pojo.Evaluation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EvaluationMapper {
    @Insert("insert into evaluation values (#{idEvl}, #{uid}, #{idPos}, #{stars}, #{content}, #{createTime})")
    void add(Evaluation evaluation);
}
