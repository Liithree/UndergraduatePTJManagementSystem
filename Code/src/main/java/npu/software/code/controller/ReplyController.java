package npu.software.code.controller;

import lombok.extern.slf4j.Slf4j;
import npu.software.code.pojo.Reply;
import npu.software.code.pojo.Result;
import npu.software.code.service.MessageService;
import npu.software.code.service.RelpyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ReplyController {
    @Autowired
    private RelpyService relpyService;

    @Autowired
    private MessageService messageService;
    /**
     * 管理员回复留言
     * API接口文档：1.5
     */
    @PostMapping("/root/reply")
    public Result add(@RequestBody Reply reply){
        log.info("管理员回复留言：{}", reply);

        relpyService.add(reply);
        // 回复成功后，设置留言状态为已回复
        messageService.updateState(reply.getIdMsg());
        return Result.success();
    }
}
