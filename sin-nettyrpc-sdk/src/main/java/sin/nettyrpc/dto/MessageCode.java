package sin.nettyrpc.dto;

import lombok.Getter;

import java.text.MessageFormat;

/**
 * user: cxshun@gmail.com
 * date: 2021/11/16
 **/
@Getter
public enum MessageCode {

    SUCCESS(200, "ok"),
    BUSINESS_ERROR(400, "business error");

    private final int code;
    private final String message;

    /**
     * define a unique message code when return to caller, we use MessageFormat to format message
     * so remember to use {0}, number inside bracket
     * @param code      code
     * @param message   message
     * @param params    params
     */
    MessageCode(int code, String message, Object... params) {
        this.code = code;
        this.message = MessageFormat.format(message, params);
    }

}
