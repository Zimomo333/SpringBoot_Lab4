package com.zimomo.lab4.dao.roles;

import com.zimomo.lab4.entity.roles.WarehouseKeeper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface WarehouseKeeperDao {
    @Select("SELECT * FROM keeper WHERE keeper_id=#{keeper_id}")
    WarehouseKeeper findKeeperById(int keeper_id);
}
