package com.fricke.personal.employee.webpage;

public class Page {

    private String template;

    public Page() {

    }

    public String getPage() {
        //<link rel="stylesheet"  href="/css/bootstrap.min.css">
        template = "<link rel=stylesheet  href= /resources/stylesheet/style.css>" +
                "<h1 style= color:DodgerBlue;> Registration </h1>";
        return template;
    }
}
