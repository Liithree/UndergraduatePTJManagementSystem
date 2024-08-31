package npu.software.code.mapper;

import npu.software.code.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

    /**
     * 根据学号和密码查询学生
     * @param student
     * @return
     */
    @Select("select * from student where uid = #{uid} and password = #{password}")
    Student getByUidAndPassword(Student student);

    /**
     * 查询所有学生信息
     * @return
     */
    @Select("select * from student")
    List<Student> list();

    @Select("select * from student where uid = #{uid}")
    Student getByUid(String uid);
}
