package org.gecko.core.metadata;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

/**
 * 链接信息
 *
 * @author: dengzhi
 * @date: 2018/5/28
 */
@Getter
@AllArgsConstructor
public class ConnectionInfo {
    @NotBlank
    private String url;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
