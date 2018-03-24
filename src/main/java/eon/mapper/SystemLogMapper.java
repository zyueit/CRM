package eon.mapper;

import eon.domain.SystemLog;
import eon.query.SystemLogQueryObject;

import java.util.List;

public interface SystemLogMapper {
    void save(SystemLog systemLog);

    List<SystemLog> query(SystemLogQueryObject qo);

    Long count();
}
