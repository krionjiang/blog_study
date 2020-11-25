package com.jlq.web.admin;

import com.github.pagehelper.PageInfo;
import com.jlq.pojo.TbType;
import com.jlq.service.TypeServcie;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author ：jlq
 * @date ：Created in 2020/11/25 17:22
 */
@Controller
@RequestMapping("/admin/types")
public class AdminTypeController {

    @Autowired
    private TypeServcie typeServcie;

    /**
     * 分类管理分页接口 合并 分类搜索接口
     * @param searchContent
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
            PageInfo<TbType> types = this.typeServcie.typesList(pageNum, pageSize, orderBy);
            model.addAttribute("types", types);
            return "admin/types";
        } else {
            PageInfo<TbType> types = this.typeServcie.findTypesLike(searchContent, pageNum, pageSize, orderBy);
            model.addAttribute("types", types);
            model.addAttribute("searchContent", searchContent);
            return "admin/types";
        }
    }

    /**
     * 跳转到添加分类页面
     * @param model
     * @return
     */
    @GetMapping("input")
    public String typeInput(Model model) {
        model.addAttribute("type", new TbType());
        return "admin/types-input";
    }

    /**
     * 添加分类接口
     * @param type
     * @param attributes
     * @return
     */
    @PostMapping("/add")
    public String addType(TbType type, RedirectAttributes attributes) {
        int t = this.typeServcie.save(type);
        if (t == 0)
            attributes.addFlashAttribute("message", "添加失败");
        else
            attributes.addFlashAttribute("message", "添加成功");
        return "redirect:list";
    }

    /**
     * 根据id删除分类接口
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String deleteType(@PathVariable("id") Long id) {
         this.typeServcie.delete(id);
        return "redirect:../list";
    }

//    @WebLog(description = "分类搜索接口")
//    @PostMapping("/search")
//    public String searchType(@RequestParam String searchContent, @PageableDefault(size = 50,sort = {"id"},direction = Sort.Direction.ASC) Pageable pageable, Model model) {
//        if (StringUtils.isBlank(searchContent)) {
//            return "redirect:list";
//        } else {
//            Page<Type> types = this.typeServcie.findTypesLike(searchContent, pageable);
//            model.addAttribute("types", types);
//            return "admin/types";
//        }
//    }

    /**
     * 编辑分类
     * @param id
     * @param model
     * @return
     */
    @GetMapping("edit/{id}")
    public String editType(@PathVariable("id")Long id,Model model){
        TbType type = this.typeServcie.findByID(id);
        model.addAttribute("type",type);
        return "admin/types-input";
    }
}


