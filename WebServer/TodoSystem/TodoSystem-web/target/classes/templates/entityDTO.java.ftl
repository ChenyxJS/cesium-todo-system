package ${package.Entity}.dto;

import ${package.Entity}.${entity};
import lombok.Data;
/**
 * ${table.comment!} DTO类
 *
 * @author ${author}
 * @since ${date}
*/
@Data
public class ${entity}DTO extends ${entity} {
    /**
     * 验证添加方法参数空值
     *
     * @return Boolean
     */
    public Boolean verifyAddNullAttribute() {
        return true;
    }

    /**
     * 验证修改方法参数空值
     *
     * @return Boolean
     */
    public Boolean verifyUpdateNullAttribute() {
        return true;
    }
}
