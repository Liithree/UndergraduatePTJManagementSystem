package npu.software.code.service;

import npu.software.code.pojo.Reply;

public interface RelpyService {
    /**
     * 管理员回复留言
     * @param reply
     */
    void add(Reply reply);
}
