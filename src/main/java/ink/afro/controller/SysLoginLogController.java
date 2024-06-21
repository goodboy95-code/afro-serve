package ink.afro.controller;

import ink.afro.entity.SysLoginLog;
import ink.afro.service.SysLoginLogService;
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
 * 系统访问记录(SysLoginLog)表控制层
 *
 * @author goodboy95-code
 */
@RestController
@RequestMapping("sysLoginLog")
public class SysLoginLogController {
    /**
     * 服务对象
     */
    private final SysLoginLogService sysLoginLogService;

    @Autowired
    public SysLoginLogController(SysLoginLogService sysLoginLogService) {
        this.sysLoginLogService = sysLoginLogService;
    }

    /**
     * 新增数据
     *
     * @param sysLoginLog 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<Boolean> add(@RequestBody SysLoginLog sysLoginLog) {
        return ResponseEntity.ok(this.sysLoginLogService.insert(sysLoginLog));
    }

    /**
     * 删除数据
     *
     * @param loginLogId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public ResponseEntity<Boolean> deleteById(Long loginLogId) {
        return ResponseEntity.ok(this.sysLoginLogService.deleteById(loginLogId));
    }

    /**
     * 删除数据
     *
     * @param loginLogIds 多个主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteBatch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> loginLogIds) {
        return ResponseEntity.ok(this.sysLoginLogService.deleteBatch(loginLogIds));
    }

    /**
     * 删除数据
     *
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Boolean> deleteAll() {
        return ResponseEntity.ok(this.sysLoginLogService.deleteAll());
    }

    /**
     * 编辑数据
     *
     * @param sysLoginLog 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public ResponseEntity<SysLoginLog> edit(@RequestBody SysLoginLog sysLoginLog) {
        return ResponseEntity.ok(this.sysLoginLogService.update(sysLoginLog));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param loginLogId 主键
     * @return 单条数据
     */
    @GetMapping("/queryById")
    public ResponseEntity<SysLoginLog> queryById(Long loginLogId) {
        return ResponseEntity.ok(this.sysLoginLogService.queryById(loginLogId));
    }

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @GetMapping("/queryByPage")
    public ResponseEntity<Page<SysLoginLog>> queryByPage(
            @RequestParam(value = "ipAddress", required = false)
            String ipAddress,
            @RequestParam(value = "userName", required = false)
            String userName,
            @RequestParam(value = "status", required = false)
            String status,
            @RequestParam(value = "beginLoginLogTime", required = false)
            String beginLoginLogTime,
            @RequestParam(value = "endLoginLogTime", required = false)
            String endLoginLogTime,
            @RequestParam("pageNumber")
            int pageNumber,
            @RequestParam("pageSize")
            int pageSize
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setIpAddress(ipAddress);
        sysLoginLog.setUserName(userName);
        sysLoginLog.setStatus(status);
        if (StringUtils.hasText(beginLoginLogTime) && StringUtils.hasText(endLoginLogTime)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            sysLoginLog.setBeginLoginLogTime(LocalDateTime.parse(beginLoginLogTime, formatter));
            sysLoginLog.setEndLoginLogTime(LocalDateTime.parse(endLoginLogTime, formatter));
        }
        return ResponseEntity.ok(this.sysLoginLogService.queryByPage(sysLoginLog, pageRequest));
    }

    @PostMapping("/export")
    public void export() {
        List<SysLoginLog> sysLoginLogs = sysLoginLogService.queryAll();
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("/Users/lemon/Downloads/excel/loginLog" + LocalDateTime.now() + ".xlsx");
        writer.addHeaderAlias("loginLogId", "访问ID");
        writer.addHeaderAlias("userName", "用户名");
        writer.addHeaderAlias("ipAddress", "IP地址");
        writer.addHeaderAlias("loginLocation", "登录地点");
        writer.addHeaderAlias("browser", "浏览器");
        writer.addHeaderAlias("os", "操作系统");
        writer.addHeaderAlias("status", "登录状态");
        writer.addHeaderAlias("msg", "提示消息");
        writer.addHeaderAlias("loginTime", "登录时间");
        // 设置只能选择第一列
        writer.setOnlyAlias(true);
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(sysLoginLogs, true);
        // 关闭writer，释放内存
        writer.close();
    }
}

