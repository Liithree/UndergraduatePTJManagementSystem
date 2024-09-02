package npu.software.code.service.impl;

import npu.software.code.StaticValue;
import npu.software.code.mapper.ReplyMapper;
import npu.software.code.pojo.Reply;
import npu.software.code.service.RelpyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReplyServiceImpl implements RelpyService {
    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public void add(Reply reply) {
        reply.setCreateTime(LocalDateTime.now());
        reply.setIdReply("RP" + LocalDateTime.now().format(StaticValue.dtf));
        replyMapper.add(reply);

    }
}
