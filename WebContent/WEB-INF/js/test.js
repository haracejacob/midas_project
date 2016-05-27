	$(document).ready(function() {
		$('input[id^=delBtn]').click(function() {
			var btn_str = $(this).attr("id").split("_");
			var btn_id = btn_str[1];
			
			$.ajax({
				url:"./rest/test",
				type:"delete",
				data:{'seq':btn_id},
				success:function(){
					alert(1);
				}
			});
		});
		$('#testButtonn').click(function() {
			alert(1);
		});

		$('#testInsertButton').click(function() {
			var formData = $('form').serialize();

			$.ajax({
	            url:"./rest/test",
	            type:"POST",
	            data:formData,
	            success:function(data){
	                alert(data);
				}
	        });
		});
		
		$.ajax({
	        url: "./rest/test.json"
	    }).then(function(data) {
	    	var i=0;
	    	while(data[i])
	    	{
	    		putAttributeAtTable(data[i]);
	    		i++;
	    	} 
	    });
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
        dataSource += "<td><a href=\"deleteTest?seq="+data.seq+"\">ªË¡¶1</a></td>";
		dataSource += "<td><input id=\"delBtn_"+data.seq+"\" type=\"button\" value=\"Delete\"/></td>";
		dataSource += "</tr>";
		$('#testTable').append(dataSource);
	};