package org.schhx.jsonrulesengine.handler;

import org.schhx.jsonrulesengine.ConditionItem;
import org.schhx.jsonrulesengine.Fact;

import java.util.Arrays;
import java.util.List;

/**
 * @author shanchao
 * @date 2019-07-09
 */
public class OperatorHandlerComplete {

    private static final List<OperatorHandler> operatorHandlers = Arrays.asList(new EqHandler());

    public static boolean handle(ConditionItem conditionItem, List<Fact> facts) {
        return operatorHandlers.stream().anyMatch(handler -> handler.handle(conditionItem, facts));
    }
}
