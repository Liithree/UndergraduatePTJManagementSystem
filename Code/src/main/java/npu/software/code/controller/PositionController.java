package npu.software.code.controller;

import lombok.extern.slf4j.Slf4j;
import npu.software.code.pojo.Position;
import npu.software.code.pojo.Result;
import npu.software.code.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class PositionController {

    @Autowired
    private PositionService positionService;

    /**
     * 查询所有岗位数据
     * @return
     */
    @GetMapping("/positions")
    public Result list(){
        log.info("查询所有职位数据");

        // 调用service查询职位数据
        List<Position> positions = positionService.list();
        return Result.success(positions);
    }

    /**
     * 添加岗位信息
     * @return
     */
    @PostMapping("/positions")
    public Result add(@RequestBody Position position){
        log.info("添加岗位：{}", position);

        // 调用service添加岗位信息
        positionService.add(position);
        return Result.success();
    }

    /**
     * 更新岗位信息
     * @param position
     * @return
     */
    @PutMapping("/positions")
    public Result update(@RequestBody Position position){
        log.info("更新岗位信息：{}", position);

        // 调用service更新岗位信息
        positionService.update(position);
        return Result.success();
    }
}
