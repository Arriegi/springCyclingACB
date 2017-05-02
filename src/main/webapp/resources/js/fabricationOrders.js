$("div.left-menu ul li a").removeClass("active");
$("div.left-menu ul li:first a").addClass("active");

$(".modal.confirm h4.modal-title").text("Operazioa ezabatu");
$(".modal.confirm .modal-body p").text("Ziur operazioa ezabatu nahi duzula?");

var BUTTON_CLASS = "fabrication-order";
var href = "";

$(document).on("click", "a.delete-" + BUTTON_CLASS, function(event) {
	event.preventDefault();
	href = $(this).attr("href");
	$(".modal.confirm").modal("show");
});

$(document).on("click",".modal.confirm .modal-footer .btn-confirm",function(event){
	event.preventDefault();
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var headers = {};
	headers[header]=token;
	$.ajax({
		type : "GET",
		url : href,
		headers: headers
	}).always(function(result){
		$(".modal.confirm").modal("hide");
		location.reload();
	});
});