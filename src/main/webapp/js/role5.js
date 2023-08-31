$(document).ready(function(){
	var selectedRow = 0;
	// Prevent default form submission behavior when the Xacnhan button is clicked
    document.querySelector('.btn-xacnhan').addEventListener('click', function(event) {
        event.preventDefault(); // Prevent the default form submission behavior
		const popup = document.getElementById("popup");
		const nameInput = document.getElementById("popup-input-name");
		const descriptionInput = document.getElementById("popup-input-description");
		const name = nameInput.value;
		const description = descriptionInput.value;
		
		var This = $(`.btn-sua[id-role="${selectedRow}"]`);
		var id = selectedRow;
		
		console.log("here");
		$.ajax({
		  method: "GET",
		  url: `http://localhost:8080/crmproject/api/role/modify?id=${id}&name=${name}&description=${description}`,
		})
		  .done(function( result ) {
			  console.log(result, " data ", result.data);
			  if(result.data == true){
				  var entryRow = This.parent().parent()
				  const nameEntry = entryRow.find("td:eq(1)");
				  nameEntry.text(name);
				  const descriptionEntry = entryRow.find("td:eq(2)");
				  descriptionEntry.text(description);
			  }
		  });
		
		popup.classList.remove("open-popup")
		
	    // Code to add new row instead of modifying

		/*let highestId = 0;
		$('.table-select tbody tr').each(function() {
	        const id = parseInt($(this).find('td:eq(0)').text());
	        if (id > highestId) {
	            highestId = id;
	        }
	    });
	
	    const newId = highestId + 1;*/
	
	    /*const newRow = `
	        <tr>
	            <td>${newId}</td>
	            <td>${name}</td>
	            <td>${description}</td>
	            <td>
	                <a href="#" class="btn btn-sm btn-primary btn-sua">Sửa</a>
	                <a href="#" id-role="${newId}" class="btn btn-sm btn-danger btn-xoa">Xóa</a>
	            </td>
	        </tr>
	    `;
	
	    $('.table-select tbody').append(newRow);*/
    });
	
	$('.btn-sua').click(function(){
		let popup = document.getElementById("popup")
		selectedRow = $(this).attr("id-role")
		popup.classList.add("open-popup")
	})
	
	$('.btn-xoa').click(function(){		
		var id = $(this).attr("id-role")
		var This = $(this)
	
		$.ajax({
		  method: "GET",
		  url: `http://localhost:8080/crmproject/api/role/delete?id=${id}`,
		  data: { name: "John", location: "Boston" }
		})
		  .done(function( result ) {
			  console.log(result, " data ", result.data);
			  if(result.data == true){
				  This.parent().parent().remove()
				  This.closest('tr').remove();
			  }
		    alert("Done");
		  });
	  
	})

	
})