package ink.afro.service.impl;

import ink.afro.entity.SysOperateLog;
import ink.afro.mapper.SysOperateLogMapper;
import ink.afro.service.SysOperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作日志记录表(SysOperateLog)表服务实现类
 *
 * @author goodboy95-code
 * @since 2024-03-16 11:54:37
 */
@Service("sysOperateLogService")
public class SysOperateLogServiceImpl implements SysOperateLogService {
    private final SysOperateLogMapper sysOperateLogMapper;

    @Autowired
    public SysOperateLogServiceImpl(SysOperateLogMapper sysOperateLogMapper) {
        this.sysOperateLogMapper = sysOperateLogMapper;
    }

    @Override
    public boolean insert(SysOperateLog sysOperateLog) {
        return this.sysOperateLogMapper.insert(sysOperateLog)> 0;
    }

    @Override
    public boolean deleteById(Long operateLogId) {
        return this.sysOperateLogMapper.deleteById(operateLogId) > 0;
    }

    @Override
    public boolean deleteBatch(List<Long> operateLogId) {
        return sysOperateLogMapper.deleteBatch(operateLogId) > 0;
    }

    @Override
    public boolean deleteAll() {
        return sysOperateLogMapper.deleteAll() > 0;
    }

    @Override
    public SysOperateLog update(SysOperateLog sysOperateLog) {
        this.sysOperateLogMapper.update(sysOperateLog);
        return this.queryById(sysOperateLog.getOperateLogId());
    }

    @Override
    public SysOperateLog queryById(Long operateLogId) {
        return this.sysOperateLogMapper.queryById(operateLogId);
    }

    @Override
    public List<SysOperateLog> queryAll() {
        return sysOperateLogMapper.queryAll();
    }

    @Override
    public Page<SysOperateLog> queryByPage(SysOperateLog sysOperateLog, PageRequest pageRequest) {
        long total = this.sysOperateLogMapper.count(sysOperateLog);
        return new PageImpl<>(this.sysOperateLogMapper.queryAllByLimit(sysOperateLog, pageRequest), pageRequest, total);
    }
}
