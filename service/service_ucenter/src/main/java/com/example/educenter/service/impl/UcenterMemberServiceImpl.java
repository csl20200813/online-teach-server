package com.example.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.commonutils.JwtUtils;
import com.example.commonutils.MD5;
import com.example.educenter.entity.UcenterMember;
import com.example.educenter.entity.vo.RegisterVo;
import com.example.educenter.mapper.UcenterMemberMapper;
import com.example.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-10-10
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(UcenterMember ucenterMember) {
        //获取登录手机和密码
        String mobile = ucenterMember.getMobile();
        String password = ucenterMember.getPassword();

        //手机号和密码非空判断
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new GuliException(20001, "手机号或者密码为空，登录失败");
        }

        //判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
        //判断根据手机号查询出的对象是否为空
        if (mobileMember == null) {
            throw new GuliException(20001, "没有该手机号码，登录失败");
        }

        //判断密码（加密过后的密码）
        if (!MD5.encrypt(password).equals(mobileMember.getPassword())) {
            throw new GuliException(20001, "密码不正确，登录失败");
        }

        //判断用户是否禁用
        if (mobileMember.getIsDisabled()) {
            throw new GuliException(20001, "用户禁用了，登录失败");
        }

        //登陆成功
        //使用jwt生成token字符串
        String token = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());

        return token;
    }

    /**
     * 注册
     *
     * @param registerVo
     */
    @Override
    public void register(RegisterVo registerVo) {
        //获取注册的数据
        String code = registerVo.getCode(); //验证码
        String mobile = registerVo.getMobile(); //手机号
        String nickname = registerVo.getNickname(); //昵称
        String password = registerVo.getPassword(); //密码

        //非空判断
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password)) {
            throw new GuliException(20001, "内容有空的，注册失败");
        }

        //从redis中获取验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (!redisCode.equals(code)) {
            throw new GuliException(20001, "验证码错误");
        }

        //判断手机号码是否重复
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new GuliException(20001, "手机号码重复");
        }

        //保存到哦数据库
        UcenterMember member = new UcenterMember();
        member.setIsDeleted(false);
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        member.setGmtModified(new Date());
        baseMapper.insert(member);
    }
}
