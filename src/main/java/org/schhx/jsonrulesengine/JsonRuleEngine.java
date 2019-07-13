package org.schhx.jsonrulesengine;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shanchao
 * @date 2019-07-09
 */
@Slf4j
public class JsonRuleEngine {



    public static List<JsonRule> parse(String jsonString) {
        try {
            List<JSONObject> jsonObjects = new ArrayList<>();
            Object object = JSON.parseObject(jsonString);
            if (object instanceof JSONArray) {
                for (int i = 0; i < ((JSONArray) object).size(); i++) {
                    JSONObject jsonObject = ((JSONArray) object).getJSONObject(i);
                    jsonObjects.add(jsonObject);
                }

            } else if (object instanceof JSONObject) {
                jsonObjects.add((JSONObject) object);
            }
            return jsonObjects.stream().map(jsonObject -> parseOne(jsonObject)).filter(jsonRule -> jsonRule != null).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("parse jsonString error", e);
        }
        return new ArrayList<>();
    }

    public static JsonRule parseOne(String jsonString) {
        try {
            return parseOne(JSON.parseObject(jsonString));
        } catch (Exception e) {
            log.error("parseOne jsonString error", e);
        }
        return null;
    }

    public static JsonRule parseOne(JSONObject jsonObject) {
        try {
            JSONObject conditionJson = jsonObject.getJSONObject("condition");
            Object response = jsonObject.get("response");

            Condition condition;
            if (conditionJson != null && response != null && (condition = parseCondition(conditionJson)) != null) {
                return new JsonRule(condition, response);
            }
        } catch (Exception e) {
            log.error("parseOne jsonObject error", e);
        }
        return null;
    }

    private static Condition parseCondition(JSONObject jsonObject) {
        try {
            List<String> keys = new ArrayList<>(jsonObject.keySet());
            if (keys.size() == 1) {
                List<Condition> subConditions = jsonObject.getJSONArray(keys.get(0)).stream()
                        .map(o -> parseCondition((JSONObject) o))
                        .filter(condition -> condition != null)
                        .collect(Collectors.toList());
                return new Condition(Operator.valueOf(keys.get(0)), subConditions);
            } else {
                return new ConditionItem(
                        Operator.valueOf(jsonObject.getString("operator")),
                        jsonObject.getString("key"),
                        jsonObject.get("value")
                );
            }
        } catch (Exception e) {
            log.error("parseCondition jsonObject error", e);
        }
        return null;
    }
}
