/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-05-24 19:01:20 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class admin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/WEB-INF/views/common/navigation.jspf", Long.valueOf(1526845551206L));
    _jspx_dependants.put("/WEB-INF/views/common/footer.jspf", Long.valueOf(1526923541037L));
    _jspx_dependants.put("/WEB-INF/views/common/header.jspf", Long.valueOf(1527187424321L));
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("\r\n");
      out.write("<link href=\"webjars/bootstrap/3.3.6/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"webjars/fullcalendar/3.8.0/fullcalendar.min.css\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script src=\"webjars/jquery/1.11.3/jquery.min.js\"></script>\r\n");
      out.write("<script src=\"webjars/bootstrap/3.3.6/js/bootstrap.min.js\"></script>\r\n");
      out.write("<script src=\"webjars/momentjs/2.10.6/moment.js\"></script>\r\n");
      out.write("<script src=\"webjars/fullcalendar/3.8.0/fullcalendar.js\"></script>\r\n");
      out.write("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css\">\r\n");
      out.write("  \r\n");
      out.write("\r\n");
      out.write("<title>WetPrzychodnia</title>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>");
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("<nav role=\"navigation\" class=\"navbar navbar-default\">\r\n");
      out.write("\t<div class=\"\">\r\n");
      out.write("\t\t<a class=\"navbar-brand\">WetPrzychodnia</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div>\r\n");
      out.write("\t\t<ul class=\"nav navbar-nav\">\r\n");
      out.write("\t\t\t<li><a href=\"/\">Strona Główna</a></li>\r\n");
      out.write("\t\t\t<li><a href=\"/list-patients\">Pacjenci</a></li>\r\n");
      out.write("\t\t\t<li><a href=\"/goods\">Magazyn</a></li>\r\n");
      out.write("\t\t\t<li><a href=\"/finance-report\">Finanse</a></li>\r\n");
      out.write("\t\t\t<li><a href=\"/admin\">Panel Administratora</a></li>\r\n");
      out.write("\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<ul class=\"nav navbar-right\">\r\n");
      out.write("\t\t\t<li><a href=\"/logout\"\">Wyloguj się     </a></li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<script>\r\n");
      out.write("\t/*menu handler*/\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t  function stripTrailingSlash(str) {\r\n");
      out.write("\t    if(str.substr(-1) == '/') {\r\n");
      out.write("\t      return str.substr(0, str.length - 1);\r\n");
      out.write("\t    }\r\n");
      out.write("\t    return str;\r\n");
      out.write("\t  }\r\n");
      out.write("\r\n");
      out.write("\t  var url = window.location.pathname;  \r\n");
      out.write("\t  var activePage = stripTrailingSlash(url);\r\n");
      out.write("\r\n");
      out.write("\t  $('.nav li a').each(function(){  \r\n");
      out.write("\t    var currentPage = stripTrailingSlash($(this).attr('href'));\r\n");
      out.write("\r\n");
      out.write("\t    if (activePage == currentPage) {\r\n");
      out.write("\t      $(this).parent().addClass('active'); \r\n");
      out.write("\t    } \r\n");
      out.write("\t  });\r\n");
      out.write("\t});\r\n");
      out.write("\t</script>\r\n");
      out.write("</nav>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class = \"container\">\r\n");
      out.write("\t\r\n");
      out.write("<header>Wybierz akcjÄ:</header></br>\r\n");
      out.write("\t\r\n");
      out.write("<table class=\"table table-striped\">\t\r\n");
      out.write("\t<tbody>\t\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td>Dodaj nowego uÅ¼ytkownika</td>\r\n");
      out.write("\t\t\t<td><a href=\"/add-user\" class = \"btn btn-info\">OK</a></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td>Zaktualizuj dane uÅ¼ytkownika:</td>\r\n");
      out.write("\t\t\t<td><a href=\"/list-users\" class = \"btn btn-info\">OK</a></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td>Edytuj grafik:</td>\r\n");
      out.write("\t\t\t<td><a href=\"/list-shifts\" class = \"btn btn-info\">OK</a></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<!--  TODO - TO BE COMPLETED IN THE FUTURE\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td>Manage consultations</td>\r\n");
      out.write("\t\t\t<td><a href=\"/consultations\" class = \"btn btn-info\">OK</a></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t-->\r\n");
      out.write("\t</tbody>\r\n");
      out.write("</table>\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
