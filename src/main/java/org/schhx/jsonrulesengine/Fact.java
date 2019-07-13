package org.schhx.jsonrulesengine;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shanchao
 * @date 2019-07-09
 */
@AllArgsConstructor
@Getter
public class Fact {

    private String key;

    private Object value;

    public boolean match(String key, Object value) {
        return this.key.equals(key) && this.value.equals(value);
    }
}
