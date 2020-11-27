package com.jlq.web.admin;

import com.github.pagehelper.PageInfo;
import com.jlq.pojo.TbBlog;
import com.jlq.pojo.TbTag;
import com.jlq.pojo.TbType;
import com.jlq.service.BlogService;
import com.jlq.service.TagService;
import com.jlq.service.TypeServcie;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author ：jlq
 * @date ：Created in 2020/11/26 11:08
 */
@Controller
@RequestMapping("admin/blogs")
public class AdminBlogController {

    private final String DEFAULT_FLAG = "原创";
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeServcie typeServcie;

    @Autowired
    private TagService tagService;

    /**
     * 查询所有博客
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @param model
     * @return
     */
    @RequestMapping(value = {"list","search"})
    public String blogList(TbBlog tbBlog,
                           @RequestParam(defaultValue = "1") int pageNum,
                           @RequestParam(defaultValue = "3") int pageSize,
                           @RequestParam(defaultValue = "update_time DESC") String orderBy, Model model) {
        List<TbType> typeList = typeServcie.finAll();
        if(tbBlog.getRecommend()==null){
            tbBlog.setRecommend(false);
        }
        if((tbBlog.getTitle()==null||tbBlog.getTitle()=="")
        &&tbBlog.getTypeId()==null&&!tbBlog.getRecommend()){
            PageInfo<TbBlog> pageInfo = this.blogService.findAllByPage(pageNum,pageSize,orderBy);
            model.addAttribute("page", pageInfo);
        }else {
//            TbBlog tbBlog1 = new TbBlog();
//            tbBlog1.setTitle(searchContent);
            PageInfo<TbBlog> pageInfo = this.blogService.findBlogListByPage(pageNum, pageSize, orderBy, tbBlog);
            model.addAttribute("page", pageInfo);
        }
        
        model.addAttribute("types", typeList);
        model.addAttribute("tbBlog",tbBlog);
        model.addAttribute("blog", new TbBlog());
        return "admin/blogs";
    }

    /**
     * 跳转博客添加页面
     * @param model
     * @return
     */
    @GetMapping("/input")
    public String addInput(Model model) {
        setAttr(model);
        model.addAttribute("blog",new TbBlog());
        return "admin/blog-input";
    }

    /**
     * 添加或者更新
     * @param blog
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/add")
    public String add(TbBlog blog, HttpSession session, RedirectAttributes attributes) {
        if (StringUtils.isBlank(blog.getFlag())) {
            blog.setFlag(DEFAULT_FLAG);
        }
        int t = this.blogService.saveOrUpdate(blog, session);
        attributes.addFlashAttribute("message", t != 0 ? "操作成功" : "操作失败");
        return "redirect:list";
    }

    /**
     * 根据博客的Id删除博客
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        this.blogService.deleteById(id);
        return "redirect:../list";
    }

    /**
     * 修改博客
     * @param id
     * @param model
     * @return
     */
    @GetMapping("edit/{id}")
    public String editTag(@PathVariable("id") Long id, Model model) {
        TbBlog blog = this.blogService.findBlogByID(id);
        blog.init();
        setAttr(model);
        model.addAttribute("blog", blog);
        return "admin/blog-input";
    }

    private void setAttr(Model model) {
        List<TbType> typeList = typeServcie.finAll();
        model.addAttribute("types", typeList);
        List<TbTag> tags = tagService.finAll();
        model.addAttribute("tags", tags);
    }
}


