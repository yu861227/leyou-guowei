package com.leyou.common.exception;

import com.leyou.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author guo
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LyException extends RuntimeException{
    private ExceptionEnum exceptionEnum;
}
