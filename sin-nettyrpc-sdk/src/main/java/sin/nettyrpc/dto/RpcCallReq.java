package sin.nettyrpc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * rpc call dto, we'll decode bytes into this class
 * user: cxshun@gmail.com
 * date: 2021/11/16
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RpcCallReq {

    /**
     * class name that current request attach
     */
    private String className;
    /**
     * the classing method name
     */
    private String methodName;
    /**
     * current request's  params, must in order
     */
    private Object[] params;

}
