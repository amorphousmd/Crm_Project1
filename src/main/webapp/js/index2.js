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
		  
		  let total = stateArray[0] + stateArray[1] + stateArray[2];

		  let notStartedRatio = Math.round((stateArray[0] / total) * 100);
		  let inProgressRatio = Math.round((stateArray[1] / total) * 100);
		  let finishedRatio = Math.round((stateArray[2] / total) * 100);
		
		  $("#progress-bar-not-started").css("width", notStartedRatio.toString() + "%");
		  $("#progress-bar-in-progress").css("width", inProgressRatio.toString() + "%");
		  $("#progress-bar-finished").css("width", finishedRatio.toString() + "%");
	  });
})