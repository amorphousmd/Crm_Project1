$(document).ready(function(){
	// Prevent default form submission behavior when the Xacnhan button is clicked
    document.querySelector('.btn-xacnhan').addEventListener('click', function(event) {
        event.preventDefault(); // Prevent the default form submission behavior
        // Add your custom functionality here
		const popup = document.getElementById("popup")
		const nameInput = document.getElementById("popup-input-name")
		const descriptionInput = document.getElementById("popup-input-description")
		const name = nameInput.value;
		const description = descriptionInput.value;
		popup.classList.remove("open-popup")
		let highestId = 0;
		$('.table-select tbody tr').each(function() {
	        const id = parseInt($(this).find('td:eq(0)').text());
	        if (id > highestId) {
	            highestId = id;
	        }
	    });
	
	    // Increment the highest ID to get the new ID for the added row
	    const newId = highestId + 1;
	
	    // Create a new table row
	    const newRow = `
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
	
	    // Append the new row to the table
	    $('.table-select tbody').append(newRow);
    });
	
	$('.btn-sua').click(function(){
		console.log("Document ready function is executed.");
		let popup = document.getElementById("popup")
		
		popup.classList.add("open-popup")
	})
	
	$('.btn-xoa').click(function(){
		console.log("Document ready function is executed.");
		
/*		$(this) // Đại diện cho thẻ đang click
*/		
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
		    alert( "Data Saved: " + msg );
		  });
	  
	})

	
})