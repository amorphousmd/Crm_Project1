$( document ).ready(function (){
		// Prevent default form submission behavior when the Xacnhan button is clicked
    document.querySelector('.btn-xacnhan').addEventListener('click', function(event) {
        event.preventDefault(); // Prevent the default form submission behavior
		const popup = document.getElementById("popup");
		const nameInput = document.getElementById("popup-input-task-name");
		const startDateInput = document.getElementById("popup-input-task-start-date");
		const endDateInput = document.getElementById("popup-input-task-end-date");
		const projectInput = document.getElementById("popup-input-task-project");
		const statusInput = document.getElementById("popup-input-task-status");
		
		const name = nameInput.value;
		const startDate = startDateInput.value;
		const endDate = endDateInput.value;
		const project = projectInput.value;
		const status = statusInput.value;
		
		var This = $(`.btn-modify[id-user="${selectedRow}"]`);
		var id = selectedRow;
		
		console.log("here");
		$.ajax({
		  method: "GET",
		  url: `http://localhost:8080/crmproject/api/task/modify?id=${id}&taskName=${name}&startDate=${startDate}&endDate=${endDate}&projectNum=${project}&statusNum=${status}`,
		})
		  .done(function( result ) {
			  console.log(result, " data ", result.data);
			  if(result.data == true){
			  	var entryRow = This.parent().parent();
		        entryRow.find("td:eq(1)").html(name);
		        entryRow.find("td:eq(2)").html(project);
		        entryRow.find("td:eq(4)").html(startDate);
		        entryRow.find("td:eq(5)").html(endDate);
		        entryRow.find("td:eq(6)").html(status);
			  }
		  });
		
		popup.classList.remove("open-popup")
	});
	
	$('.btn-modify').click(function(){
		let popup = document.getElementById("popup")
		selectedRow = $(this).attr("id-user")
		popup.classList.add("open-popup")
	})
	
	$('.btn-delete').click(function () {
		var id = $(this).attr("id-user");
		var This = $(this);
		
		$.ajax({
		  method: "GET",
		  url: `http://localhost:8080/crmproject/api/task/delete?id=${id}`,
		})
		  .done(function( result ) {
			  console.log(result, " data ", result.data);
			  if(result.data == true){
				  This.closest('tr').remove();
			  }
		  });
	})
	
})