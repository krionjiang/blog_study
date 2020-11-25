package com.jlq.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jlq.mapper.TbTagMapper;
import com.jlq.pojo.TbTag;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：jlq
 * @date ：Created in 2020/11/24 16:41
 */
@Service
public class TagService {
    @Resource
    private TbTagMapper tbTagMapper;
    
    
    public PageInfo<TbTag> tagList(int pageNum, int pageSize, String orderBy){
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<TbTag> tbTags = tbTagMapper.selectAll();
        PageInfo<TbTag> pageInfo = new PageInfo<>(tbTags);
        return pageInfo;
    }

    public void deleteByID(Long id) {
        this.tbTagMapper.deleteByPrimaryKey(id);
    }

    public TbTag findByID(Long id) {
        return this.tbTagMapper.selectByPrimaryKey(id);
//        return this.tagRepository.findById(id).orElse(new TbTag());
    }

    public int save(TbTag tag) {
        return this.tbTagMapper.saveAndFlush(tag);
    }

    public PageInfo<TbTag> findTypesLike(String searchContent, int pageNum, int pageSize, String orderBy) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        WeekendSqls<TbTag> sqls = WeekendSqls.custom();
        sqls.andLike(TbTag::getTagName,"%"+searchContent+"%");
        List<TbTag> tbTags = tbTagMapper.selectByExample(Example.builder(TbTag.class).where(sqls).build());
        PageInfo<TbTag> pageInfo = new PageInfo<>(tbTags);
        return pageInfo;
    }

    public List<TbTag> finAll() {
        return this.tbTagMapper.selectAll();
    }

    public List<TbTag> findByIdS(List<Long> longs) {
        WeekendSqls<TbTag> sqls = WeekendSqls.custom();
        sqls.andIn(TbTag::getId,longs);
        List<TbTag> tbTags = tbTagMapper.selectByExample(Example.builder(TbTag.class).where(sqls).build());
        return tbTags;
    }

    public List<TbTag> listTop(Integer size) {
//        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
//        return this.tagRepository.listTop(PageRequest.of(0,size,sort));
        return tbTagMapper.listTop(size);
    }
}


