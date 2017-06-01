package com.ssm.framework.utils;

/**
 * 
 * 常量定义
 * ConstantDef.
 *
 * @author zax
 */
public interface ConstantDef {

    /**
     * 文件大小定义
     * FILE_SIZE.
     *
     * @author zax
     */
    public static interface FILE_SIZE {
        int ONE_KB = 1024;
        int ONE_MB = 1024 * 1024;
        int FIFTY_MB = 50 * 1024 * 1024;
        int ONE_GB = 1024 * 1024 * 1024;
    }
    
    /**
     * 成功与失败
     * SUCCESS_FAIL.
     *
     * @author zax
     */
    public static interface SUCCESS_FAIL{
        String SUCCESS = "SUCCESS";
        String FAIL = "FAIL";
    }
    
    /**
     * UUID长度
     * UUID_LENGTH.
     *
     * @author zax
     */
    public static interface UUID_LENGTH{
        int SIX_TEEN = 16;
        int THIRTY_TWO = 32;
        int SIXTY_FOUR = 64;
    }
    
    /**
     * 1-12数字
     * INT_VALUE.
     *
     * @author zax
     */
    public static interface INT_VALUE{
        int INT_ZERO = 0;
        int INT_ONE = 1;
        int INT_TWO = 2;
        int INT_THREE = 3;
        int INT_FOUR = 4;
        int INT_FIVE = 5;
        int INT_SIX = 6;
        int INT_SEVEN = 7;
        int INT_EIGHT = 8;
        int INT_NINE = 9;
        int INT_TEN = 10;
        int INT_ELEVEN = 11;
        int INT_TWELVE = 12;
    }
}
