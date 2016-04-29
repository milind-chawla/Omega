package com.omega.util

import org.sitemesh.builder.SiteMeshFilterBuilder

object SiteMeshPathRegistration {
    def apply(builder: SiteMeshFilterBuilder): Unit = {
        builder.addDecoratorPath("/home/*", "/WEB-INF/views/jsp/_layout0/home.jsp")
            .addDecoratorPath("/books/*", "/WEB-INF/views/jsp/_layout0/books.jsp")
    }
}