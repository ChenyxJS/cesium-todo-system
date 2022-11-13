package ${package.Entity}.enums;

/**
 * ${table.comment!} 枚举类
 *
 * @author ${author}
 * @since ${date}
*/
public enum  ${entity}Enum {
    /**
     * 用于分辨
     */
    STATUS_PROCESS("已处理", "after"),
    STATUS_NO_PROCESS("未处理", "before"),
    ;

    /**
     * 说明
     */
    private final String meaning;

    /**
     * 代码
     */
    private final String code;

    public String getMeaning() {
        return meaning;
    }

    public String getCode() {
        return code;
    }

    ${entity}Enum(String meaning, String code) {
        this.meaning = meaning;
        this.code = code;
    }
}