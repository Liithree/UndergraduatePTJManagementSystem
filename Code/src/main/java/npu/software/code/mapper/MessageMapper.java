package npu.software.code.mapper;

import npu.software.code.pojo.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Select("select * from message order by create_time desc")
    List<Message> list();

    @Select("select * from message where uid = #{uid} order by create_time desc")
    List<Message> listByUid(String uid);

    @Insert("insert into message (id_msg, uid, content, create_time, state) values (#{idMsg}, #{uid}, #{content}, #{createTime}, #{state})")
    void insert(Message message);

    @Update("update message set state = 1 where id_msg = #{idMsg}")
    void updateState(String idMsg);
}
