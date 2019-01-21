# JSON Serializer

Serialize classes into JSON.
Very easy to use, just annotate the fields you wish to serialize into JSON with ```@JsonField``` and your class can be easily converted to a JSON string with the ```serialize()``` method.

## Usage
### Example Class
```java
import com.tropicalbastos.jsonserializr.JsonField;
import com.tropicalbastos.jsonserializr.JsonSerializeable;

public class Person implements JsonSerializeable {

    @JsonField
    private String name;

    @JsonField
    private int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setName(String name){
        this.name = name;
    }

}
```

### Example Implementation
```java
Person person = new Person("Steve", 30);
Serializer serializer = new Serializer();
String json = serializer.serialize(mc);
```

## Final Notes
And there you have it, if you find any bugs or issues let me know so they can be tracked and resolved.