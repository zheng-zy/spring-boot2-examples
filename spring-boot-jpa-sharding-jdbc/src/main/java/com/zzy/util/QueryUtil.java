package com.zzy.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2018/12/26.
 */
public class QueryUtil {
    /**
     * 分页
     * @param page 当前页
     * @param pageSize 页面数量
     * @param sortType 排序方式
     * @param sortCol 排序字段
     * @return 分页对象
     */
    public static PageRequest buildPageRequest(int page, int pageSize, String sortType, String sortCol) {
        Sort sort = null;
        if (StringUtils.isNotEmpty(sortCol)){
            if ("desc".equalsIgnoreCase(sortType)) {
                sort = new Sort(Sort.Direction.DESC, sortCol);
            } else {
                sort = new Sort(Sort.Direction.ASC, sortCol);
            }
            return PageRequest.of(page, pageSize, sort);
        }
        return PageRequest.of(page, pageSize);
    }

//    public static <T> Specification<T> buildSpecification(Map<String, Object> searchParams, Class<T> domainClass){
//        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
//        Specification<T> spec = DynamicSpecifications.bySearchFilter(filters.values(), domainClass);
//        return spec;
//    }
}
