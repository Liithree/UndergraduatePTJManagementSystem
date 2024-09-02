package npu.software.code.mapper;

import npu.software.code.pojo.Reply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapper {
    @Insert("insert into reply (id_reply, id_msg, content, create_time) values (#{idReply}, #{idMsg}, #{content}, #{createTime})")
    void add(Reply reply);
}
