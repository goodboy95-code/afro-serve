package ink.afro.service.impl;

import ink.afro.entity.SysLoginLog;
import ink.afro.mapper.SysLoginLogMapper;
import ink.afro.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统访问记录(SysLoginLog)表服务实现类
 *
 * @author goodboy95-code
 * @since 2024-03-18 19:11:53
 */
@Service("sysLoginLogService")
public class SysLoginLogServiceImpl implements SysLoginLogService {
    private final SysLoginLogMapper sysLoginLogMapper;

    @Autowired
    public SysLoginLogServiceImpl(SysLoginLogMapper sysLoginLogMapper) {
        this.sysLoginLogMapper = sysLoginLogMapper;
    }

    @Override
    public boolean insert(SysLoginLog sysLoginLog) {
        return this.sysLoginLogMapper.insert(sysLoginLog) > 0;
    }

    @Override
    public boolean deleteById(Long loginLogId) {
        return this.sysLoginLogMapper.deleteById(loginLogId) > 0;
    }

    @Override
    public boolean deleteBatch(List<Long> loginLogIds) {
        return sysLoginLogMapper.deleteBatch(loginLogIds) > 0;
    }

    @Override
    public boolean deleteAll() {
        return sysLoginLogMapper.deleteAll() > 0;
    }

    @Override
    public SysLoginLog update(SysLoginLog sysLoginLog) {
        this.sysLoginLogMapper.update(sysLoginLog);
        return this.queryById(sysLoginLog.getLoginLogId());
    }

    @Override
    public SysLoginLog queryById(Long loginLogId) {
        return this.sysLoginLogMapper.queryById(loginLogId);
    }

    @Override
    public List<SysLoginLog> queryAll() {
        return sysLoginLogMapper.queryAll();
    }

    @Override
    public Page<SysLoginLog> queryByPage(SysLoginLog sysLoginLog, PageRequest pageRequest) {
        long total = this.sysLoginLogMapper.count(sysLoginLog);
        return new PageImpl<>(this.sysLoginLogMapper.queryAllByLimit(sysLoginLog, pageRequest), pageRequest, total);
    }
}
