package com.jlq.service;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jlq.mapper.TbBlogMapper;
import com.jlq.pojo.TbBlog;
import com.jlq.pojo.TbTag;
import com.jlq.pojo.TbType;
import com.jlq.pojo.TbUser;
import com.jlq.util.MarkdownUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author ：jlq
 * @date ：Created in 2020/11/24 16:40
 */
@Service
public class BlogService {
    @Resource
    private TbBlogMapper tbBlogMapper;

    @Autowired
    private TypeServcie typeServcie;

    @Autowired
    private TagService tagService;

    @Transactional
    public int save(TbBlog blog) {
        return this.tbBlogMapper.saveAndFlush(blog);
    }

    @Transactional
    public void deleteById(Long id) {
        this.tbBlogMapper.deleteBlogTags(id);
        this.tbBlogMapper.deleteByPrimaryKey(id);
    }

    public TbBlog findBlogByID(Long id) {
        return this.tbBlogMapper.selectAllNewTbBlogById(id);
    }

    public PageInfo<TbBlog> findAllByPage(int pageNum, int pageSize, String orderBy) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<TbBlog> blogs = this.tbBlogMapper.selectAllNewTbBlog();
        PageInfo<TbBlog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;    
    }

    /**
     * 有内容则搜索，无则全查询
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @param blog
     * @return
     */
    public PageInfo<TbBlog> findBlogListByPage(int pageNum, int pageSize, String orderBy, TbBlog blog) {
        WeekendSqls<TbBlog> sqls = WeekendSqls.custom();
        if(blog.getTitle()!=null && blog.getTitle()!=""){
            sqls.andLike(TbBlog::getTitle,"%"+blog.getTitle()+"%");
        }
        if(blog.getTypeId()!=null){
            sqls.andEqualTo(TbBlog::getTypeId,blog.getTypeId());
        }
        if(blog.getRecommend()){
            sqls.andEqualTo(TbBlog::getRecommend,blog.getRecommend());
        }
        List<TbBlog> blogs = this.tbBlogMapper.selectByExample(Example.builder(TbBlog.class).where(sqls).build());
        PageHelper.startPage(pageNum, pageSize, orderBy);
        if(blogs.isEmpty()){
            PageInfo<TbBlog> pageInfo = new PageInfo<>(blogs);
            return pageInfo;
        }
        List<Long> list = new ArrayList<>();
        for (TbBlog tbBlog : blogs) {
            list.add(tbBlog.getId());
        }
        List<TbBlog> blogs1 = this.tbBlogMapper.selectAllNewTbBlogInId(list);
        PageInfo<TbBlog> pageInfo = new PageInfo<>(blogs1);
        return pageInfo;
    }

    @Transactional
    public int saveOrUpdate(TbBlog blog, HttpSession session) {
        if (blog.getId() == null) {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(blog.getCreateTime());
            blog.setViews(0);
        } else {
            blog.setCreateTime(this.tbBlogMapper.selectByPrimaryKey(blog.getId()).getCreateTime());
            blog.setUpdateTime(new Date());
        }
        blog.setUser((TbUser) session.getAttribute("user"));
        blog.setUserId(blog.getUser().getId());
        TbType byID = typeServcie.findByID(blog.getType().getId());
        blog.setType(byID);
        blog.setTypeId(blog.getType().getId());
        if(StringUtils.isNotBlank(blog.getTagIds())){
            String[] strings = StringUtils.split(blog.getTagIds(), ",");
            long[] longs = Arrays.stream(strings).mapToLong(s -> Long.valueOf(s)).toArray();
            List<Long> list = CollectionUtils.arrayToList(longs);
            List<TbTag> tags = tagService.findByIdS(list);
            blog.setTags(tags);
            if (blog.getId() == null) { 
                this.tbBlogMapper.insertSelective(blog);
//                TbBlog tbBlog = this.tbBlogMapper.selectByPrimaryKey(id);
                }
            for (int i = 0; i < tags.size(); i++) {
                blog.setTempTagId(tags.get(i).getId());
                this.tbBlogMapper.saveAndFlushBlogTags(blog);
            }
        }else {
            this.tbBlogMapper.deleteBlogTags(blog.getId());
        }
        return this.tbBlogMapper.saveAndFlush(blog);
    }

    public List<TbBlog> findListByRecommend(Integer size) {
//        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        return this.tbBlogMapper.findListByRecommend(size);
    }

    /**
     * 根据id查询博客内容并转换content属性
     *
     * @param id
     * @return
     */
    public TbBlog findBlogByIDConvertContent(Long id) {
        TbBlog blog = this.tbBlogMapper.selectAllNewTbBlogById(id);
        TbBlog b = new TbBlog();
        BeanUtils.copyProperties(blog, b);
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(b.getContent()));
        this.tbBlogMapper.updateViews(id);
        return b;
    }

    /**
     * 根据TagId查找对应的Blog
     * @param id
     * @return
     */
    public PageInfo<TbBlog> findBlogListByTagId(Long id,int pageNum, int pageSize, String orderBy){
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<TbBlog> blogs = this.tbBlogMapper.selectByTagId(id);
        PageInfo<TbBlog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;
    }
    
    
}


