package springboottools.config;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AuditMetaObjectHandler implements MetaObjectHandler {

    private static final String CREATED_AT = "createdAt";
    private static final String CREATED_BY = "createdBy";
    private static final String UPDATED_AT = "updatedAt";
    private static final String UPDATED_BY = "updatedBy";
    private static final String GUID = "guid";
    private static final String VERSION = "versions";
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();

        //新增时间
        if (metaObject.hasSetter(CREATED_AT) && null == this.getFieldValByName(CREATED_AT, metaObject)) {
            Field createAtField = getFieldByName(metaObject.getOriginalObject(), CREATED_AT);
            if(createAtField.getType() == LocalDateTime.class) {
                this.strictInsertFill(metaObject, CREATED_AT, LocalDateTime.class, now);
            }else if(createAtField.getType() == Date.class) {
                this.strictInsertFill(metaObject, CREATED_AT, Date.class, new Date());
            }
        }

        //更新时间
        if (metaObject.hasSetter(UPDATED_AT) && null == this.getFieldValByName(UPDATED_AT, metaObject)) {
            Field updateAtField = getFieldByName(metaObject.getOriginalObject(), UPDATED_AT);
            if(updateAtField.getType() == LocalDateTime.class) {
                this.strictInsertFill(metaObject, UPDATED_AT, LocalDateTime.class, now);
            }else if(updateAtField.getType() == Date.class){
                this.strictInsertFill(metaObject, UPDATED_AT, Date.class, new Date());
            }
        }

        //填充guid
        if (metaObject.hasSetter(GUID) && null == this.getFieldValByName(GUID, metaObject)) {
            this.strictInsertFill(metaObject, GUID, String.class, IdUtil.simpleUUID());
        }

        //填充version
        if (metaObject.hasSetter(VERSION) && null == this.getFieldValByName(VERSION, metaObject)) {
            this.strictInsertFill(metaObject, VERSION, Integer.class, 1);
        }

        //填充新增/更新用户名
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        if (metaObject.hasSetter(UPDATED_AT) && null == this.getFieldValByName(UPDATED_AT, metaObject)) {
            Field updateAtField = getFieldByName(metaObject.getOriginalObject(), UPDATED_AT);
            if(updateAtField.getType() == LocalDateTime.class) {
                this.strictInsertFill(metaObject, UPDATED_AT, LocalDateTime.class, LocalDateTime.now());
            }else if(updateAtField.getType() == Date.class){
                this.strictInsertFill(metaObject, UPDATED_AT, Date.class, new Date());
            }
        }

    }

    //根据字段名获取字段
    private Field getFieldByName(Object object, String name) {
        Field[] fields = getAllFields(object);
        for(Field f : fields) {
            if(name.equals(f.getName())) {
                return f;
            }
        }
        return null;
    }

    //获取类的所有字段
    private Field[] getAllFields(Object object){
        Class clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null){
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }
}
