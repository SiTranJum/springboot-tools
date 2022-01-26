package springboottools.serializer;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

/**
 * @author HEMINGLIANG823
 * @USER: HEMINGLIANG823
 * @DATE: 2019/5/20
 **/
public class ExtendJacksonAnnotationIntrospector extends JacksonAnnotationIntrospector {

    @Override
    public Object findSerializer(Annotated a) {
        JsonEnum jsonEnum = a.getAnnotation(JsonEnum.class);
        if (jsonEnum != null) {
            return JsonEnumSerializer.class;
        }
        JsonMoney jsonMoney = a.getAnnotation(JsonMoney.class);
        if (jsonMoney != null) {
            return JsonMoneySerializer.class;
        }
        JsonMile jsonMile = a.getAnnotation(JsonMile.class);
        if (jsonMile != null) {
            return JsonMileSerializer.class;
        }
        Sensitive sensitive = a.getAnnotation(Sensitive.class);
        if(sensitive != null){
            return SensitiveSerializer.class;
        }
        return super.findSerializer(a);
    }
}
