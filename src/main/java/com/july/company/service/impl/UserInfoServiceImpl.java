package com.july.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.july.company.constant.SystemConstant;
import com.july.company.dto.UserInfoDto;
import com.july.company.dto.login.LoginAuthDto;
import com.july.company.dto.login.UserRegisterDto;
import com.july.company.entity.UserInfo;
import com.july.company.exception.BnException;
import com.july.company.intercepts.TokenHandle;
import com.july.company.mapper.UserInfoMapper;
import com.july.company.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.utils.Md5Utils;
import com.july.company.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * 用户服务
 * @author zengxueqi
 * @since 2020-05-16
 */
@Service
@Slf4j
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOperations;
    @Resource
    private TokenHandle tokenHandle;

    @Override
    public UserInfoDto login(LoginAuthDto loginAuthDto) {
        String mobile = loginAuthDto.getMobile();
        log.trace("用户手机:{},登录系统", loginAuthDto.getMobile());
        //数据库匹配用户
        UserInfo userInfo = this.getUserInfoByMobile(loginAuthDto.getMobile());
        if (userInfo == null) {
            throw BnException.on("用户不存在");
        }
        UserInfo checkUserInfo = this.getUserInfoByMobileAndPassword(mobile, loginAuthDto.getEncryptPassword(userInfo.getPwdSalt()));
        if (checkUserInfo == null) {
            throw BnException.on("用户名或密码错误");
        }
        if (checkUserInfo.getDeleted() == 1) {
            throw BnException.on("账号已被删除");
        }
        String token = UUIDUtils.getReplaceUuid();
        String tokenDes = tokenHandle.encryptAuth(token, checkUserInfo.getId());

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .userName(checkUserInfo.getUsername())
                .avatar(checkUserInfo.getAvatar())
                .mobile(checkUserInfo.getMobile())
                .sex(checkUserInfo.getSex())
                .id(checkUserInfo.getId())
                .token(tokenDes)
                .build();
        valueOperations.set(SystemConstant.CACHE_NAME + token, userInfoDto.getId(), SystemConstant.EXPIRE_LOGIN, TimeUnit.MINUTES);
        return userInfoDto;
    }

    /**
     * code加入缓存
     * @param flag
     * @param key
     * @param code
     * @return void
     * @author zengxueqi
     * @since 2020/5/16
     */
    @Override
    public void codeCache(String flag, String key, String code) {
        Object o = valueOperations.get(flag + key);
        if (o != null) {
            throw BnException.on("请不要重复生成验证码！");
        }
        valueOperations.set(flag + key, code, Duration.ofMinutes(15));
    }

    /**
     * 用户注册
     * @param userRegisterDto
     * @return void
     * @author zengxueqi
     * @since 2020/5/16
     */
    @Override
    public void userRegister(UserRegisterDto userRegisterDto) {
        UserInfo userInfoExist = getUserInfoByMobile(userRegisterDto.getMobile());
        BnException.of(userInfoExist != null,"当前手机号以前存在，请直接登录！");

        String passwordSalt = Md5Utils.getRandomString(32);
        UserInfo userInfo = UserInfo.builder()
                .mobile(userRegisterDto.getMobile())
                .password(Md5Utils.generatePassword(userRegisterDto.getPassword(), passwordSalt))
                .pwdSalt(passwordSalt)
                .username(userRegisterDto.getUserName())
                .build();
        this.save(userInfo);
    }

    /**
     * 获取用户信息
     * @param userId
     * @return com.july.company.dto.UserInfoDto
     * @author zengxueqi
     * @since 2020/5/16
     */
    @Override
    public UserInfoDto getUserInfo(Long userId) {
        UserInfo userInfo = this.getById(userId);
        UserInfoDto userInfoDto = new UserInfoDto();
        if (userInfo != null) {
            userInfoDto.setId(userInfo.getId());
            userInfoDto.setAvatar(userInfo.getAvatar());
            userInfoDto.setMobile(userInfo.getMobile());
            userInfoDto.setSex(userInfo.getSex());
            userInfoDto.setUserName(userInfo.getUsername());
        }
        return userInfoDto;
    }

    /**
     * 通过手机号获取用户信息
     * @param mobile
     * @return com.july.company.entity.UserInfo
     * @author zengxueqi
     * @since 2020/5/16
     */
    public UserInfo getUserInfoByMobile(String mobile) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile)
                .eq("deleted", SystemConstant.SYS_TRUE);
        return this.getOne(queryWrapper);
    }

    /**
     * 通过手机号和密码获取用户信息
     * @param mobile
     * @param password
     * @return com.july.company.entity.UserInfo
     * @author zengxueqi
     * @since 2020/5/16
     */
    public UserInfo getUserInfoByMobileAndPassword(String mobile, String password) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile)
                .eq("password", password)
                .eq("deleted", SystemConstant.SYS_TRUE);
        return this.getOne(queryWrapper);
    }

}
