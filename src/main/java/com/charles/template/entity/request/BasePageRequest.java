package com.charles.template.entity.request;

import lombok.Data;

/**
 * @author charles tao
 */
@Data
public class BasePageRequest {
    private int page = 0;
    private int pageSize = 20;
}
