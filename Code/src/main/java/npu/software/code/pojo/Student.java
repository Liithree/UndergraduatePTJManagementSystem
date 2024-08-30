package npu.software.code.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student{
    /**
     * 学生用户id
     */
    private String uid;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 学院
     */
    private Integer dept;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 学号
     */
    private String num;

    /**
     * 性别
     */
    private Short gender;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * 总工作时长
     */
    private LocalDateTime workTime;

    /**
     * 薪资
     */
    private Double salary;
}
