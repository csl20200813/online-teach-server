package com.example.educenter.service;

import com.example.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-10-10
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember ucenterMember);

    void register(RegisterVo registerVo);
}
