package com.dy.mapper;

import com.dy.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;
@MapperScan
@Repository
public interface UserMapper {
    /**
     * 登录
     * @param user
     * @param password
     * @return
     */
    @Select("select * from user where user = #{user} and password = #{password}")
    User login(String user, String password);

    /**
     * 注册
     * @param user
     * @param password
     * @param email
     * @return
     */
    @Insert("insert into user values(id,#{user},#{password},#{email})")
    Integer register(String user, String password, String email);
    /**
     *查询用户名是否重复
     * @param user
     * @return
     */
    @Select("select * from user where user = #{user}")
    User queryByUser(String user);

    /**
     * 判断用户名邮箱是否正确
     * @param user
     * @param email
     * @return
     */
    @Select("select * from user where user = #{user} and email = #{email}")
    User UserIsPwd(String user, String email);

    /**
     * 修改密码
     * @param user
     * @param password
     * @return
     */
    @Update("update user set password = #{password} where user = #{user}")
    Integer updatePwd(String user, String password);

    /**
     * 全查
     * @return
     */
    @Select("select * from user")
    List<User> queryAll();

    /**
     * 删除
     * @param id
     * @return
     */
    @Delete("delete from user where id = #{id}")
    Integer del(Integer id);

    /**
     * 根据id查询User
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    User queryById(Integer id);

    /**
     * 修改User
     * @param user
     * @param password
     * @param email
     * @return
     */
    @Update("update user set user = #{user}, password = #{password}, email = #{email} where id = #{id}")
    Integer updateUser(String user, String password, String email, Integer id);

    /**
     * 根据id 查询user
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    User queryIdByUser(Integer id);
}
