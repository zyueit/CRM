package eon.service.impl;

import eon.domain.SystemLog;
import eon.mapper.SystemLogMapper;
import eon.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("systemLogService")
public class SystemLogServiceImpl implements ISystemLogService {
    @Autowired
    private SystemLogMapper systemLogMapper;

    @Override
    public void save(SystemLog systemLog) {
        systemLogMapper.save(systemLog);
    }
}
