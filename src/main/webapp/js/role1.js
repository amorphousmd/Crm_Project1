$(document).ready(function(){
	$('.btn-sua').click(function(){
		console.log("Document ready function is executed.");
		let popup = document.getElementById("popup")
		
		popup.classList.add("open-popup")
	})
	
	$('.btn-xoa').click(function(){
/*		alert("Hello Baby")
*/		console.log("Document ready function is executed.");
		
/*		$(this) // Đại diện cho thẻ đang click
*/		
		var id = $(this).attr("id-role")
		var This = $(this)
		alert("Hello Baby" + id)
		
		/*$.ajax({
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
		  });*/
	  
	})
	
	
})