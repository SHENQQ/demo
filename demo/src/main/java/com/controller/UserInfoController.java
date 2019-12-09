package com.controller;

import com.dal.entity.UserRole;
import com.response.CommonResponse;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserInfoController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping({"/systemRole"})
    public CommonResponse getUserInfo(@RequestParam Integer id) {
        CommonResponse commonResponse = new CommonResponse();
        UserRole userRole = userService.getById(id);
        commonResponse.setData(userRole);
        return commonResponse;
    }


}
