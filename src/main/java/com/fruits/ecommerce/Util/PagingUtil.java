package com.fruits.ecommerce.Util;

import org.springframework.data.domain.PageRequest;

public class PagingUtil {
    private static Integer PAGE = 0;
    private static Integer PAGE_SIZE = 25;

    public static PageRequest getPageRequest(Long page, Long pageSize) {
        if (page != null && page > 0 && page <= Integer.MAX_VALUE) {
            PAGE = Integer.valueOf(page.toString()) - 1;
        }

        if (pageSize != null && pageSize > 0 && pageSize < Integer.MAX_VALUE) {
            PAGE_SIZE = Integer.valueOf(pageSize.toString());
        }

        return PageRequest.of(PAGE, PAGE_SIZE);
    }
}
