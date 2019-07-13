# json-rules-engine
Java 实现简单的 json-rules-engine，灵感来源于
[json-rules-engine](https://github.com/CacheControl/json-rules-engine)。

## 基本用法

### 构建规则 JsonRule

JsonRule 包含两个部分，condition 以及 response。你可以通过如下两种方式构建 JsonRule：

-  通过构造方法 ```new JsonRule(Condition condition, Object response)```。
-  通过json string 构造 ```JsonRuleEngine.parseOne(String jsonString)```，推荐使用。

json string 示例如下：

```
{
    "condition":{
        "ANY":[
            {
                "ALL":[
                    {
                        "key":"gameDuration",
                        "operator":"EQ",
                        "value":40
                    },
                    {
                        "key":"personalFoulCount",
                        "operator":"EQ",
                        "value":5
                    }
                ]
            },
            {
                "ALL":[
                    {
                        "key":"gameDuration",
                        "operator":"EQ",
                        "value":5
                    },
                    {
                        "key":"personalFoulCount",
                        "operator":"EQ",
                        "value":40
                    }
                ]
            }
        ]
    },
    "response":"xx"
}
```

### 实际值 Fact

包含两个字段 key 和 value，与 condition 中 key/value 比较。

### 支持操作

- ANY 或
- ALL 与
- EQ 相等，使用 equals 判断
- ALL_IN 实际值全部包含在条件值内，两者必须都是 Collection
- ANY_IN 实际值任意一个包含在条件值内，两者必须都是 Collection

### 简单示例

```
boolean match = JsonRuleEngine.parseOne("{\n" +
                "    \"condition\":{\n" +
                "        \"ANY\":[\n" +
                "            {\n" +
                "                \"ALL\":[\n" +
                "                    {\n" +
                "                        \"key\":\"gameDuration\",\n" +
                "                        \"operator\":\"EQ\",\n" +
                "                        \"value\":40\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"key\":\"personalFoulCount\",\n" +
                "                        \"operator\":\"EQ\",\n" +
                "                        \"value\":5\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"ALL\":[\n" +
                "                    {\n" +
                "                        \"key\":\"gameDuration\",\n" +
                "                        \"operator\":\"EQ\",\n" +
                "                        \"value\":5\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"key\":\"personalFoulCount\",\n" +
                "                        \"operator\":\"EQ\",\n" +
                "                        \"value\":40\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"response\":\"xx\"\n" +
                "}").match(Arrays.asList(new Fact("gameDuration", 5), new Fact("personalFoulCount", 40)));
        Assert.assertTrue(match);
```
