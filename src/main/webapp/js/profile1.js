$( document ).ready(function (){
	function convertDateFormat(inputDate) {
	    const date = new Date(inputDate);
	    
	    const year = date.getFullYear();
	    const month = String(date.getMonth() + 1).padStart(2, '0');
	    const day = String(date.getDate()).padStart(2, '0');
	    const hours = String(date.getHours()).padStart(2, '0');
	    const minutes = String(date.getMinutes()).padStart(2, '0');
	    const seconds = String(date.getSeconds()).padStart(2, '0');
	
	    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
	}

    document.querySelector('.btn-xacnhan').addEventListener('click', function(event) {
        event.preventDefault(); // Prevent the default form submission behavior
		const popup = document.getElementById("popup");
		const statusInput = document.getElementById("popup-input-project-status");
		const startDateInput = document.getElementById("popup-input-project-start-date");
		const endDateInput = document.getElementById("popup-input-project-end-date");
		
		const status = statusInput.value;
		const startDate = startDateInput.value;
		const endDate = endDateInput.value;
		
		let statusString = "Không Xác Định";
	    switch (status) {
	        case "1":
	            statusString = "Đang Thực Hiện";
	            break;
	        case "2":
	            statusString = "Hoàn Thành";
	            break;
	        case "3":
	            statusString = "Chưa Bắt Đầu";
	            break;
	    }
		
		var This = $(`.btn-modify[id-project="${selectedRow}"]`);
		var id = selectedRow;
		
		$.ajax({
		  method: "GET",
		  url: `http://localhost:8080/crmproject/api/task/modify-user-level?id=${id}&statusNum=${status}&startDate=${startDate}&endDate=${endDate}`,
		})
		  .done(function( result ) {
			  console.log(result, " data ", result.data);
			  if(result.data == true){
			  	var entryRow = This.parent().parent();
		        entryRow.find("td:eq(5)").html(statusString);
		        entryRow.find("td:eq(3)").html(convertDateFormat(startDate));
		        entryRow.find("td:eq(4)").html(convertDateFormat(endDate));
			  }
		  });
		
		popup.classList.remove("open-popup")
	});
	
	$('.btn-modify').click(function(){
		let popup = document.getElementById("popup")
		selectedRow = $(this).attr("id-project")
		popup.classList.add("open-popup")
	})
})