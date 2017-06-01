package com.ssm.framework.model.enu.user.typehandler;

import org.apache.ibatis.type.EnumOrdinalTypeHandler;

import com.ssm.framework.model.enu.user.UserSexEnum;

public class UserSexEnumTypeHandler extends EnumOrdinalTypeHandler<UserSexEnum> {
    
    public UserSexEnumTypeHandler(Class<UserSexEnum> type) {
        super(type);
    }
}
