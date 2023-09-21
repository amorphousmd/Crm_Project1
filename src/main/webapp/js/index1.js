$( document ).ready(function (){
	$.ajax({
	  method: "GET",
	  url: `http://localhost:8080/crmproject/api/project/getall`,
	})
	  .done(function( result ) {
		  let stateArray = result.data;
		  console.log(result, " data ", result.data);
		  let notStartedValue = document.getElementById('not-started-projects');
		  let inProgressValue = document.getElementById('in-progress-projects');
		  let finishedValue = document.getElementById('finished-projects');
		  notStartedValue.innerText = stateArray[0];
		  inProgressValue.innerText = stateArray[1];
		  finishedValue.innerText = stateArray[2];
	  });
})