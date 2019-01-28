# JSON Serializer

Serialize classes into JSON.
Very easy to use, first implement the ```JsonSerializeable``` interface and just annotate the fields you wish to serialize into JSON with ```@JsonField``` and your class can be easily converted to a JSON string with the ```serialize()``` method. You can also serialize ```List<JsonSerializeable>``` into a json array by creating a list of objects that implement the JsonSerializeable interface.

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
try{
    String json = serializer.serialize(person);
}catch(JsonSerializeException e){
    System.out.println(e.getMessage());
}
```

### Example Implementation with Lists
```java
List<Person> people = new ArrayList();
people.add(new Person("Brienne", 40));
people.add(new Person("Harold", 27));
people.add(new Person("Hannah", 32));
Serializer serializer = new Serializer();
try {
    String json = serializer.serialize(people);
} catch(JsonSerializeException e) {
    System.out.println(e.getMessage());
}
```

## Nesting

Nesting fields is also supported, lets say you have a parent defined below:

```java
public class PersonContainer {

    @JsonField("person")
    public Person person;

    public PersonContainer(Person person){
        this.person = person;
    }

}

// and in another file/class:
Person person = new Person("Ian", 24);
PersonContainer personContainer = new PersonContainer(person);
Serializer serializer = new Serializer();
try{
    String json = serializer.serialize(personContainer);
}catch(JsonSerializeException e) {
    System.out.println(e.getMessage());
}
```

The above serialized output will produce:
```json
{
    "person": {
        "name" : "Ian",
        "age" : "24"
    }
}
```

## Final Notes
And there you have it, if you find any bugs or issues let me know so they can be tracked and resolved.