<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>	
	<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
	<link href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
	
	<%-- <script src="/midasWeb/WEB-INF/js/test.js"></script> --%>
	<script>
		$(document).ready(function() {
			$('input[id^=delBtn]').click(function() {
				var btn_str = $(this).attr("id").split("_");
				var btn_id = btn_str[1];

				$.ajax({
					url:"./rest/test/"+btn_id,
					type:"delete",
					data:{'seq':btn_id},
					success:function(data){
						
					}
				});
			});
	
			$('#testInsertButton').click(function() {
				var formData = $('form').serialize();
	
				$.ajax({
		            url:"./rest/test",
		            type:"POST",
		            data:formData,
		            success:function(data){
		                
					}
		        });
			});
			
			var table = $('#myTable').DataTable();
			/* $.ajax({
		        url: "./rest/test.json"
		    }).then(function(data) {
		    	var i=0;
		    	while(data[i])
		    	{
		    		putAttributeAtTable(data[i]);
		    		i++;
		    	} 
		    }); */
		});
		
		function putAttributeAtTable(data){
			var dataSource = "";
			dataSource += "<tr>";
	        dataSource += "<td>"+data.seq+"</td>";
	        dataSource += "<td>"+data.id+"</td>";
	        dataSource += "<td>"+data.gems+"</td>";
	        dataSource += "<td>"+data.coins+"</td>";
	        dataSource += "<td>"+data.hearts+"</td>";
	        dataSource += "<td>"+data.highscore+"</td>";
	        dataSource += "<td><a href=\"deleteTest?seq="+data.seq+"\">삭제1</a></td>";
			dataSource += "<td><input id=\"delBtn_"+data.seq+"\" type=\"button\" value=\"Delete\"/></td>";
			dataSource += "</tr>";
			$('#testTable').append(dataSource);
		};
	</script>
	<style>
		#overlay {
		    position: fixed; 
		    top: 0;
		    left: 0;
		    width: 100%;
		    height: 100%;
		    background: #000;
		    opacity: 0.5;
		    filter: alpha(opacity=50);
		}
	
		#modal {
		    position:absolute;
		    background:url(tint20.png) 0 0 repeat;
		    background:rgba(0,0,0,0.2);
		    border-radius:14px;
		    padding:8px;
		}
		
		#content {
		    border-radius:8px;
		    background:#fff;
		    padding:20px;
		}
		
		#close {
		    position:absolute;
		    background:url(close.png) 0 0 no-repeat;
		    width:24px;
		    height:27px;
		    display:block;
		    text-indent:-9999px;
		    top:-7px;
		    right:-7px;
		}
	</style>
	
<div>
	<form id="testInsertForm" method="post" action="insertTest">
		<table id="insertTable">
			<tr>
				<td>id</td>
				<td><input type="text" name="id" value="" /></td>
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
				<th>id</th>
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
				<tr>
					<td>${test.seq}</td>
					<td>${test.id}</td>					
					<td>${test.gems}</td>
					<td>${test.coins}</td>
					<td>${test.hearts}</td>
					<td>${test.highscore}</td>
					<td><a href="deleteTest?seq=${test.seq}">삭제1</a></td>
					<td><input id="delBtn_${test.seq}" type="button" value="Delete"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<input type="button" id="testButtonn" value="test"/>
</div>
<div id="overlay">

</div>
<div id="modal">
	<div id="content">No JavaScript Yet!</div>
    <a href="#" id="close">close</a>
</div>