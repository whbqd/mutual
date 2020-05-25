package com.dy.mapper;

import com.dy.entity.Goods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: ZT
 * Date: 2020/5/24 13:09
 * Version 1.0
 */
@MapperScan
@Repository
public interface GoodsMapper {
    /**
     * 查询商品列表
     * @return
     */
    @Select("select * from goods")
    List<Goods> queryAllGoods();

    /**
     * 添加商品
     * @param number
     * @param name
     * @param price
     * @param type
     * @return
     */
    @Insert("insert into goods values(id,#{number},#{name},#{price},#{type})")
    Integer addGoods(Integer number, String name, Integer price, String type);

    /**
     * 根据订单编号 查询
     * @param number
     * @return
     */
    @Select("select * from goods where number = #{number}")
    Goods queryNumber(Integer number);

    /**
     * 根据订单编号删除商品
     * @param number
     * @return
     */
    @Delete("delete from goods where number = #{number}")
    Integer delNumber(Integer number);

    /**
     * 更新商品信息
     *
     * @param number
     * @param name
     * @param price
     * @param type
     * @param id
     * @return
     */
    @Update("update goods set number = #{number}, name = #{name}, price = #{price}, type = #{type} where id = #{id}")
    Integer updateGoods(Integer number, String name, Integer price, String type, Integer id);

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    @Select("select * from goods where id = #{id}")
    Goods queryId(Integer id);
}
