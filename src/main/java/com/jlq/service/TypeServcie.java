package com.jlq.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jlq.mapper.TbTypeMapper;
import com.jlq.pojo.TbTag;
import com.jlq.pojo.TbType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;

/**
 * @author ：jlq
 * @date ：Created in 2020/11/25 16:43
 */
@Service
public class TypeServcie {
    @Autowired
    private TbTypeMapper tbTypeMapper;

    public PageInfo<TbType> typesList(int pageNum, int pageSize, String orderBy) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<TbType> tbTypes = tbTypeMapper.selectAll();
        PageInfo<TbType> pageInfo = new PageInfo<>(tbTypes);
        return pageInfo;
    }

    @Transactional
    public int save(TbType type) {
        return this.tbTypeMapper.saveAndFlush(type);
    }

    @Transactional
    public void delete(Long id) {
        this.tbTypeMapper.deleteByPrimaryKey(id);
    }

    public PageInfo<TbType> findTypesLike(String searchContent, int pageNum, int pageSize, String orderBy) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        WeekendSqls<TbType> sqls = WeekendSqls.custom();
        sqls.andLike(TbType::getTypeName,"%"+searchContent+"%");
        List<TbType> tbTypes = tbTypeMapper.selectByExample(Example.builder(TbType.class).where(sqls).build());
        PageInfo<TbType> pageInfo = new PageInfo<>(tbTypes);
        return pageInfo;

    }

    public TbType findByID(Long id) {
        return this.tbTypeMapper.selectByPrimaryKey(id);
    }

    public TbType findByIDPro(Long id) {
        return this.tbTypeMapper.findByIDPro(id);
    }

    public List<TbType> finAll() {
        return this.tbTypeMapper.selectAll();
    }
    
    public List<TbType> selectTypesAllPro() {
        return this.tbTypeMapper.selectTypesAllPro();
    }

    public List<TbType> listTop(Integer size) {
//        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
//        Pageable pageable =  PageRequest.of(0,size,sort);
//        return this.typeRepository.listTop(pageable);
        return tbTypeMapper.listTop(size);
    }
}


