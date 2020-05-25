package com.dy.controller;

import com.dy.entity.Goods;
import com.dy.packageEntity.Result;
import com.dy.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author: ZT
 * Date: 2020/5/24 13:16
 * Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    /**
     * 查询商品列表
     * @param pageNow
     * @param pageSize
     * @return
     */
    @PostMapping("/queryAllGoods")
    public Result queryAllGoods(@RequestParam Integer pageNow, @RequestParam Integer pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        List<Goods> list = goodsService.queryAllGoods();
        PageInfo<Goods> info = new PageInfo<>(list);
        return new Result(info, "查询成功", 100);
    }

    /**
     * 添加商品
     * @param number
     * @param name
     * @param price
     * @param type
     * @return
     */
    @PostMapping("/addGoods")
    public Result addGoods(@RequestParam Integer number, @RequestParam String name, @RequestParam Integer price, @RequestParam String type) {
        if(goodsService.queryNumber(number) != null) {
            return new Result(null, "订单编号重复", 104);
        }
        int flag = goodsService.addGoods(number, name, price, type);
        if(flag != 1) {
            return new Result(null, "添加失败", 104);
        }
        return new Result(goodsService.queryNumber(number), "添加成功", 100);
    }

    /**
     * 删除商品
     * @param number
     * @return
     */
    @PostMapping("/delNumber")
    public Result delNumber(@RequestParam Integer number) {
        Goods goods = goodsService.queryNumber(number);
        int flag = goodsService.delNumber(number);
        if(flag != 1) {
            return new Result(null, "删除失败", 104);
        }
        return new Result(goods, "删除成功", 100);
    }

    @PostMapping("/updateGoods")
    public Result updateGoods(@RequestParam Integer number, @RequestParam String name, @RequestParam Integer price, @RequestParam String type, @RequestParam Integer id) {
        int flag = goodsService.updateGoods(number, name, price, type, id);
        if(flag != 1) {
            return new Result(null, "修改失败", 104);
        }
        return new Result(goodsService.queryNumber(number), "修改成功", 100);
    }

    @PostMapping("queryId")
    public Result queryId(@RequestParam Integer id) {
        Goods goods = goodsService.queryId(id);
        if(goods == null) {
            return new Result(null, "查询失败", 104);
        }
        return new Result(goods, "查询成功", 100);
    }

    @PostMapping("/nb")
    public Goods nb(@RequestParam Integer number) {
        return goodsService.queryNumber(number);
    }
}
