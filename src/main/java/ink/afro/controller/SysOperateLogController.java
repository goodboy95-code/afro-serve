package ink.afro.controller;

import ink.afro.entity.SysOperateLog;
import ink.afro.service.SysOperateLogService;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 操作日志记录表(SysOperateLog)表控制层
 *
 * @author goodboy95-code
 */
@RestController
@RequestMapping("sysOperateLog")
public class SysOperateLogController {
    /**
     * 服务对象
     */
    private final SysOperateLogService sysOperateLogService;

    @Autowired
    public SysOperateLogController(SysOperateLogService sysOperateLogService) {
        this.sysOperateLogService = sysOperateLogService;
    }

    /**
     * 新增数据
     *
     * @param sysOperateLog 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<Boolean> add(@RequestBody SysOperateLog sysOperateLog) {
        return ResponseEntity.ok(this.sysOperateLogService.insert(sysOperateLog));
    }

    /**
     * 删除数据
     *
     * @param operateLogId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public ResponseEntity<Boolean> deleteById(Long operateLogId) {
        return ResponseEntity.ok(this.sysOperateLogService.deleteById(operateLogId));
    }

    /**
     * 删除数据
     *
     * @param operateLogIds 多个主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteBatch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> operateLogIds) {
        return ResponseEntity.ok(this.sysOperateLogService.deleteBatch(operateLogIds));
    }

    /**
     * 清空数据
     *
     * @return 清空是否成功
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Boolean> deleteAll() {
        return ResponseEntity.ok(this.sysOperateLogService.deleteAll());
    }

    /**
     * 编辑数据
     *
     * @param sysOperateLog 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public ResponseEntity<SysOperateLog> edit(@RequestBody SysOperateLog sysOperateLog) {
        return ResponseEntity.ok(this.sysOperateLogService.update(sysOperateLog));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param operateLogId 主键
     * @return 单条数据
     */
    @GetMapping("/queryById")
    public ResponseEntity<SysOperateLog> queryById(Long operateLogId) {
        return ResponseEntity.ok(this.sysOperateLogService.queryById(operateLogId));
    }

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @GetMapping("/queryByPage")
    public ResponseEntity<Page<SysOperateLog>> queryByPage(
            @RequestParam(value = "ipAddress", required = false)
            String ipAddress,
            @RequestParam(value = "title", required = false)
            String title,
            @RequestParam(value = "operator", required = false)
            String operator,
            @RequestParam(value = "operateType", required = false)
            String operateType,
            @RequestParam(value = "status", required = false)
            String status,
            @RequestParam(value = "beginOperateTime", required = false)
            String beginOperateTime,
            @RequestParam(value = "endOperateTime", required = false)
            String endOperateTime,
            @RequestParam("pageNumber")
            int pageNumber,
            @RequestParam("pageSize")
            int pageSize
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        SysOperateLog sysOperateLog = new SysOperateLog();
        sysOperateLog.setIpAddress(ipAddress);
        sysOperateLog.setTitle(title);
        sysOperateLog.setOperator(operator);
        sysOperateLog.setOperatorType(operateType);
        sysOperateLog.setStatus(status);
        if (StringUtils.hasText(beginOperateTime) && StringUtils.hasText(endOperateTime)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            sysOperateLog.setBeginOperateTime(LocalDateTime.parse(beginOperateTime, formatter));
            sysOperateLog.setEndOperateTime(LocalDateTime.parse(endOperateTime, formatter));
        }
        return ResponseEntity.ok(this.sysOperateLogService.queryByPage(sysOperateLog, pageRequest));
    }


    @PostMapping("/export")
    public void export() {
        List<SysOperateLog> sysOperateLogs = sysOperateLogService.queryAll();
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("/Users/lemon/Downloads/excel/operateLog" + LocalDateTime.now() + ".xlsx");
        writer.addHeaderAlias("operateLogId", "日志主键");
        writer.addHeaderAlias("title", "系统模块");
        writer.addHeaderAlias("operateType", "操作类型");
        writer.addHeaderAlias("methodName", "方法名称");
        writer.addHeaderAlias("requestMethod", "请求方式");
        writer.addHeaderAlias("operatorType", "操作类别");
        writer.addHeaderAlias("operator", "操作人");
        writer.addHeaderAlias("requestUrl", "请求URL");
        writer.addHeaderAlias("ipAddress", "主机地址");
        writer.addHeaderAlias("operateLocation", "操作地点");
        writer.addHeaderAlias("requestParam", "请求参数");
        //writer.addHeaderAlias("jsonResult", "返回参数");
        writer.addHeaderAlias("status", "操作状态");
        writer.addHeaderAlias("errorMsg", "错误消息");
        writer.addHeaderAlias("operateTime", "操作时间");
        writer.addHeaderAlias("costTime", "花费时间");
        writer.setOnlyAlias(true);
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(sysOperateLogs, true);
        // 关闭writer，释放内存
        writer.close();
    }
}

