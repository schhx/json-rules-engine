package org.schhx.jsonrulesengine.handler;

import org.schhx.jsonrulesengine.ConditionItem;
import org.schhx.jsonrulesengine.Fact;

import java.util.List;

/**
 * @author shanchao
 * @date 2019-07-09
 */
public interface OperatorHandler {


    /**
     * 处理操作
     * @param conditionItem
     * @param facts
     * @return
     */
    boolean handle(ConditionItem conditionItem, List<Fact> facts);
}
