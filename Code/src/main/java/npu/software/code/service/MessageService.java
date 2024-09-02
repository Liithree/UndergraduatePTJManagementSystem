package npu.software.code.service;

import npu.software.code.pojo.Message;

import java.util.List;

public interface MessageService {
    /**
     * 管理员查看所有留言
     * @return
     */
    List<Message> list(String token);

    /**
     * 用户添加留言
     * @param message
     */
    void add(Message message);

    /**
     * 更新留言状态
     * @param idMsg
     */
    void updateState(String idMsg);
}
