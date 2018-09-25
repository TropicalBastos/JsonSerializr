import com.tropicalbastos.jsonserializr.JsonField;

public class MockClass {

    @JsonField
    private String name;

    @JsonField
    private int age;

    public MockClass(String name, int age){
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