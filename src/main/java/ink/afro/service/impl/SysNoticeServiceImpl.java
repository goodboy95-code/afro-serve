package ink.afro.service.impl;

import ink.afro.entity.SysNotice;
import ink.afro.mapper.SysNoticeMapper;
import ink.afro.service.SysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * 通知公告表(SysNotice)表服务实现类
 *
 * @author goodboy95-code
 * @since 2024-03-17 20:33:13
 */
@Service("sysNoticeService")
public class SysNoticeServiceImpl implements SysNoticeService {
    private final SysNoticeMapper sysNoticeMapper;

    @Autowired
    public SysNoticeServiceImpl(SysNoticeMapper sysNoticeMapper) {
        this.sysNoticeMapper = sysNoticeMapper;
    }

    @Override
    public boolean insert(SysNotice sysNotice) {
        return this.sysNoticeMapper.insert(sysNotice)>0;
    }
    
    @Override
    public boolean deleteById(Integer noticeId) {
        return this.sysNoticeMapper.deleteById(noticeId) > 0;
    }
    
    @Override
    public SysNotice update(SysNotice sysNotice) {
        this.sysNoticeMapper.update(sysNotice);
        return this.queryById(sysNotice.getNoticeId());
    }

    @Override
    public SysNotice queryById(Integer noticeId) {
        return this.sysNoticeMapper.queryById(noticeId);
    }

    @Override
    public Page<SysNotice> queryByPage(SysNotice sysNotice, PageRequest pageRequest) {
        long total = this.sysNoticeMapper.count(sysNotice);
        return new PageImpl<>(this.sysNoticeMapper.queryAllByLimit(sysNotice, pageRequest), pageRequest, total);
    }
}
