package com.example.sso.user.service;

import com.example.sso.entry.UserEntry;
import com.example.sso.repository.UserEntryRepository;
import com.example.sso.user.domain.UserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Slf4j
@Component
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        if (StringUtils.isEmpty(userName)) {
            log.warn("用户登陆用户名为空:{}", userName);
            throw new UsernameNotFoundException("用户名不能为空");
        }
         UserEntry userEntry = userEntryRepository.findByUsername(userName);

        if (null == userEntry) {
            log.warn("根据用户名没有查询到对应的用户信息:{}", userName);
        }

        log.info("根据用户名:{}获取用户登陆信息:{}", userName, userEntry);

        UserDetails memberDetails = new UserDetails(userEntry);

        return memberDetails;
    }


    /*public UserDetails getByUsername(String username) {
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(memberList)) {
            return memberList.get(0);
        }
        return null;
    }*/
}
