package springboottools.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import springboottools.enums.SensitiveEnum;
import springboottools.tools.DesensitizeUtil;

import java.io.IOException;
import java.lang.reflect.Field;


@Slf4j
public class SensitiveSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        JsonStreamContext outputContext = gen.getOutputContext();
        String currentName = outputContext.getCurrentName();
        if (value != null) {
            Object currentValue = outputContext.getCurrentValue();
            try {
                Field field = currentValue.getClass().getDeclaredField(currentName);
                Sensitive annotation = field.getAnnotation(Sensitive.class);
                if (annotation != null) {
                    SensitiveEnum type=annotation.type();
                    gen.writeString(DesensitizeUtil.around(value,type.getBefore(),type.getAfter(),type.getMiddle()));
                }
            } catch (NoSuchFieldException e) {
                log.warn(e.getMessage(), e);
            }
        }
    }
}
