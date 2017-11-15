//var success = false;
//var message;
//
//function connect() {
//  $('.signin').addClass('loading');
//  $('.signin').off('click', connect);
//  setTimeout(misteryMessage, 2000);
//}

//function misteryMessage(){
//  message = (success) ? 'success' : 'error';
//  $('.message-' + message).addClass('active');
//  setTimeout(function(){
//    $('.signin').removeClass('loading');
//  }, 500);
//  success = !success;
//}

function closeMessage() {
  $('.message-' + message).removeClass('active');
  $('.signin').on('click', connect);
}

//$('.signin').on('click', misteryMessage);
$('.close').click(closeMessage);

//
//$(document).ready(function() {
//	
//	alert("exception: ");
//});