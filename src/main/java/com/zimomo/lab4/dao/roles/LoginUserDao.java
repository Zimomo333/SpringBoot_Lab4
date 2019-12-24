package com.zimomo.lab4.dao.roles;

import com.zimomo.lab4.entity.roles.LoginUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Mapper
public interface LoginUserDao {
    @Insert("INSERT INTO loginuser VALUES(#{username},123456,#{employee_id},'ROLE_Salesman')")
    void addLoginuser(int employee_id,String username);

    @Update("UPDATE loginuser SET password = #{password} WHERE employee_id = #{employee_id}")
    void changePassword(String password, int employee_id);

    @Delete("DELETE FROM loginuser WHERE employee_id = #{employee_id}")
    void delete(int employee_id);

    @Select("SELECT * FROM loginuser WHERE username=#{username}")
    LoginUser findByUsername(String username);

    @Delete("DELETE FROM loginuser WHERE employee_id=#{employee_id}")
    void delLoginUser(int employee_id);
}
