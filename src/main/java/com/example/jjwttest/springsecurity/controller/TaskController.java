package com.example.jjwttest.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by echisan on 2018/6/23
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @GetMapping
    public String listTasks(){
        return "任务列表";
    }

    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    //hasRole的角色信息需要加上"ROLE_"前缀,可以简写成hasRole('ADMIN'),
    //hasAuthority不需要加上前缀,角色信息是什么，就写成什么
    @PreAuthorize("hasAuthority('ADMIN')")
    public String newTasks(){
        return "创建了一个新的任务";
    }

    @PutMapping("/{taskId}")
    public String updateTasks(@PathVariable("taskId")Integer id){
        return "更新了一下id为:"+id+"的任务";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{taskId}")
    public String deleteTasks(@PathVariable("taskId")Integer id){
        return "删除了id为:"+id+"的任务";
    }
}
