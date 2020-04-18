package com.myedu.project.store.domain.vo;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/4/18
 * Time: 13:17
 * Description:
 */
public class EvaluationStandard {
    private  String  content;
    private  BigDecimal score;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
