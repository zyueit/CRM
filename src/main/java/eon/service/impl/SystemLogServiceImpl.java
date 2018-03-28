package eon.service.impl;

import eon.domain.SystemLog;
import eon.mapper.SystemLogMapper;
import eon.page.PageResult;
import eon.query.SystemLogQueryObject;
import eon.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("systemLogService")
public class SystemLogServiceImpl implements ISystemLogService {
    @Autowired
    private SystemLogMapper systemLogMapper;

    @Override
    public void save(SystemLog systemLog) {
        systemLogMapper.save(systemLog);
    }

    @Override
    public PageResult<SystemLog> query(SystemLogQueryObject qo) {
        int count = systemLogMapper.count().intValue();
        if (count == 0) {
            return PageResult.empty();
        }
        return new PageResult<>(count, systemLogMapper.query(qo));
    }
}
