package springboottools.enums;
/**
 * @author FANWENKUI771
 * @create 2019-06-06
 * @description
 */
public enum Status {
    UNUSED(0, "停用"),
    USE(1, "启用"),
    ;

    Status(int code, String value) {
        this.code = code;
        this.value = value;
    }

    private int code;
    private String value;

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
