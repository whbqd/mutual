package com.dy.mapper;

import com.dy.entity.Goods;
import com.dy.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: ZT
 * Date: 2020/5/24 13:09
 * Version 1.0
 */
@Repository
public interface GoodsMapper {
    /**
     * 查询商品列表
     * @return
     */
    List<Goods> queryAllGoods();

    /**
     * 添加商品
     * @param number
     * @param name
     * @param price
     * @param type
     * @return
     */
    Integer addGoods(Integer number, String name, Integer price, String type);

    /**
     * 根据订单编号 查询
     * @param number
     * @return
     */
    Goods queryNumber(Integer number);

    /**
     * 根据订单编号删除商品
     * @param number
     * @return
     */
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
    Integer updateGoods(Integer number, String name, Integer price, String type, Integer id);

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    Goods queryId(Integer id);
}
