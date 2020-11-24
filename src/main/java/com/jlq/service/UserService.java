package com.jlq.service;

import com.jlq.mapper.TbUserMapper;
import com.jlq.pojo.TbUser;
import com.jlq.vo.UserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author ：jlq
 * @date ：Created in 2020/11/24 10:06
 */
@Service
public class UserService {
    @Resource
    private TbUserMapper userMapper;

    public TbUser checkUser(TbUser user){
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        return userMapper.selectOne(user);
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("root"));
        
    }

    @Transactional
    public TbUser changeUser(UserVo userVo) {
        String password = DigestUtils.md5Hex(userVo.getPassword());
        TbUser tbUser = new TbUser();
        tbUser.setUsername(userVo.getUsername());
        tbUser.setPassword(password);
        TbUser user = this.userMapper.selectOne(tbUser);
        if (user != null) {
            user.setPassword(DigestUtils.md5Hex(userVo.getNewPassword()));
            this.userMapper.updateByPrimaryKeySelective(user);
//            this.userRepository.saveAndFlush(user);
            return user;
        }
        return user;
    }

}


