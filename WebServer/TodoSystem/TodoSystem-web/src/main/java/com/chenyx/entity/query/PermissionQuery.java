package com.chenyx.entity.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chenyx.entity.Permission;
import com.chenyx.entity.dto.PermissionDTO;
import com.chenyx.util.ListUtil;
import com.chenyx.util.StringUtil;
import lombok.Data;

import java.util.List;

/**
 * 权限表 查询类
 *
 * @author 空城
 * @since 2021-07-29
*/
@Data
public class PermissionQuery extends QueryParam {
    /**
     * 原类
     */
    private PermissionDTO param;
    private QueryWrapper<Permission> query;
    private UpdateWrapper<Permission> update;
    private String isHaveBlob;

    private String isTree;
    // 是返回全部权限还是只返回一级权限
    private String isAll;
    private String queryType = "C";

    // 角色id，用于查询该角色拥有的权限的
    private Long roleId;


    // 角色的权限id列表
    private List<Long> rolePerIds;

    // likeParam
    // 权限路径
    private String permPcodeIsLike;
    // 权限名称，用于生成，title
    private String permNameIsLike;
    // 权限代码，唯一标识，用于生成path和name
    private String permCodeIsLike;
    // 权限类型，分三种：
    private String permTypeIsLike;
    // 权限映射地址，用于生成映射地址
    private String permUrlIsLike;
    // 权限状态，启用或者停用
    private String permStatusIsLike;
    // 控制菜单显示或者不显示
    private String permShowIsLike;
    // 权限图标，类型为目录时必须有
    private String permIconIsLike;
    // 备注
    private String permRemarkIsLike;
    // 标识该权限是否是登录可用,Y:是
    private String permLoginIsLike;
    // 界面是否自动刷新
    private String permKeppAliveIsLike;
    // 权限管控的地址
    private String permControlUrlIsLike;

