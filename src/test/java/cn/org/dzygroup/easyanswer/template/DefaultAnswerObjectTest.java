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
    private DefaultAnswerObject uncoverAnswer;
    private DefaultAnswerObject coverAnswer;

    @BeforeEach
    public void setUp() throws Exception {
        uncoverAnswer = new DefaultAnswerObject();
        coverAnswer = new DefaultAnswerObject(true);
    }


    // 不应该覆盖模板属性
    @Test
    void uncoverPut1() {
        uncoverAnswer.putTemplateProperty(oldKey1, oldValue1);
        uncoverAnswer.put(oldKey1, newValue1);
        assertEquals(newValue1, uncoverAnswer.get(oldKey1));
    }

    @Test
    void uncoverPut2() {
        uncoverAnswer.put(oldKey1, newValue1);
        uncoverAnswer.putTemplateProperty(oldKey1, oldValue1);
        assertEquals(newValue1, uncoverAnswer.get(oldKey1));
    }

    @Test
    void coverPut1() {
        coverAnswer.putTemplateProperty(oldKey1, oldValue1);
        coverAnswer.put(oldKey1, newValue1);
        assertEquals(newValue1, coverAnswer.get(oldKey1));
    }

    @Test
    void coverPut2() {
        coverAnswer.put(oldKey1, newValue1);
        coverAnswer.putTemplateProperty(oldKey1, oldValue1);
        assertEquals(newValue1, coverAnswer.get(oldKey1));
    }

    @Test
    void uncoverClear1() {
        uncoverAnswer.putTemplateProperty(oldKey1, oldValue1);
        uncoverAnswer.putTemplateProperty(oldKey2, oldValue2);
        uncoverAnswer.put(newKey1, newValue1);
        uncoverAnswer.clear();
        assertEquals(2, uncoverAnswer.getProperties().size());
    }

    @Test
    void uncoverClear2() {
        uncoverAnswer.put(newKey1, newValue1);
        uncoverAnswer.putTemplateProperty(oldKey1, oldValue1);
        uncoverAnswer.putTemplateProperty(oldKey2, oldValue2);
        uncoverAnswer.clear();
        assertEquals(2, uncoverAnswer.getProperties().size());
    }

    @Test
    void coverClear1() {
        coverAnswer.putTemplateProperty(oldKey1, oldValue1);
        coverAnswer.putTemplateProperty(oldKey2, oldValue2);
        coverAnswer.put(newKey1, newValue1);
        coverAnswer.clear();
        assertEquals(2, coverAnswer.getProperties().size());
    }

    @Test
    void coverClear2() {
        coverAnswer.put(newKey1, newValue1);
        coverAnswer.putTemplateProperty(oldKey1, oldValue1);
        coverAnswer.putTemplateProperty(oldKey2, oldValue2);
        coverAnswer.clear();
        assertEquals(2, coverAnswer.getProperties().size());
    }

    @Test
    void uncoverRemove1() {
        uncoverAnswer.putTemplateProperty(oldKey1, oldValue1);
        uncoverAnswer.remove(oldKey1);
        assertFalse(uncoverAnswer.getProperties().isEmpty());

    }

    @Test
    void uncoverRemove2() {
        uncoverAnswer.put(oldKey1, oldValue1);
        uncoverAnswer.remove(oldKey1);
        assertTrue(uncoverAnswer.getProperties().isEmpty());
    }

    @Test
    void uncoverRemove3() {
        uncoverAnswer.putTemplateProperty(oldKey1, oldValue1);
        uncoverAnswer.put(oldKey1, oldValue1);
        uncoverAnswer.remove(oldKey1);
        assertEquals(1, uncoverAnswer.getProperties().size());
    }

    @Test
    void uncoverRemove4() {
        uncoverAnswer.put(oldKey1, oldValue1);
        uncoverAnswer.putTemplateProperty(oldKey1, oldValue1);
        uncoverAnswer.remove(oldKey1);
        assertEquals(1, uncoverAnswer.getProperties().size());
    }

    @Test
    public void uncoverToMap() {
        uncoverAnswer.putTemplateProperty(oldKey1, oldValue1);
        uncoverAnswer.putTemplateProperty(oldKey2, oldValue2);
        uncoverAnswer.put(oldKey1, newValue1);
        uncoverAnswer.put(oldKey2, newValue2);

        Map<String, Object> map = uncoverAnswer.toMap();
        assertEquals(2, map.size());
        assertTrue(map.containsValue(oldValue1));
        assertTrue(map.containsValue(oldValue2));
    }
}