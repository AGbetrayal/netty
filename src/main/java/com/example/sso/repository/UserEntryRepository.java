package com.example.sso.repository;

import com.ag.core.data.jpa.repository.StringIdJpaRepository;
import com.example.sso.entry.UserEntry;

/**
 * @Description: 描述
 * @Author: zhengaiguo
 * @CreateDate: 2020-09-01 13:31
 */
public interface UserEntryRepository extends StringIdJpaRepository<UserEntry> {
//public interface ReceptionMessageRepository extends BaseJpaRepository<ReceptionMessage, String> {

    UserEntry findByUsername(String username);


}
