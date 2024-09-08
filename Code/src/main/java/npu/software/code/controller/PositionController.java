package npu.software.code.controller;

import lombok.extern.slf4j.Slf4j;
import npu.software.code.StaticValue;
import npu.software.code.pojo.Position;
import npu.software.code.pojo.Result;
import npu.software.code.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class PositionController {

    @Autowired
    private PositionService positionService;

    /**
     * 添加岗位信息
     * API接口文档：1.3
     */
    @PostMapping("/root/positions")
    public Result add(@RequestBody Position position, @RequestHeader(name = "Authorization") String token){
        String uid = StaticValue.getUid(token);
        if(!StaticValue.rootAccount.equals(uid)){
            return Result.error("权限不足");
        }
        log.info("添加岗位：{}", position);

        // 调用service添加岗位信息
        positionService.add(position);
        return Result.success();
    }

    /**
     * 更新岗位信息
     * API接口文档：1.4
     */
    @PutMapping("/root/positions")
    public Result update(@RequestBody Position position){
        log.info("更新岗位信息：{}", position);

        // 调用service更新岗位信息
        positionService.update(position);
        return Result.success();
    }

    /**
     * 根据筛选信息查询岗位
     * API接口文档：2.2
     */
    @GetMapping("/positions")
    public Result search(String name, String description, Integer dept,
                         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                         Integer state,
                         Double workTimeLow, Double workTimeUp,
                         Double salaryLow, Double salaryUp){
        log.info("根据筛选信息查询岗位");
        List<Position> positions = positionService.search(name, description, dept, begin, end, state, workTimeLow, workTimeUp, salaryLow, salaryUp);
        return Result.success(positions);
    }

}
