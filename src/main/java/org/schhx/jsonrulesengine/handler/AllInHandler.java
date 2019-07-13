package org.schhx.jsonrulesengine.handler;

import org.schhx.jsonrulesengine.ConditionItem;
import org.schhx.jsonrulesengine.Fact;
import org.schhx.jsonrulesengine.Operator;

import java.util.Collection;

/**
 * @author shanchao
 * @date 2019-07-09
 */
public class AllInHandler extends AbstractHanlder {
    @Override
    public boolean canHandle(ConditionItem conditionItem) {
        return Operator.ALL_IN == conditionItem.getOperator();
    }

    @Override
    public boolean doHandle(ConditionItem conditionItem, Fact fact) {
        if(conditionItem.getValue() instanceof Collection && fact.getValue() instanceof Collection) {
            Collection expected = (Collection) conditionItem.getValue();
            Collection factValue = (Collection) fact.getValue();
            return expected.containsAll(factValue);
        }
        return false;
    }
}
