package springboottools.enums;

import lombok.Getter;

/**
 * 定义脱敏类型的规则，name为枚举类型描述,before表示前面保留几位，after表示后面保留几位，
 * middle表示显示*号的位数，为0表示根据剩余类容显示。
 * 例如123456，before为2，after为1，middle为0，则最终脱敏后为12***6
 * 如果middle为1，则最终脱敏后显示12*6
 */
@Getter
public enum SensitiveEnum {
    NAME("名字",1,0,2),
    MOBILE("电话",3,4,0),
    ID_CARD("身份证",3,2,0),
    DEPOT_ID_CARD("站务端身份证",2,2,0),
    DEPOT_PASSENGER_ID_CARD("站务端乘客身份证",10,4,0),
    DRIVING_LICENSE("驾驶证",5,2,0),
    TRAVEL_LICENSE("行驶证",5,2,0),
    VIN_NO("车架号",3,1,0),
    ;

    private String name;
    private int before;
    private int after;
    private int middle;

    SensitiveEnum(String name, int before, int after, int middle){
        this.name=name;
        this.before=before;
        this.after=after;
        this.middle=middle;
    }
}
