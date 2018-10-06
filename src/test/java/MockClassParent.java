import com.tropicalbastos.jsonserializr.JsonField;

public class MockClassParent {

    @JsonField("person")
    public MockClass mockClass;

    public MockClassParent(MockClass mc){
        this.mockClass = mc;
    }

}