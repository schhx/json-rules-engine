package org.schhx.jsonrulesengine;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class JsonRuleEngineTest {
    @Test
    public void parseOne() throws Exception {
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
    }

}