package com.example.demo.controller;

import com.example.demo.entity.Blog;
import com.jfinal.plugin.activerecord.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2020/4/10.
 */
@RestController
public class DemoController {

    private Blog dao = new Blog().dao();

    @RequestMapping("test")
    public Page<Blog> test() {
        return dao.paginate(1, 10, "select *", "from blog order by id asc");
    }

    @RequestMapping("test/{id}")
    public Blog test2(@PathVariable Integer id) {
        return dao.findById(id);
    }


    @RequestMapping("like/{title}")
    public Blog test3(@PathVariable String title) {
        return dao.findFirst("select * from blog where title like ?", "%" + title + "%");
    }
}
