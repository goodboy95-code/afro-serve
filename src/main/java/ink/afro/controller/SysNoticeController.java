package ink.afro.controller;

import ink.afro.entity.SysNotice;
import ink.afro.service.SysNoticeService;
import ink.afro.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 通知公告表(SysNotice)表控制层
 *
 * @author goodboy95-code
 */
@RestController
@RequestMapping("sysNotice")
public class SysNoticeController {
    /**
     * 服务对象
     */
    private final SysNoticeService sysNoticeService;

    @Autowired
    public SysNoticeController(SysNoticeService sysNoticeService) {
        this.sysNoticeService = sysNoticeService;
    }

    /**
     * 新增数据
     *
     * @param sysNotice 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<Boolean> add(@RequestBody SysNotice sysNotice) {
        SysNotice sysNoticeData = new SysNotice();
        sysNoticeData.setNoticeTitle(sysNotice.getNoticeTitle());
        sysNoticeData.setNoticeContent(sysNotice.getNoticeContent());
        sysNoticeData.setNoticeType(sysNotice.getNoticeType());
        sysNoticeData.setStatus(sysNotice.getStatus());
        sysNoticeData.setCreateBy(SecurityUtils.getLoginUser().getUsername());
        sysNoticeData.setCreateTime(LocalDateTime.now());
        sysNoticeData.setUpdateBy(SecurityUtils.getLoginUser().getUsername());
        sysNoticeData.setUpdateTime(LocalDateTime.now());
        sysNoticeData.setRemark(sysNotice.getRemark());
        return ResponseEntity.ok(this.sysNoticeService.insert(sysNoticeData));
    }

    /**
     * 删除数据
     *
     * @param noticeId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public ResponseEntity<Boolean> deleteById(Integer noticeId) {
        return ResponseEntity.ok(this.sysNoticeService.deleteById(noticeId));
    }

    /**
     * 编辑数据
     *
     * @param sysNotice 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public ResponseEntity<SysNotice> edit(@RequestBody SysNotice sysNotice) {
        return ResponseEntity.ok(this.sysNoticeService.update(sysNotice));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param noticeId 主键
     * @return 单条数据
     */
    @GetMapping("/queryById")
    public ResponseEntity<SysNotice> queryById(Integer noticeId) {
        return ResponseEntity.ok(this.sysNoticeService.queryById(noticeId));
    }

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @GetMapping("/queryByPage")
    public ResponseEntity<Page<SysNotice>> queryByPage(
            @RequestParam(value = "noticeTitle", required = false)
            String noticeTitle,
            @RequestParam(value = "noticeType", required = false)
            String noticeType,
            @RequestParam(value = "status", required = false)
            String status,
            @RequestParam(value = "createBy", required = false)
            String createBy,
            @RequestParam("pageNumber")
            int pageNumber,
            @RequestParam("pageSize")
            int pageSize
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        SysNotice sysNotice = new SysNotice();
        sysNotice.setNoticeTitle(noticeTitle);
        sysNotice.setNoticeType(noticeType);
        sysNotice.setStatus(status);
        sysNotice.setCreateBy(createBy);
        return ResponseEntity.ok(this.sysNoticeService.queryByPage(sysNotice, pageRequest));
    }
}

