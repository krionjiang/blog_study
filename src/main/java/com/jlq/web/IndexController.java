package com.jlq.web;

import com.github.pagehelper.PageInfo;
import com.jlq.pojo.TbBlog;
import com.jlq.pojo.TbTag;
import com.jlq.pojo.TbType;
import com.jlq.service.BlogService;
import com.jlq.service.CommentService;
import com.jlq.service.TagService;
import com.jlq.service.TypeServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author ：jlq
 * @date ：Created in 2020/11/28 9:35
 */

@Controller
public class IndexController {
    private final Integer DEFAULT_LISTRECOMMEND = 6;
    private final Integer DEFAULT_LISTTYPE = 6;
    private final Integer DEFAULT_LISTTAG = 10;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeServcie typeServcie;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @RequestMapping("/")
    public String index(@RequestParam(defaultValue = "1") int pageNum,
                        @RequestParam(defaultValue = "3") int pageSize,
                        @RequestParam(defaultValue = "update_time DESC") String orderBy, Model model) {
        model.addAttribute("page", blogService.findAllByPage(pageNum,pageSize,orderBy));
        model.addAttribute("types", typeServcie.listTop(DEFAULT_LISTTYPE));
        model.addAttribute("tags", tagService.listTop(DEFAULT_LISTTAG));
        model.addAttribute("recommendBlogs", blogService.findListByRecommend(DEFAULT_LISTRECOMMEND));
        return "index";
    }

    /**
     * 根据博客Id查看博客详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id") Long id, Model model) {
        model.addAttribute("blog", this.blogService.findBlogByIDConvertContent(id));
        model.addAttribute("comments", this.commentService.findByBlogID(id));
        return "blog";
    }

    @RequestMapping("/types")
    public String types() {
        return "types";
    }

    /**
     * 分类首页接口
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @param model
     * @return
     */
    @GetMapping("/type.html")
    public String typeIndex(@RequestParam(defaultValue = "1") int pageNum,
                            @RequestParam(defaultValue = "3") int pageSize,
                            @RequestParam(defaultValue = "update_time DESC") String orderBy, Model model) {
        setTypeModel(model, null, pageNum,pageSize,orderBy);
        return "types";
    }

    /**
     * 根据分类id查询博客列表接口
     * @param id
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @param model
     * @return
     */
    @GetMapping("/type/{id}")
    public String typeByID(@PathVariable Long id,@RequestParam(defaultValue = "1") int pageNum,
                            @RequestParam(defaultValue = "3") int pageSize,
                            @RequestParam(defaultValue = "update_time DESC") String orderBy, Model model) {
        setTypeModel(model, id, pageNum,pageSize,orderBy);
        return "types";
    }

    /**
     * 标签首页接口
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @param model
     * @return
     */
    @GetMapping("/tags.html")
    public String tagsIndex(@RequestParam(defaultValue = "1") int pageNum,
                            @RequestParam(defaultValue = "3") int pageSize,
                            @RequestParam(defaultValue = "update_time DESC") String orderBy, Model model) {
        setTagModel(model, null, pageNum,pageSize,orderBy);
        return "tags";
    }

    /**
     * 根据标签id获取博客列表接口
     * @param id
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @param model
     * @return
     */
    @GetMapping("/tags/{id}")
    public String tagsByID(@PathVariable Long id,@RequestParam(defaultValue = "1") int pageNum,
                            @RequestParam(defaultValue = "3") int pageSize,
                            @RequestParam(defaultValue = "update_time DESC") String orderBy, Model model) {
        setTagModel(model, id, pageNum,pageSize,orderBy);
        return "tags";
    }
    

    private void setTypeModel(Model model, Long index,
                              @RequestParam(defaultValue = "1") int pageNum,
                              @RequestParam(defaultValue = "3") int pageSize,
                              @RequestParam(defaultValue = "update_time DESC") String orderBy) {
        List<TbType> typeList = this.typeServcie.selectTypesAllPro();
        model.addAttribute("typeList", typeList);
        TbBlog tbBlog = new TbBlog();
        //todo 清空数据库后从一开始
        if(tbBlog.getRecommend()==null){
            tbBlog.setRecommend(false);
        }
        tbBlog.setTypeId(index == null ? 1 : index);
        PageInfo<TbBlog> blogListByPage = this.blogService.findBlogListByPage(pageNum,pageSize,orderBy,tbBlog);
        model.addAttribute("type", index == null ? typeList.get(0) : this.typeServcie.findByIDPro(index));
        model.addAttribute("page", blogListByPage);
    }

    private void setTagModel(Model model, Long index, 
                             @RequestParam(defaultValue = "1") int pageNum,
                             @RequestParam(defaultValue = "3") int pageSize,
                             @RequestParam(defaultValue = "update_time DESC") String orderBy) {
        List<TbTag> tags = this.tagService.selectTagsAllPro();
        //todo 清空数据库后从一开始
        model.addAttribute("tags", tags);
        model.addAttribute("tag", index == null ? tags.get(0) : this.tagService.findByIDPro(index));
        model.addAttribute("page", this.blogService.findBlogListByTagId(index == null ? 1 : index, pageNum,pageSize,orderBy));
    }
}


