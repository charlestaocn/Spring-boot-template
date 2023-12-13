package com.charles.template.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author charles tao
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {

    private Integer code;

    private String msg;

    private T data;

    public static <D> BaseResponse.BaseResponseBuilder<D> success() {
        return new BaseResponse.BaseResponseBuilder<D>().code(200).msg("SUCCESS");
    }

    public static <D> BaseResponse.BaseResponseBuilder<D> fail() {
        return new BaseResponse.BaseResponseBuilder<D>().code(500).msg("FAIL!");
    }

}
