package org.schhx.jsonrulesengine.handler;

import org.schhx.jsonrulesengine.ConditionItem;
import org.schhx.jsonrulesengine.Fact;

import java.util.List;

/**
 * @author shanchao
 * @date 2019-07-09
 */
public abstract class AbstractHanlder implements OperatorHandler {

    @Override
    public boolean handle(ConditionItem conditionItem, List<Fact> facts) {
        return canHandle(conditionItem) && facts.stream().anyMatch(fact -> conditionItem.getKey().equals(fact.getKey()) && doHandle(conditionItem, fact));
    }
    /**
     * 是否能处理操作
     * @param conditionItem
     * @return
     */
    protected abstract boolean canHandle(ConditionItem conditionItem);

    /**
     * 处理操作
     * @param conditionItem
     * @param fact
     * @return
     */
    protected abstract boolean doHandle(ConditionItem conditionItem, Fact fact);
}
