package com.risesin.enterprise.feign.dict;

import com.risesin.core.launch.constant.AppConstant;
import com.risesin.core.tool.api.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @AUTHOR Darling
 * @CREATE 2019-11-29
 * @DESCRIPTION 融资类型feign接口类
 * @since 1.0.0
 */
@FeignClient(
        value = AppConstant.APPLICATION_SYSTEM_NAME,
        fallback = FinancingTypeClientFallback.class
)
public interface IFinancingTypeClient {

    String API_PREFIX = "/financingType";

    /**
     * 根据父code查询
     *
     * @param parentCode 父code
     * @return json
     */
    @ApiOperation(value = "根据parentCode查询信息", notes = "传入pcode")
    @GetMapping(API_PREFIX + "/listByPcode/{parentCode}")
    R listByPid(@PathVariable @ApiParam("父code") String parentCode);

    /**
     * 获取融资类型树形结构
     *
     * @return
     */
    @GetMapping(API_PREFIX + "/tree")
    @ApiOperation(value = "融资类型树形结构", notes = "融资类型树形结构")
    R tree(@RequestParam(required = false) Byte status);


}
