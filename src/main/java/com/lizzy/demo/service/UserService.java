package com.lizzy.demo.service;

import com.lizzy.demo.entity.UserEntity;
import com.lizzy.demo.req.UserReq;
import com.lizzy.demo.req.UserSaveReq;
import com.lizzy.demo.resp.PageResp;

public interface UserService {

    PageResp<UserEntity> getList(UserReq userReq);

    void Delete(Long id);

    void Create(UserSaveReq req);
}
