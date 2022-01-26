package springboottools.serializer;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 序列化里程公里
 */
@Slf4j
public class JsonMileSerializer extends JsonSerializer<Long> {
    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(value);
        JsonStreamContext outputContext = gen.getOutputContext();
        String currentName = outputContext.getCurrentName();
        if (value != null) {
            Object currentValue = outputContext.getCurrentValue();
            try {
                Field field = null;
                try{
                    field = currentValue.getClass().getDeclaredField(currentName);
                }catch (Exception e){
                    field = currentValue.getClass().getSuperclass().getDeclaredField(currentName);
                }
                JsonMile annotation = field.getAnnotation(JsonMile.class);
                if (annotation != null) {
                    String key = annotation.key();
                    if (StrUtil.isBlank(key)) {
                        key = currentName + "kkm";
                    }
                    BigDecimal result = BigDecimal.valueOf(value).divide(BigDecimal.valueOf(1000000));
                    DecimalFormat df = new DecimalFormat("0.000");
                    gen.writeFieldName(key);
                    gen.writeString(df.format(result));
                }
            } catch (NoSuchFieldException e) {
                log.warn(e.getMessage(), e);
            }
        }
    }
}
