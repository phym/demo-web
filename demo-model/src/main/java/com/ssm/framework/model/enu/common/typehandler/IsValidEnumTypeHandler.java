package com.ssm.framework.model.enu.common.typehandler;

import org.apache.ibatis.type.EnumOrdinalTypeHandler;

import com.ssm.framework.model.enu.common.IsValidEnum;

public class IsValidEnumTypeHandler extends EnumOrdinalTypeHandler<IsValidEnum> {

    public IsValidEnumTypeHandler(Class<IsValidEnum> type) {
        super(type);
    }

}
