package org.example;

import org.example.services.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CommentService commentService = context.getBean(CommentService.class);
        CommentService commentService1 = context.getBean(CommentService.class);

        boolean b = commentService1 == commentService;

        System.out.println(b);
    }
}
