// creates shorten message 
function changeDescription(text){
	var textLength = text.length;
	var maxLength = 25, maxWord = 10;
	var index = textLength > maxLength ? maxLength : textLength;
	var nextSpace = text.indexOf(' ', index);
	if(nextSpace > index && nextSpace - index <= maxWord ){
		index = nextSpace;
	}
	if(textLength - index < 7) index = textLength;
	var dotString = index < textLength ? "..." : ""; 
	return text.substring(0, index)+dotString;	
}

function main(){
	console.log("The ERS Web App logs: ");
	// shortens the text for each description
	$(".shorten").each(function(){
		// get text from class
		var text = $(this).text();
		var newText = changeDescription(text);
		$(this).html(newText);
	});
	
	// fades out client message after 10s
	$(".client-message").delay(1000*10).fadeOut("slow");
	
	// count the number of characters in description
	$("#reim-describe").keyup(function(){
		var chars = $("#reim-describe").val();
		var numChars = 250 - chars.length;
		$("#reim-describe-chars").html(numChars);
	});
	
};

$(document).ready(main());
