package sin.nettyrpc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * user: cxshun@gmail.com
 * date: 2021/11/16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RpcCallResp {

    /**
     * response code, {@link MessageCode}
     */
    private int code;
    /**
     * message {@link MessageCode}
     */
    private String msg;
    /**
     * return object to the caller
     */
    private Object obj;

}
