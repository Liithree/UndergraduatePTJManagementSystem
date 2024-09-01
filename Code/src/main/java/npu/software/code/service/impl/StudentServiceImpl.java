package npu.software.code.service.impl;

import npu.software.code.mapper.StudentMapper;
import npu.software.code.pojo.Student;
import npu.software.code.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student login(Student student) {
        return studentMapper.getByUidAndPassword(student);
    }

    @Override
    public List<Student> list() {
        return studentMapper.list();
    }

    @Override
    public Student getByUid(String uid) {
        return studentMapper.getByUid(uid);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void updatePwd(String newPwd, String uid) {
        studentMapper.updatePwd(newPwd, uid);
    }
}
