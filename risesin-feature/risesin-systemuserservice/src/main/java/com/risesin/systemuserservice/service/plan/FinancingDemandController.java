package com.risesin.systemuserservice.service.plan;

import com.risesin.core.boot.ctrl.RisesinController;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.utils.Func;
import com.risesin.system.service.plan.FinancingDemandService;
import com.risesin.systemapi.plan.entity.FinancingDemand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 融资需求管理
 *
 * @author : honey
 * @date : 2019-11-28 11:38
 **/
//@RestController
//@AllArgsConstructor
////@Api(tags = "融资需求管理")
//@RequestMapping("/finaDemand")
public class FinancingDemandController extends RisesinController {

    private FinancingDemandService service;

    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入ID")
    public R<FinancingDemand> detail(@RequestParam @ApiParam("主键ID") @NotBlank(message = "id不能为空") String id) {
        FinancingDemand detail = service.findById(Func.toInt(id));
        return R.data(detail);
    }

    /**
     * 新增
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "新增", notes = "传入FinancingDemand")
    public R submit(@Valid @RequestBody @ApiParam("FinancingDemand对象") FinancingDemand financingDemand) {
        service.add(financingDemand);
        return R.success("操作成功!");
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "修改", notes = "传入FinancingDemand")
    public R update(@Valid @RequestBody @ApiParam("FinancingDemand对象") FinancingDemand financingDemand) {
        service.update(financingDemand);
        return R.success("操作成功!");
    }


    /**
     * 列表
     */
    /*
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "法人代表姓名", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = " idNumber", value = "法人代表身份证", paramType = "query", dataType = "string")
    })
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "列表", notes = "传入name/idNumber")
    public R<List<EnterpriseAssetInfo>> list(@ApiIgnore @RequestParam Map<String, Object> query) {
        List<EnterpriseAssetInfo> search = infoService.findSearch(query);
        return R.data(search);
    }*/
    @PostMapping("/remove")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除", notes = "传入id")
    public R remove(@ApiParam(value = "主键", required = true) @RequestParam String id) {
        service.deleteById(Func.toInt(id));
        return R.success(ResultCode.SUCCESS);
    }

}
