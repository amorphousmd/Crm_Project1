$( document ).ready(function (){
		// Prevent default form submission behavior when the Xacnhan button is clicked
    document.querySelector('.btn-xacnhan').addEventListener('click', function(event) {
        event.preventDefault(); // Prevent the default form submission behavior
		const popup = document.getElementById("popup");
		const nameInput = document.getElementById("popup-input-project-name");
		const startDateInput = document.getElementById("popup-input-project-start-date");
		const endDateInput = document.getElementById("popup-input-project-end-date");
		
		const name = nameInput.value;
		const startDate = startDateInput.value;
		const endDate = endDateInput.value;

		
		var This = $(`.btn-modify[id-user="${selectedRow}"]`);
		var id = selectedRow;
		
		console.log("here");
		$.ajax({
		  method: "GET",
		  url: `http://localhost:8080/crmproject/api/project/modify?id=${id}&projectName=${name}&startDate=${startDate}&endDate=${endDate}`,
		})
		  .done(function( result ) {
			  console.log(result, " data ", result.data);
			  if(result.data == true){
			  	var entryRow = This.parent().parent();
		        entryRow.find("td:eq(1)").html(name);
		        entryRow.find("td:eq(2)").html(startDate);
		        entryRow.find("td:eq(3)").html(endDate);
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
		  url: `http://localhost:8080/crmproject/api/project/delete?id=${id}`,
		})
		  .done(function( result ) {
			  console.log(result, " data ", result.data);
			  if(result.data == true){
				  This.closest('tr').remove();
			  }
		  });
	})
	
})