package com.bgl.mall.controller.portal;

import com.bgl.mall.common.ServerResponse;
import com.bgl.mall.service.IProductService;
import com.github.pagehelper.PageInfo;
import com.bgl.mall.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by BGL on 2017/7/3.
 */
@RestController
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @PostMapping("detail.do")
    public ServerResponse<ProductDetailVo> getDetail(Integer productId){
        return iProductService.getProductDetail(productId);
    }

    @PostMapping("list.do")
    public ServerResponse<PageInfo> list(@RequestParam(value = "keyWord", required = false)String keyWord,
                                         @RequestParam(value = "categoryId", required = false)Integer categoryId,
                                         @RequestParam(value = "pageNum", defaultValue = "1")int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                         @RequestParam(value = "orderBy", defaultValue = "") String orderBy){
        return iProductService.getProductByKeywordCategory(keyWord, categoryId, pageNum, pageSize, orderBy);
    }
}
