package npu.software.code.service.impl;

import npu.software.code.StaticValue;
import npu.software.code.mapper.EvaluationMapper;
import npu.software.code.pojo.Evaluation;
import npu.software.code.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    private EvaluationMapper evaluationMapper;

    @Override
    public void add(String uid, String idPos, Integer stars, String content) {
        Evaluation evaluation = new Evaluation();
        evaluation.setUid(uid);
        evaluation.setIdPos(idPos);
        evaluation.setStars(stars);
        evaluation.setContent(content);
        evaluation.setCreateTime(LocalDateTime.now());
        evaluation.setIdEvl("EVL" + LocalDateTime.now().format(StaticValue.dtf));
        evaluationMapper.add(evaluation);
    }

    @Override
    public void delete(String idEvl) {
        evaluationMapper.delete(idEvl);
    }
}
