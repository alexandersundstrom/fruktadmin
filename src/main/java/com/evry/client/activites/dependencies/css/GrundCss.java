package com.evry.client.activites.dependencies.css;

import com.google.gwt.resources.client.CssResource;

public interface GrundCss extends CssResource {
    String contentFooter();

    String nav();

    String subMenu();

    String mobilemenuinside();

    String loginbtn();

    String thirdLevel();

    String active();

    String menuwrapper();

    String secondLevel();

    String hover();

    String mainheader();

    @ClassName("menu-container")
    String menuContainer();

    @ClassName("toggle-menu")
    String toggleMenu();

    String activefontcolor();

    @ClassName("start-alternativ")
    String startAlternativ();
}
