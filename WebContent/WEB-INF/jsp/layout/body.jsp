<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<div>
	body
	<table>
		<tr>
			<th>seq</th>
			<th>id</th>
			<th>gems</th>
			<th>coins</th>
			<th>hearts</th>
			<th>highscore</th>
		</tr>
		<s:iterator var="test" value="testList">
			<tr>
				<td>${test.seq}</td>
				<td>${test.id}</td>					
				<td>${test.gems}</td>
				<td>${test.coins}</td>
				<td>${test.highscore}</td>
			</tr>
		</s:iterator>		
	</table>
</div>