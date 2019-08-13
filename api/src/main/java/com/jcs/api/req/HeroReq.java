package com.jcs.api.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @auther xukh
 * @date 2019/8/13 20:34
 */
@Data
public class HeroReq implements Serializable {
    private static final long serialVersionUID = -3830393802178004128L;

    @NotBlank(message = "uuid 不能为空")
    private String uuid;
}
