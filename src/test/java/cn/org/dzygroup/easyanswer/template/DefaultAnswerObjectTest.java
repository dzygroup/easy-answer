package cn.org.dzygroup.easyanswer.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 戴志勇
 */
public class DefaultAnswerObjectTest {

    private final String oldKey1 = "oldKey1";
    private final String oldKey2 = "oldKey2";
    private final String oldValue1 = "oldValue1";
    private final String oldValue2 = "oldValue2";
    private final String newKey1 = "newKey1";
    private final String newKey2 = "newKey2";
    private final String newValue1 = "newValue1";
    private final String newValue2 = "newValue2";
    private DefaultAnswerObject unmodifiableAnswer;
    private DefaultAnswerObject modifiableAnswer;

    @BeforeEach
    public void setUp() {
        unmodifiableAnswer = new DefaultAnswerObject();
        modifiableAnswer = new DefaultAnswerObject(true);
    }


    // 不应该覆盖模板属性
    @Test
    void uncoverPut1() {
        unmodifiableAnswer.putTemplateProperty(oldKey1, oldValue1);
        unmodifiableAnswer.put(oldKey1, newValue1);
        assertEquals(oldValue1, unmodifiableAnswer.get(oldKey1));
    }

    @Test
    void uncoverPut2() {
        unmodifiableAnswer.put(oldKey1, newValue1);
        unmodifiableAnswer.putTemplateProperty(oldKey1, oldValue1);
        assertEquals(oldValue1, unmodifiableAnswer.get(oldKey1));
    }

    @Test
    void coverPut1() {
        modifiableAnswer.putTemplateProperty(oldKey1, oldValue1);
        modifiableAnswer.put(oldKey1, newValue1);
        assertEquals(newValue1, modifiableAnswer.get(oldKey1));
    }

    @Test
    void coverPut2() {
        modifiableAnswer.put(oldKey1, newValue1);
        modifiableAnswer.putTemplateProperty(oldKey1, oldValue1);
        assertEquals(oldValue1, modifiableAnswer.get(oldKey1));
    }

    @Test
    void uncoverClear1() {
        unmodifiableAnswer.putTemplateProperty(oldKey1, oldValue1);
        unmodifiableAnswer.putTemplateProperty(oldKey2, oldValue2);
        unmodifiableAnswer.put(newKey1, newValue1);
        unmodifiableAnswer.clear();
        assertEquals(0, unmodifiableAnswer.getProperties().size());
    }

    @Test
    void uncoverClear2() {
        unmodifiableAnswer.put(newKey1, newValue1);
        unmodifiableAnswer.putTemplateProperty(oldKey1, oldValue1);
        unmodifiableAnswer.putTemplateProperty(oldKey2, oldValue2);
        unmodifiableAnswer.clear();
        assertEquals(0, unmodifiableAnswer.getProperties().size());
    }

    @Test
    void coverClear1() {
        modifiableAnswer.putTemplateProperty(oldKey1, oldValue1);
        modifiableAnswer.putTemplateProperty(oldKey2, oldValue2);
        modifiableAnswer.put(newKey1, newValue1);
        modifiableAnswer.clear();
        assertEquals(0, modifiableAnswer.getProperties().size());
    }

    @Test
    void coverClear2() {
        modifiableAnswer.put(newKey1, newValue1);
        modifiableAnswer.putTemplateProperty(oldKey1, oldValue1);
        modifiableAnswer.putTemplateProperty(oldKey2, oldValue2);
        modifiableAnswer.clear();
        assertEquals(0, modifiableAnswer.getProperties().size());
    }

    @Test
    void uncoverRemove1() {
        unmodifiableAnswer.putTemplateProperty(oldKey1, oldValue1);
        unmodifiableAnswer.remove(oldKey1);
        assertFalse(unmodifiableAnswer.getProperties().isEmpty());

    }

    @Test
    void uncoverRemove2() {
        unmodifiableAnswer.put(oldKey1, oldValue1);
        unmodifiableAnswer.remove(oldKey1);
        assertTrue(unmodifiableAnswer.getProperties().isEmpty());
    }

    @Test
    void uncoverRemove3() {
        unmodifiableAnswer.putTemplateProperty(oldKey1, oldValue1);
        unmodifiableAnswer.put(oldKey1, oldValue1);
        unmodifiableAnswer.remove(oldKey1);
        assertEquals(1, unmodifiableAnswer.getProperties().size());
    }

    @Test
    void uncoverRemove4() {
        unmodifiableAnswer.put(oldKey1, oldValue1);
        unmodifiableAnswer.putTemplateProperty(oldKey1, oldValue1);
        unmodifiableAnswer.remove(oldKey1);
        assertEquals(1, unmodifiableAnswer.getProperties().size());
    }

    @Test
    public void unmodifiableGetProperties() {
        unmodifiableAnswer.putTemplateProperty(oldKey1, oldValue1);
        unmodifiableAnswer.putTemplateProperty(oldKey2, oldValue2);
        unmodifiableAnswer.put(oldKey1, newValue1);
        unmodifiableAnswer.put(oldKey2, newValue2);

        Map<String, Object> map = unmodifiableAnswer.getProperties();
        assertEquals(2, map.size());
        assertTrue(map.containsValue(oldValue1));
        assertTrue(map.containsValue(oldValue2));
    }

    @Test
    void putComputable1() {
        unmodifiableAnswer.putTemplateProperty(oldKey1, oldValue1);
        unmodifiableAnswer.put(oldKey1, new Computable() {
            @Override
            public Object compute() {
                return newValue1;
            }
        });

        assertEquals(oldValue1, unmodifiableAnswer.get(oldKey1));

        modifiableAnswer.put(oldKey1, new Computable() {
            @Override
            public Object compute() {
                return newValue1;
            }
        });

        assertEquals(newValue1, modifiableAnswer.get(oldKey1));
    }

    @Test
    void getProperties1() {
        unmodifiableAnswer.putTemplateProperty("t1", "t1");
        unmodifiableAnswer.put("t2", "t2");
        assertEquals(2, unmodifiableAnswer.getProperties().size());
    }

    @Test
    void getProperties2() {
        modifiableAnswer.put("t1", "t1");
        modifiableAnswer.putTemplateProperty("t2", "t2");
        assertEquals(2, modifiableAnswer.getProperties().size());
    }
}