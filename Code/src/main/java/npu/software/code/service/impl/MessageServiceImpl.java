package npu.software.code.service.impl;

import npu.software.code.StaticValue;
import npu.software.code.mapper.MessageMapper;
import npu.software.code.pojo.Message;
import npu.software.code.service.MessageService;
import npu.software.code.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Message> list(String token) {
        // 解析token，如果是root用户则返回所有留言，否则返回用户自己的留言
        String uid = (String) JwtUtils.parseJwt(token).get("uid");
        if(!"root".equals(uid)) {
            return messageMapper.listByUid(uid);
        }else{
            return messageMapper.list();
        }
    }

    @Override
    public void add(Message message) {
        message.setState(0);
        message.setCreateTime(LocalDateTime.now());
        message.setIdMsg("MSG" + LocalDateTime.now().format(StaticValue.dtf));
        messageMapper.insert(message);
    }

    @Override
    public void updateState(String idMsg) {
        messageMapper.updateState(idMsg);
    }
}
