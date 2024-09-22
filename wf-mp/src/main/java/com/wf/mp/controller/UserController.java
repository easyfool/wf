package com.wf.mp.controller;


import cn.hutool.core.bean.BeanUtil;
import com.wf.mp.domain.dto.UserDTO;
import com.wf.mp.domain.entity.User;
import com.wf.mp.domain.form.UserForm;
import com.wf.mp.domain.query.UserQuery;
import com.wf.mp.domain.vo.UserVO;
import com.wf.mp.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangfeng
 * @since 2024-09-21
 */
@Api("用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @ApiOperation("新增用户接口")
    @PutMapping("/add")
    public void addUser(@RequestBody UserForm userForm) {
        userService.addUser(userForm);
    }

    @ApiOperation("根据id查询用户接口")
    @GetMapping("{id}")
    public UserVO queryUserById(@ApiParam("用户id") @PathVariable("id") Long id) {
        User user = userService.getById(id);
        return BeanUtil.copyProperties(user, UserVO.class);
    }

    @ApiOperation("根据ids批量查询用户接口")
    @GetMapping
    public List<UserVO> queryUserByIds(@ApiParam("用户id集合") @RequestParam("ids") List<Long> ids) {
        List<User> users = userService.listByIds(ids);
        return BeanUtil.copyToList(users, UserVO.class);
    }

    @ApiOperation("根据复杂条件查询用户接口")
    @GetMapping("/list")
    public List<UserVO> queryUsers(@ApiParam("查询条件")  UserQuery userQuery) {
        List<User> users = userService.queryUsers(userQuery);
        return BeanUtil.copyToList(users, UserVO.class);
    }

}
