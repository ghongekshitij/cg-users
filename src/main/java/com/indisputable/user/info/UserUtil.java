package com.indisputable.user.info;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserUtil {

    public static final byte USER_TYPE_SUPER_ADMIN = 0;
    public static final byte USER_TYPE_INDIVIDUAL = 1;
    public static final byte USER_TYPE_BUSINESS = 2;
    public static final byte USER_TYPE_AGENCY = 3;

    public static Map<Integer, String> userTypes;

    static {
        userTypes = new HashMap<>();
        userTypes.put(0, "Super Admin");
        userTypes.put(1, "Individual");
        userTypes.put(2, "Business");
        userTypes.put(3, "Agency");
    }
}
