package npu.software.code.mapper;

import npu.software.code.pojo.WorkBill;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface WorkBillMapper {
    @Insert("insert into work_bill (id_wb, uid, id_pos, create_time, update_time, state, sign_in_time, sign_out_time) values (#{idWb}, #{uid}, #{idPos}, #{createTime}, #{updateTime}, #{state}, #{signInTime}, #{signOutTime})")
    void add(WorkBill workBill);

    @Select("select * from work_bill")
    List<WorkBill> list();

    @Select("select * from work_bill where uid = #{uid}")
    List<WorkBill> listByUid(String uid);

    @Update("update work_bill set update_time = #{updateTime}, state = #{state}, sign_in_time = #{signInTime}, sign_out_time = #{signOutTime} where id_wb = #{idWb}")
    void update(WorkBill workBill);

    @Select("select * from work_bill where id_wb = #{idWb}")
    WorkBill getById(String idWb);
}
