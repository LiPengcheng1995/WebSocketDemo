package com.myWebApp.web;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;


/**
 * @Author: lpc
 * @Description:
 * @Date: create in 15:37 2017/10/13
 * @Modified By:
 */
@Controller

public class homeController {
    @Resource
    LogWebSocketHandler socket;

    /**
     * 这里之前有问题，所有的userName都变成了jquery-3.2.1.min
     * 这种将请求当链接写的虽然会让用户操作看起来像访问资源，但是也有问题，
     * 在页面中有jquery的link时，浏览器会在申请完页面加载时会向浏览器
     * 申请资源http://localhost/springWebSocket/ListernPage/jquery-3.2.1.min.js
     *  =_= 又是一个userName
     * @param userName
     * @param session
     * @return
     */
    @RequestMapping(value = "/ListernPage")
    public ModelAndView turnToListernPage(String userName, HttpSession session){
        session.setAttribute("userName",userName);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ListernPage");
        return mv;
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView turnToLogin(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }
    @RequestMapping(value = "/PublishPage",method = RequestMethod.GET)
    public ModelAndView turnToPublishPage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("PublishPage");
        mv.addObject("userNames",socket.getAllUserNames());
        return mv;
    }

    @RequestMapping(value = "PublishInforMation",method = RequestMethod.POST)
    @ResponseBody
    public String publishInformation(@RequestParam(value = "userNames") String userNames,
                                   @RequestParam(value = "message") String message){
        //向ArrayList中的所有用户发送数据
        String names[] = userNames.split(",");
        ArrayList<String> userNamesArryList = new ArrayList<String>();
        for (int i=0;i<names.length;i++) {
            userNamesArryList.add(names[i]);
        }
        socket.sendMessageToUsers(userNamesArryList,message);
        return "success";
    }

}
