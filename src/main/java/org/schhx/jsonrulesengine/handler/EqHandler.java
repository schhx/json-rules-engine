package org.schhx.jsonrulesengine.handler;

import org.schhx.jsonrulesengine.ConditionItem;
import org.schhx.jsonrulesengine.Fact;
import org.schhx.jsonrulesengine.Operator;

/**
 * @author shanchao
 * @date 2019-07-09
 */
public class EqHandler extends AbstractHanlder {
    @Override
    public boolean canHandle(ConditionItem conditionItem) {
        return Operator.EQ == conditionItem.getOperator();
    }

    @Override
    public boolean doHandle(ConditionItem conditionItem, Fact fact) {
        return conditionItem.getValue().equals(fact.getValue());
    }
}
