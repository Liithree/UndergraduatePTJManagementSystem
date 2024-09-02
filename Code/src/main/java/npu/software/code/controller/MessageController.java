package npu.software.code.controller;

import lombok.extern.slf4j.Slf4j;
import npu.software.code.StaticValue;
import npu.software.code.pojo.Message;
import npu.software.code.pojo.Result;
import npu.software.code.service.MessageService;
import npu.software.code.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * 查看留言，根据用户权限返回不同的留言
     * @return
     */
    @GetMapping("/messages")
    public Result list(@RequestHeader(name = "Authorization") String token){
        log.info("管理员查看所有留言");

        List<Message> messages = messageService.list(token);
        return Result.success(messages);
    }

    /**
     * 用户添加留言
     * @param message
     * @return
     */
    @PostMapping("/student/messages")
    public Result add(@RequestBody Message message, @RequestHeader(name = "Authorization") String token){
        String uid = StaticValue.getUid(token);
        message.setUid(uid);
        log.info("用户添加留言：{}", message);

        messageService.add(message);
        return Result.success();
    }

}
