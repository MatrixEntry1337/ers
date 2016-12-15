function changeDescription(text){
	var textLength = text.length;
	var maxLength = 25, maxWord = 10;
	var index = textLength > maxLength ? maxLength : textLength;
	var nextSpace = text.indexOf(' ', index);
	if(nextSpace > index && nextSpace - index <= maxWord ){
		index = nextSpace;
	}
	var dotString = index < textLength ? "..." : ""; 
	return text.substring(0, index)+dotString;	
}

function main(){
	console.log("The js debug for ERS: ");
	$(".shorten").each(function(){
		console.log("Text says: " + $(this).text());
		var text = $(this).text();
		var newText = changeDescription(text);
		console.log("Changing text to: " + newText);
		$(this).html(newText);
	});
};



$(document).ready(main());