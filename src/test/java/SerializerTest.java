import org.junit.Test;
import static org.junit.Assert.*;
import com.tropicalbastos.jsonserializr.*;

public class SerializerTest {

    @Test public void serializesFields() {
        MockClass mc = new MockClass("Steve", 30);
        Serializer serializer = new Serializer();
        try{
            String json = serializer.serialize(mc);
            assertEquals("{\"name\":\"Steve\",\"age\":\"30\"}", json);
        }catch(JsonSerializeException e){
            System.out.println(e.getMessage());
        }
    }

    @Test public void serializesClassField(){
        MockClass mc = new MockClass("Ian", 24);
        MockClassParent mockClassParent = new MockClassParent(mc);
        Serializer serializer = new Serializer();
        try{
            String json = serializer.serialize(mockClassParent);
            assertEquals("{\"person\":{\"name\":\"Ian\",\"age\":\"24\"}", json);
        }catch(JsonSerializeException e){
            System.out.println(e.getMessage());
        }

    }

}
