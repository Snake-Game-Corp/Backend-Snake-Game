package com.lcaohoanq.views.exception;

import com.lcaohoanq.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Internal server error")
@Route(value = "error/internal", layout = MainLayout.class)
public class InternalServerErrorExceptionView extends Composite<VerticalLayout> {

    public InternalServerErrorExceptionView() {
        getContent().add("Internal server error");
    }

}
