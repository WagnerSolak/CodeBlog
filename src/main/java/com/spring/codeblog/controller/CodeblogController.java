package com.spring.codeblog.controller;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.service.CodeblogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CodeblogController {

    @Autowired
    CodeblogService codeblogService;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getPosts(){ //recebe as requisições do cliente -> ModelAndView
        ModelAndView mv = new ModelAndView("posts");
        List<Post> posts = codeblogService.findAll();
        mv.addObject("posts", posts);
        return mv;
    }

    // Metodo vai receber através da URL /posts/id uma req para retornar os detalhes de um determinado post
    @RequestMapping(value="/posts/{id}", method = RequestMethod.GET)
    public ModelAndView getPostDetail(@PathVariable("id") long id){
        ModelAndView mv = new ModelAndView("postDetails"); //view
        Post post = codeblogService.findById(id);
        mv.addObject("posts", post); //model
        return mv;

    }

}
