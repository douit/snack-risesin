package com.risesin.systemuserservice.service.system;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.core.log.annotation.ApiLog;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.utils.Func;
import com.risesin.system.service.system.RisesinClientService;
import com.risesin.system.wrapper.system.RisesinClientWrapper;
import com.risesin.systemapi.system.entity.RisesinClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * RisesinClient的路由接口服务
 *
 * @author
 * @date 2019-12-22
 */
@RestController
@AllArgsConstructor
@Api(tags = "应用管理")
@RequestMapping("/client")
public class RisesinClientController {

    private RisesinClientService service;


    /**
     * 详情
     */
    @ApiLog("详情查询")
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入risesinClientId")
    public R detail(@RequestParam @ApiParam("主键ID") @NotBlank(message = "id不能为空") String id) {
        return R.data(service.findById(Func.toInt(id)));
    }

    /**
     * 新增
     */
    @ApiLog("新增")
    @PostMapping("/submit")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "新增", notes = "传入risesinClient")
    public R submit(@Valid @RequestBody @ApiParam("risesinClient对象") RisesinClient value) {
        service.add(value);
        return R.success(ResultCode.SUCCESS);
    }

    /**
     * 修改
     */
    @ApiLog("修改")
    @PostMapping("/update")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "修改", notes = "传入risesinClient")
    public R update(@Valid @RequestBody @ApiParam("risesinClient对象") RisesinClient value) {
        service.update(value);
        return R.success(ResultCode.SUCCESS);
    }

    @ApiLog("删除")
    @PostMapping("/remove")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(service.deleteLogic(Func.toIntList(ids), SqlConstant.UPDATE));
    }

    /**
     * 修改状态
     */
    @ApiLog("修改状态")
    @ApiOperationSupport(order = 5)
    @PostMapping(value = "/updateStatusById")
    @ApiOperation(value = "更新状态", notes = "传入id和状态")
    public R updateStatusById(@RequestBody RisesinClient value) {
        service.updateStatus(value.getId(), value.getStatus());
        return R.success(ResultCode.SUCCESS);
    }

    /**
     * 分页+多条件查询
     *
     * @param params 查询条件封装
     * @param query  查询对象
     * @return 分页结果
     */
    @ApiLog("分页+ 多条件查询")
    @ApiOperationSupport(order = 6)
    @PostMapping(value = "/findPageSearch")
    @ApiOperation(value = "分页查询", notes = "根据条件分页查询")
    public R<PageResult> findSearch(@ApiParam(value = "参数集合") @RequestBody Map<String, Object> params,
                                    @ApiParam(value = "分页码") @ApiIgnore @RequestBody Query query) {
        Page<RisesinClient> pages = service.findSearch(params, query);
        return R.data(RisesinClientWrapper.build().pageVO(pages));
    }

}