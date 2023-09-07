$( document ).ready(function (){
		// Prevent default form submission behavior when the Xacnhan button is clicked
    document.querySelector('.btn-xacnhan').addEventListener('click', function(event) {
        event.preventDefault(); // Prevent the default form submission behavior
		const popup = document.getElementById("popup");
		const fullnameInput = document.getElementById("popup-input-fullname");
		const emailInput = document.getElementById("popup-input-email");
		const locationInput = document.getElementById("popup-input-location");
		const phoneInput = document.getElementById("popup-input-phone");
		const roleNumInput = document.getElementById("popup-input-rolenum");
		
		const fullname = fullnameInput.value;
		const email = emailInput.value;
		const location = locationInput.value;
		const phone = phoneInput.value;
		const roleNum = roleNumInput.value;
		
		var This = $(`.btn-modify[id-user="${selectedRow}"]`);
		var id = selectedRow;
		
		console.log("here");
		$.ajax({
		  method: "GET",
		  url: `http://localhost:8080/crmproject/api/user/modify?id=${id}&fullname=${fullname}&email=${email}&location=${location}&phone=${phone}&roleNum=${roleNum}`,
		})
		  .done(function( result ) {
			  console.log(result, " data ", result.data);
			  if(result.data == true){
			  	var entryRow = This.parent().parent();
		        entryRow.find("td:eq(1)").html(fullname);
		        entryRow.find("td:eq(2)").html(email);
		        entryRow.find("td:eq(3)").html(location);
		        entryRow.find("td:eq(4)").html(phone);
		        entryRow.find("td:eq(5)").html(roleNum);
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
		  url: `http://localhost:8080/crmproject/api/user/delete?id=${id}`,
		})
		  .done(function( result ) {
			  console.log(result, " data ", result.data);
			  if(result.data == true){
				  This.closest('tr').remove();
			  }
		  });
	})
	
})