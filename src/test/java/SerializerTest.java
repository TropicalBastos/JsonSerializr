import org.junit.Test;
import static org.junit.Assert.*;
import com.tropicalbastos.jsonserializr.*;
import java.util.List;
import java.util.ArrayList;

public class SerializerTest {

    @Test public void serializesFields() {
        MockClass mc = new MockClass("Steve", 30);
        Serializer serializer = new Serializer();
        try{
            String json = serializer.serialize(mc);
            assertEquals("{\"name\":\"Steve\",\"age\":\"30\"}", json);
        }catch(JsonSerializeException e){
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test public void serializesClassField() {
        MockClass mc = new MockClass("Ian", 24);
        MockClassParent mockClassParent = new MockClassParent(mc);
        Serializer serializer = new Serializer();
        try{
            String json = serializer.serialize(mockClassParent);
            assertEquals("{\"person\":{\"name\":\"Ian\",\"age\":\"24\"}", json);
        }catch(JsonSerializeException e) {
            System.out.println(e.getMessage());
            fail();
        }

    }

    @Test public void serializesLists() {
        List<MockClass> people = new ArrayList();
        people.add(new MockClass("Brienne", 40));
        people.add(new MockClass("Harold", 27));
        people.add(new MockClass("Hannah", 32));
        Serializer serializer = new Serializer();
        try {
            String json = serializer.serialize(people);
            assertEquals("[{\"name\":\"Brienne\",\"age\":\"40\"},{\"name\":\"Harold\",\"age\":\"27\"},{\"name\":\"Hannah\",\"age\":\"32\"}]", json);
        } catch(JsonSerializeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            fail();
        }
    }

}
