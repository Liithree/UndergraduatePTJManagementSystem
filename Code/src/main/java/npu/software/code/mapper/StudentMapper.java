package npu.software.code.mapper;

import npu.software.code.pojo.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    void update(Student student);

    @Update("update student set password = #{newPwd}, update_time = now() where uid = #{uid}")
    void updatePwd(String newPwd, String uid);

    @Insert("insert into student (uid, name, password, dept, create_time, update_time, gender, age, work_time, salary) values (#{uid}, #{name}, #{password}, #{dept}, now(), now(), #{gender}, #{age}, #{workTime}, #{salary})")
    void add(Student student);
}
