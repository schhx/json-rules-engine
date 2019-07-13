package org.schhx.jsonrulesengine;

import lombok.AllArgsConstructor;

import java.util.List;

/**
 * @author shanchao
 * @date 2019-07-09
 */
@AllArgsConstructor
public class JsonRule {

    private Condition condition;

    private Object response;

    public boolean match(List<Fact> facts) {
        return condition.match(facts);
    }

    public Object run(List<Fact> facts) {
        return match(facts) ? response : null;
    }
}
