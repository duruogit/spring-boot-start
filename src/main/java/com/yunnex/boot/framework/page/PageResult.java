package com.yunnex.boot.framework.page;

import java.io.Serializable;
import java.util.List;

/**
 * 带数据的分页结果
 * @author yuwenjun
 * @date 2017年12月5日 下午2:33:38
 */
public class PageResult<T> implements Serializable{

    private static final long serialVersionUID = 1L;

    private List<T> list;

    private PageInfo page;

    public static <T> PageResult<T> wrap(PageInfo page, List<T> list) {
        PageResult<T> pageResult = new PageResult<T>();
        pageResult.setPage(page);
        pageResult.setList(list);
        return pageResult;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> result) {
        this.list = result;
    }

    public PageInfo getPage() {
        return page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }



}
