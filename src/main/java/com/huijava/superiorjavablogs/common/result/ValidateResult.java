/**
 * huijava.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.common.result;

import lombok.Data;

/**
 * @author chenhx
 * @version ValidateResult.java, v 0.1 2018-07-31 下午 10:53
 */
@Data
public class ValidateResult {
    private boolean isSuccess;
    private String msg;
}