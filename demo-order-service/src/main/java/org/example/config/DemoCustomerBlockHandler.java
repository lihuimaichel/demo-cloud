package org.example.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class DemoCustomerBlockHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        Map<String, Object> backMap = new HashMap<>();
        /**
         * FlowException //限流异常
         * DegradeException //降级异常
         * ParamFlowException //参数限流异常
         * SystemBlockException //系统负载异常
         * AuthorityException //授权异常
         */
        if(e instanceof FlowException) {
            backMap.put("code", -1);
            backMap.put("msg", "限流异常");
        } else if(e instanceof DegradeException) {
            backMap.put("code", -1);
            backMap.put("msg", "降级异常");
        } else if(e instanceof ParamFlowException) {
            backMap.put("code", -1);
            backMap.put("msg", "参数限流异常");
        } else if(e instanceof SystemBlockException) {
            backMap.put("code", -1);
            backMap.put("msg", "系统负载异常");
        } else if(e instanceof AuthorityException) {
            backMap.put("code", -1);
            backMap.put("msg", "授权异常");
        }
        httpServletResponse.setStatus(200);
        httpServletResponse.setHeader("content-Type", "application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(backMap));
    }
}
