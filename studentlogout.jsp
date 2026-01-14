<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ page import="javax.servlet.http.*, javax.servlet.*"%>
<%
    session.invalidate();
    response.sendRedirect("studentlogin.html");
    
%>