package com.zimomo.lab4.dao.roles;

import com.zimomo.lab4.entity.roles.LoginUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Mapper
public interface LoginUserDao {
    @Select("SELECT * FROM loginuser WHERE employee_id=#{employee_id}")
    LoginUser getSingle(int employee_id);

    @Insert("INSERT INTO loginuser VALUES(#{Username},#{Password},#{Employee_Id},#{Power})")
    void insert(LoginUser loginUser);

    @Update("UPDATE loginuser SET password = #{Password} WHERE employee_id = #{Employee_Id}")
    void update(String password, int employee_id);

    @Delete("DELETE FROM loginuser WHERE employee_id = #{Employee_Id}")
    void delete(int employee_id);

    @Select("SELECT * FROM loginuser WHERE username=#{username}")
    LoginUser findByUsername(String username);
}
