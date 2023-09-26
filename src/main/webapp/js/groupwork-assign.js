$(document).ready(function (){	
    $('.btn-save').click(function(){
		var arr = [];
        $('.item-label').each(function() {
            var dataValue = $(this).data('value');
            arr.push(dataValue);
            // Do something with dataValue
        });
        let str = arr.join(',');
        const selectedValue = $('#selected-project').val();
        console.log(selectedValue);
        console.log(str)
        $.ajax({
		  method: "GET",
		  url: `http://localhost:8080/crmproject/api/project/add-users?project=${selectedValue}&user-list=${str}`,
		})
	  	.done(function( result ) {
		  console.log(result, " data ", result.data);
		  alert("Insert successful.")
	  	});
    });
});
