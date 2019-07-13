package org.schhx.jsonrulesengine;

import lombok.Getter;
import org.schhx.jsonrulesengine.handler.OperatorHandlerComplete;

import java.util.List;

/**
 * @author shanchao
 * @date 2019-07-09
 */
@Getter
public class ConditionItem extends Condition {

    private String key;

    private Object value;

    public ConditionItem(Operator operator, String key, Object value) {
        super(operator);
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean match(List<Fact> facts) {
        return OperatorHandlerComplete.handle(this, facts);
    }
}
