// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better 
// to create separate JavaScript files as needed.
//
//= require jquery
//= require bower_components/bootstrap/dist/js/bootstrap.min.js
//= require_tree .
//= require_self

if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}

function agregarCurso() {
	$('#div-cursos').append(
		'<div class="form-group">' +
			'<input type="text" name="cursos" required="true" placeholder="Curso" class="form-control" value="">' +
		'</div>'
	);
    $('#btn-remover').removeAttr('disabled');
}

function removerCurso() {
	$('#div-cursos div.form-group:last-child').remove();
	if($('#div-cursos div.form-group').length == 1) {
		$('#btn-remover').attr('disabled', true);
	}
}
