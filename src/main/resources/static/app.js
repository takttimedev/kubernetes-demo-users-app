function getUserDetails(id){
	
	$('#user_details').text("");
	document.getElementById("spinner").style.display = "block";
	$.get("/user/"+id, function(user, status) {
		document.getElementById("spinner").style.display = "none";
		$('#user_details').append('<table class="table table-striped table-bordered" />');
		var tableData = "<tr><td><b>Host IP</b></td><td>"+user.hostName+"</td></tr>"+
			"<tr><td><b>User Id</b></td><td>"+user.id+"</td></tr>"+
		"<tr><td><b>Name</b></td><td>"+user.name+"</td></tr>"+
		"<tr><td><b>Reports To</b></td><td>"+user.reportsTo+"</td></tr>"+
		"<tr><td><b>Experience</b></td><td>"+user.experience+"</td></tr>"+
		"<tr><td><b>Skills</b></td><td>"+user.skills+"</td></tr>";
		$('#user_details table').append(tableData);
	});
}

function getUsers() {

	document.getElementById("spinner").style.display = "block";
	$.get("/users", function(data, status) {
		
		document.getElementById("spinner").style.display = "none";
		console.log(data);
		
		$('#all_users').text("");
		$('#all_users').append('<table class="table table-bordered" />');

		$('#all_users table').append(
				'<thead>' +
					'<th>Host IP</td>' +
					'<th>User Id</td>' + 
					'<th>User Name</td>' +
					'<th>Actions</td>' + 
		        '</thead>');
		data.forEach((user,index) => {
			var td = "<td>"+user.hostName+"</td>"+
				"<td>"+user.id+"</td>"+
			"<td>"+user.name+"</td>"+
			"<td><button type='button' onClick=getUserDetails("+user.id+")>Details</button></td>";
			$('#all_users table').append('<tr>' + td+ '</tr>');			
		});
	});
}

$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$("#users_button").click(function() {
		getUsers();
	});
});