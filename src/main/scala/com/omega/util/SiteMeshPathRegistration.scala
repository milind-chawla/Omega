package com.omega.util

import org.sitemesh.builder.SiteMeshFilterBuilder
import org.sitemesh.content.tagrules.html.DivExtractingTagRuleBundle
import org.sitemesh.content.tagrules.html.Sm2TagRuleBundle

object SiteMeshPathRegistration {
    def apply(builder: SiteMeshFilterBuilder): Unit = {
        builder.addTagRuleBundle(new Sm2TagRuleBundle)
        
        builder.addDecoratorPath("/home/*", "/WEB-INF/views/jsp/_layout0/base.jsp")
            .addDecoratorPath("/books/*", "/WEB-INF/views/jsp/_layout0/base.jsp")
            .addDecoratorPath("/angularseed/*", "/WEB-INF/views/jsp/_layout0/base.jsp")
    }
}