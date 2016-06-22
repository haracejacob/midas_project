<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		
	<script src="/midasWeb/js/jquery-2.2.4.min.js"></script>
	
	<script src="/midasWeb/js/jquery-ui.js"></script>
	<link href="/midasWeb/css/jquery-ui.css" rel="stylesheet" type="text/css" />
	
	<script src="/midasWeb/js/jquery.dataTables.min.js"></script>
	<link href="/midasWeb/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
	
	<script src="/midasWeb/js/test.js"></script>
	<script src="/midasWeb/js/modal.js"></script>
	<link href="/midasWeb/css/modal.css" rel="stylesheet" type="text/css" />
	
<div>
	<form id="testInsertForm" method="post" action="insertTest">
		<table id="insertTable">
			<tr>
				<td>id</td>
				<td><input type="text" name="test_id" value="" /></td>
			</tr>
			<tr>
				<td>gems</td>
				<td><input type="text" name="gems" value="" /></td>
			</tr>
			<tr>
				<td>coins</td>
				<td><input type="text" name="coins" value="" /></td>
			</tr>
			<tr>
				<td>hearts</td>
				<td><input type="text" name="hearts" value="" /></td>
			</tr>
			<tr>
				<td>highscore</td>
				<td><input type="text" name="highscore" value="" /></td>
			</tr>
		</table>
		<input id="testInsertSubmit" type="submit" value="제출" />
		<input id="testInsertButton" type="button" value="제출" />
		
	</form>

	<table id="myTable" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>seq</th>
				<th>test_id</th>
				<th>gems</th>
				<th>coins</th>
				<th>hearts</th>
				<th>highscore</th>
				<th>삭제1</th>
				<th>삭제2</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="test" value="testList">
				<tr id="test_row_${test.seq}">
					<td>${test.seq}</td>
					<td>${test.test_id}</td>					
					<td>${test.gems}</td>
					<td>${test.coins}</td>
					<td>${test.hearts}</td>
					<td>${test.highscore}</td>
					<td><button id="modyBtn_${test.seq}">Create</button></td>
					<td><button id="delBtn_${test.seq}">Delete</button></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</div>

<div id="dialog-form" title="Create new user">
	<p class="validateTips">All form fields are required.</p>
	
	<form id="testModifyForm">
		<fieldset>
			<input type="hidden" name="seq" id="modify_seq" value=""/> 
			<label for="id">id</label>
			<input type="text" name="test_id" id="modify_test_id" value="" class="text ui-widget-content ui-corner-all"><br>
			<label for="gems">gems</label>
			<input type="text" name="gems" id="modify_gems" value="" class="text ui-widget-content ui-corner-all"><br>
			<label for="coins">coins</label>
			<input type="text" name="coins" id="modify_coins" value="" class="text ui-widget-content ui-corner-all"><br>
			<label for="hearts">hearts</label>
			<input type="text" name="hearts" id="modify_hearts" value="" class="text ui-widget-content ui-corner-all"><br>
			<label for="highscore">highscore</label>
			<input type="text" name="highscore" id="modify_highscore" value="" class="text ui-widget-content ui-corner-all">
			
			<!-- Allow form submission with keyboard without duplicating the dialog button -->
			<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
		</fieldset>
	</form>
</div>
