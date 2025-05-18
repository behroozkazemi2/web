
let showError = function(res){
$('.toast').addClass('toast-failed');
$('.toast').removeClass('hidden');
$('.toast-body').text(res.responseJSON[0].message);
$('.toast').toast('show');
};
let showErrorAnotherModel = function(res){
$('.toast').addClass('toast-failed');
$('.toast').removeClass('hidden');
$('.toast-body').text(res.responseJSON.message);
$('.toast').toast('show');
};

