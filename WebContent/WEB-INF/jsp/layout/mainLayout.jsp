<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="tiles"  uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		
	  	<title>
	  		<tiles:getAsString name="title" />
	  	</title>
	  	
		<meta http-equiv="content-type" content="text/html; CHARSET=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	
		
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta name="viewport" content="width=device-width; initial-scale=1.0;" />
	</head>
	<body>
		<div id="wrap">
		        <tiles:insertAttribute name="header" />
		        <tiles:insertAttribute name="body" />
		        <tiles:insertAttribute name="footer" />
		</div>
	</body>
</html>