package springboottools.serializer;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import springboottools.enums.IEnum;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * 序列化枚举类型
 */
@Slf4j
public class JsonEnumSerializer extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
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
                JsonEnum annotation = field.getAnnotation(JsonEnum.class);
                if (annotation != null) {
                    Class<?> enumClass = annotation.value();
                    if (enumClass.isEnum()) {
                        String jsonKey = annotation.key();
                        String key = currentName + "Value";
                        if (StrUtil.isNotBlank(jsonKey)) {
                            key = jsonKey;
                        }
                        String enumValue = null;
                        Object[] enumConstant = enumClass.getEnumConstants();
                        if (enumConstant != null) {
                            for (int i = 0; i < enumConstant.length; i++) {
                                IEnum iEnum = (IEnum) enumConstant[i];
                                int code = iEnum.getCode();
                                if (value.equals(code)) {
                                    enumValue = iEnum.getValue();
                                    break;
                                }
                            }
                        }
                        gen.writeFieldName(key);
                        gen.writeString(enumValue);
                    }
                }
            } catch (NoSuchFieldException e) {
                log.warn(e.getMessage(), e);
            }
        }
    }
}
