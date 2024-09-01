package npu.software.code.service;

import npu.software.code.pojo.Student;

import java.util.List;

public interface StudentService {
    /**
     * 学生登录
     * @param student
     * @return
     */
    Student login(Student student);

    /**
     * 查询所有学生信息
     * @return
     */
    List<Student> list();

    Student getByUid(String uid);

    void update(Student student);

    /**
     * 更新密码
     * @param newPwd
     */
    void updatePwd(String newPwd, String uid);
}
