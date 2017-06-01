package com.ssm.framework.model.enu.user.typehandler;

import org.apache.ibatis.type.EnumOrdinalTypeHandler;

import com.ssm.framework.model.enu.user.UserStatusEnum;

public class UserStatusEnumTypeHandler extends EnumOrdinalTypeHandler<UserStatusEnum> {

    public UserStatusEnumTypeHandler(Class<UserStatusEnum> type) {
        super(type);
    }

}
