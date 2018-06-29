package com.aem.community.core.models;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Servlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

/***    
 * 
 * @author mohan krishna nalluri
 *
 *This class explains how to register Servlet using the resourceTypes and by passing selectors you can write different use cases
 *
 *This can be triggered from any page that has the given resourceType and can be used to abstract the API calls
 */

/*
 Using SCR annotations
 */


//@SlingServlet(
// methods = {"GET"},
// metatype= true,
// resourceTypes= {"AEM63POCS/components/structure/page"},
// selectors= {"one","two"})


/*
 Using R6 annotations
 */
@Component(
        immediate=true,
        service=Servlet.class,
           property={
                   ServletResolverConstants.SLING_SERVLET_METHODS+"=GET",
                   ServletResolverConstants.SLING_SERVLET_RESOURCE_TYPES+"=AEM63POCS/components/structure/page",
                   ServletResolverConstants.SLING_SERVLET_SELECTORS+"=one",
                   ServletResolverConstants.SLING_SERVLET_SELECTORS+"=two",
                   ServletResolverConstants.SLING_SERVLET_EXTENSIONS+"=html"
           })
public class ResourceTypeServletRegistration extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
             PrintWriter out = response.getWriter();
             String a = null;
             List<String> list = Arrays.asList(request.getRequestPathInfo().getSelectors());
             if (null != request.getRequestParameter("kittu")) {
              a = request.getRequestParameter("kittu").getString();
             }
             if(list.contains("one")) {
                 out.println("<html><body>");
                 out.println("<h1>This value was returned by an AEM Sling Servlet bound by using a Sling ResourceTypes property one</h1>" + a);
                 out.println("</html></body>");
             }
             if(list.contains("two")) {
                 out.println("<html><body>");
                 out.println("<h1>This value was returned by an AEM Sling Servlet bound by using a Sling ResourceTypes property two</h1>" + a);
                 out.println("</html></body>");
             }
             if(list.contains("three")) {
                 out.println("<html><body>");
                 out.println("<h1>This value was returned by an AEM Sling Servlet bound by using a Sling ResourceTypes property three</h1>" + a);
                 out.println("</html></body>");
             }
             out.println("<html><body>");
             out.println("<h1>This value was returned by an AEM Sling Servlet bound by using a Sling ResourceTypes property NO</h1>" + a);
             out.println("</html></body>");
             
      }
    }
