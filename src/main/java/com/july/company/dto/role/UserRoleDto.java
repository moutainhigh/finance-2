package com.july.company.dto.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户的角色信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-05 15:05
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoleDto implements Serializable {

    private static final long serialVersionUID = -7612256073083121205L;

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 角色id
     */
    private Long roleId;

}
