package com.jlq.web.admin;

import com.github.pagehelper.PageInfo;
import com.jlq.pojo.TbTag;
import com.jlq.service.TagService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author ：jlq
 * @date ：Created in 2020/11/25 11:11
 */
@Controller
@RequestMapping("admin/tags")
public class AdminTagController {

    @Autowired
    private TagService tagService;

    /**
     * 标签管理分页接口
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @param model
     * @return
     */
    @RequestMapping(value = {"list","/search"})
    public String typesList(String searchContent,
                            @RequestParam(defaultValue = "1") int pageNum,
                            @RequestParam(defaultValue = "3") int pageSize,
                            @RequestParam(defaultValue = "id ASC") String orderBy, Model model) {

        if (StringUtils.isBlank(searchContent)) {
            PageInfo<TbTag> tags = this.tagService.tagList(pageNum, pageSize, orderBy);
            model.addAttribute("tags", tags);
            return "admin/tags";
        } else {
            PageInfo<TbTag> tags = this.tagService.findTypesLike(searchContent, pageNum, pageSize, orderBy);
            model.addAttribute("tags", tags);
            model.addAttribute("searchContent", searchContent);
            return "admin/tags";
        }
    }

    /**
     * 根据id删除标签
     * @param id
     * @return
     */
    @GetMapping("delete/{id}")
    public String deleteTag(@PathVariable("id") Long id) {
        this.tagService.deleteByID(id);
        return "redirect:../list";
    }

    /**
     * 编辑分类
     * @param id
     * @param model
     * @return
     */
    @GetMapping("edit/{id}")
    public String editTag(@PathVariable("id") Long id, Model model) {
        TbTag tag = this.tagService.findByID(id);
        model.addAttribute("tag", tag);
        return "admin/tags-input";
    }

    /**
     * 添加标签接口
     * @param tag
     * @param attributes
     * @return
     */
    @PostMapping("/add")
    public String addTag(TbTag tag, RedirectAttributes attributes) {
        int t = this.tagService.save(tag);
        if (t == 0)
            attributes.addFlashAttribute("message", "添加失败");
        else
            attributes.addFlashAttribute("message", "添加成功");
        return "redirect:list";
    }

    /**
     * 跳转到添加标签页面
     * @param model
     * @return
     */
    @GetMapping("input")
    public String tagInput(Model model) {
        model.addAttribute("tag", new TbTag());
        return "admin/tags-input";
    }

//    /**
//     * 标签搜索接口
//     * @param searchContent
//     * @param pageNum
//     * @param pageSize
//     * @param orderBy
//     * @param model
//     * @return
//     */
//    @PostMapping("/search")
//    public String searchTag(@RequestParam String searchContent,
//                            @RequestParam(defaultValue = "1") int pageNum,
//                            @RequestParam(defaultValue = "3") int pageSize,
//                            @RequestParam(defaultValue = "id ASC") String orderBy, Model model) {
//        if (StringUtils.isBlank(searchContent)) {
//            return "redirect:list";
//        } else {
//            PageInfo<TbTag> tags = this.tagService.findTypesLike(searchContent, pageNum, pageSize, orderBy);
//            model.addAttribute("tags", tags);
//            return "admin/tags";
//        }
//    }
    
}


