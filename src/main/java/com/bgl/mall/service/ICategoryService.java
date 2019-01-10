package com.bgl.mall.service;

import com.bgl.mall.pojo.Category;
import com.bgl.mall.common.ServerResponse;

import java.util.List;

/**
 * Created by BGL on 2017/6/10.
 */
public interface ICategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
}
