package com.dy.service;

import com.dy.entity.Goods;
import com.dy.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: ZT
 * Date: 2020/5/24 13:12
 * Version 1.0
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * ��ѯ��Ʒ�б�
     * @return
     */
    public List<Goods> queryAllGoods() {
        return goodsMapper.queryAllGoods();
    }

    /**
     * �����Ʒ
     * @param number
     * @param name
     * @param price
     * @param type
     * @return
     */
    public Integer addGoods(Integer number, String name, Integer price, String type) {
        return goodsMapper.addGoods(number, name, price, type);
    }

    /**
     * ���ݶ���id��ѯ
     * @param number
     * @return
     */
    public Goods queryNumber(Integer number) {
        return goodsMapper.queryNumber(number);
    }

    /**
     * ���ݶ������ɾ����Ʒ
     * @param number
     * @return
     */
    public Integer delNumber(Integer number) {
        return goodsMapper.delNumber(number);
    }

    /**
     * ������Ʒ��Ϣ
     * @param number
     * @param name
     * @param price
     * @param type
     * @param id
     * @return
     */
    public Integer updateGoods(Integer number, String name, Integer price, String type, Integer id) {
        return goodsMapper.updateGoods(number, name, price, type, id);
    }

    /**
     * ����id��ѯ��Ʒ��Ϣ
     * @param id
     * @return
     */
    public Goods queryId(Integer id) {
        return goodsMapper.queryId(id);
    }
}
