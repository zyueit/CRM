package eon.service;

import eon.domain.SystemLog;
import eon.page.PageResult;
import eon.query.SystemLogQueryObject;

public interface ISystemLogService {
    void save(SystemLog systemLog);

    PageResult<SystemLog> query(SystemLogQueryObject qo);
}
