package ${package.Mapper};

import ${package.Entity}.dto.${entity}DTO;
import ${package.Entity}.${entity};
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
 * ${table.comment!} mapper自定义类
 *
 * @author ${author}
 * @since ${date}
*/
public interface Ext${table.mapperName} extends BaseMapper<${entity}> {

    /**
     * 批量插入
     * @param list  数据
     * @return  int
    */
    int batchAdd(List<${entity}DTO> list);

    /**
     * 加锁查询
     *
     * @param id 主键
     * @return Address
    */
    ${entity} getByIdForUpdate(Long id);

}