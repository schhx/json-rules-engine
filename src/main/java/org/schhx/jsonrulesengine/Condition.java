package org.schhx.jsonrulesengine;


import lombok.Getter;

import java.util.List;

/**
 * @author shanchao
 * @date 2019-07-09
 */
@Getter
public class Condition {

    protected Operator operator;

    private List<Condition> subConditions;

    protected Condition(Operator operator) {
        this.operator = operator;
    }

    public Condition(Operator operator, List<Condition> subConditions) {
        this.operator = operator;
        this.subConditions = subConditions;
    }

    public boolean match(List<Fact> facts) {
        if(operator.equals(Operator.ALL)) {
            return subConditions.stream().allMatch(subCondition -> subCondition.match(facts));
        } else if(operator.equals(Operator.ANY)) {
            return subConditions.stream().anyMatch(subCondition -> subCondition.match(facts));
        }
        return false;
    }
}