    public QueryWrapper<Permission> buildExample() {
        query = new QueryWrapper<>();
        update = new UpdateWrapper<>();
            if(getParam() == null){
                setParam(new PermissionDTO());
            }
        if(ListUtil.isNotEmpty(getRolePerIds())){
            query.in("perm_id",getRolePerIds());
            update.in("perm_id",getRolePerIds());
        }

    //
        if(StringUtil.isNotEmpty(getParam().getPermId())){
            query.eq("perm_id",getParam().getPermId());
            update.eq("perm_id",getParam().getPermId());
        }

    // 父权限id
        if(StringUtil.isNotEmpty(getParam().getPermPid())){
            query.eq("perm_pid",getParam().getPermPid());
            update.eq("perm_pid",getParam().getPermPid());
        }

    // 权限路径
        if(StringUtil.isNotEmpty(getParam().getPermPcode())){
            query.eq("perm_pcode",getParam().getPermPcode());
            update.eq("perm_pcode",getParam().getPermPcode());
        }
        if(StringUtil.isNotEmpty(getPermPcodeIsLike())){
            query.like("perm_pcode",getPermPcodeIsLike());
            update.like("perm_pcode",getPermPcodeIsLike());
        }

    // 权限名称，用于生成，title
        if(StringUtil.isNotEmpty(getParam().getPermName())){
            query.eq("perm_name",getParam().getPermName());
            update.eq("perm_name",getParam().getPermName());
        }
        if(StringUtil.isNotEmpty(getPermNameIsLike())){
            query.like("perm_name",getPermNameIsLike());
            update.like("perm_name",getPermNameIsLike());
        }

    // 权限代码，唯一标识，用于生成path和name
        if(StringUtil.isNotEmpty(getParam().getPermCode())){
            query.eq("perm_code",getParam().getPermCode());
            update.eq("perm_code",getParam().getPermCode());
        }
        if(StringUtil.isNotEmpty(getPermCodeIsLike())){
            query.like("perm_code",getPermCodeIsLike());
            update.like("perm_code",getPermCodeIsLike());
        }

    // 权限类型，分三种：
        if(StringUtil.isNotEmpty(getParam().getPermType())){
            query.eq("perm_type",getParam().getPermType());
            update.eq("perm_type",getParam().getPermType());
        }
        if(StringUtil.isNotEmpty(getPermTypeIsLike())){
            query.like("perm_type",getPermTypeIsLike());
            update.like("perm_type",getPermTypeIsLike());
        }

    // 权限映射地址，用于生成映射地址
        if(StringUtil.isNotEmpty(getParam().getPermUrl())){
            query.eq("perm_url",getParam().getPermUrl());
            update.eq("perm_url",getParam().getPermUrl());
        }
        if(StringUtil.isNotEmpty(getPermUrlIsLike())){
            query.like("perm_url",getPermUrlIsLike());
            update.like("perm_url",getPermUrlIsLike());
        }

    // 权限状态，启用或者停用
        if(StringUtil.isNotEmpty(getParam().getPermStatus())){
            query.eq("perm_status",getParam().getPermStatus());
            update.eq("perm_status",getParam().getPermStatus());
        }
        if(StringUtil.isNotEmpty(getPermStatusIsLike())){
            query.like("perm_status",getPermStatusIsLike());
            update.like("perm_status",getPermStatusIsLike());
        }

    // 控制菜单显示或者不显示
        if(StringUtil.isNotEmpty(getParam().getPermShow())){
            query.eq("perm_show",getParam().getPermShow());
            update.eq("perm_show",getParam().getPermShow());
        }
        if(StringUtil.isNotEmpty(getPermShowIsLike())){
            query.like("perm_show",getPermShowIsLike());
            update.like("perm_show",getPermShowIsLike());
        }

    // 排序
        if(StringUtil.isNotEmpty(getParam().getPermOrder())){
            query.eq("perm_order",getParam().getPermOrder());
            update.eq("perm_order",getParam().getPermOrder());
        }

    // 权限图标，类型为目录时必须有
        if(StringUtil.isNotEmpty(getParam().getPermIcon())){
            query.eq("perm_icon",getParam().getPermIcon());
            update.eq("perm_icon",getParam().getPermIcon());
        }
        if(StringUtil.isNotEmpty(getPermIconIsLike())){
            query.like("perm_icon",getPermIconIsLike());
            update.like("perm_icon",getPermIconIsLike());
        }

    // 备注
        if(StringUtil.isNotEmpty(getParam().getPermRemark())){
            query.eq("perm_remark",getParam().getPermRemark());
            update.eq("perm_remark",getParam().getPermRemark());
        }
        if(StringUtil.isNotEmpty(getPermRemarkIsLike())){
            query.like("perm_remark",getPermRemarkIsLike());
            update.like("perm_remark",getPermRemarkIsLike());
        }

    // 记录创建日期时间
        if(StringUtil.isNotEmpty(getParam().getPermDbCreateDate())){
            query.eq("perm_db_create_date",getParam().getPermDbCreateDate());
            update.eq("perm_db_create_date",getParam().getPermDbCreateDate());
        }

    // 记录最后一次修改日期时间
        if(StringUtil.isNotEmpty(getParam().getPermDbUpdateDate())){
            query.eq("perm_db_update_date",getParam().getPermDbUpdateDate());
            update.eq("perm_db_update_date",getParam().getPermDbUpdateDate());
        }

    // 标识该权限是否是登录可用,Y:是
        if(StringUtil.isNotEmpty(getParam().getPermLogin())){
            query.eq("perm_login",getParam().getPermLogin());
            update.eq("perm_login",getParam().getPermLogin());
        }
        if(StringUtil.isNotEmpty(getPermLoginIsLike())){
            query.like("perm_login",getPermLoginIsLike());
            update.like("perm_login",getPermLoginIsLike());
        }

    // 界面是否自动刷新
        if(StringUtil.isNotEmpty(getParam().getPermKeppAlive())){
            query.eq("perm_kepp_alive",getParam().getPermKeppAlive());
            update.eq("perm_kepp_alive",getParam().getPermKeppAlive());
        }
        if(StringUtil.isNotEmpty(getPermKeppAliveIsLike())){
            query.like("perm_kepp_alive",getPermKeppAliveIsLike());
            update.like("perm_kepp_alive",getPermKeppAliveIsLike());
        }

    // 权限管控的地址
        if(StringUtil.isNotEmpty(getParam().getPermControlUrl())){
            query.eq("perm_control_url",getParam().getPermControlUrl());
            update.eq("perm_control_url",getParam().getPermControlUrl());
        }
        if(StringUtil.isNotEmpty(getPermControlUrlIsLike())){
            query.like("perm_control_url",getPermControlUrlIsLike());
            update.like("perm_control_url",getPermControlUrlIsLike());
        }
// 排序
        if(StringUtil.isNotEmpty(getOrderItem())){
            if(StringUtil.isEmpty(getOrderType())){
                query.orderByAsc(getOrderItem().split(","));
            }else if("asc".equals((getOrderType()))){
                query.orderByAsc(getOrderItem().split(","));
            }else if("desc".equals((getOrderType()))){
                query.orderByDesc(getOrderItem().split(","));
            }
        }
        return query;
    }

    @Override
    public QueryWrapper<Permission> getQueryP() {
        return query;
    }

}
