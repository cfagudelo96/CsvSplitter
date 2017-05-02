<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>CsvSplitter</title>
	</head>
	<body>
		<div class="jumbotron">
			<h1>Bienvenido a CsvSplitter</h1>
			<p>
				Para hacer split de un archivo csv de cursos, selecciona por favor el archivo e ingresa los nombres
				de los cursos que deseas obtener en nuevos archivos csv. Agrega cuantos desees!
			</p>
		</div>
		<g:if test="${flash.error}">
			<div class="alert alert-danger fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				${flash.error}
			</div>
		</g:if>
		<div>
			<g:uploadForm controller="csvSplitter" action="cargarArchivo" name="formSplitter">
				<div class="row">
					<div class="col-md-6 col-xs-12">
						<h3>Archivo</h3>
						<div class="form-group">
							<g:field type="file" name="archivo" required="${true}" class="btn btn-default"/>
						</div>
						<h3>Separador <small>Indique por favor el separador del archivo csv</small></h3>
						<div class="form-group">
							<g:field type="text" name="separador" required="${true}" maxlength="1" class="form-control"/>
						</div>
						<input type="submit" value="Enviar archivo" class="btn btn-default">
					</div>
					<div class="col-md-6 col-xs-12">
						<h3>Cursos</h3>
						<div id="div-cursos">
							<div class="form-group">
								<g:field type="text" name="cursos" required="${true}" placeholder="Curso" class="form-control"/>
							</div>
						</div>
						<div class="btn-group" role="group">
							<button type="button" onclick="removerCurso()" class="btn btn-default" id="btn-remover" disabled><span class="glyphicon glyphicon-minus"></span></button>
							<button type="button" onclick="agregarCurso()" class="btn btn-default" id="btn-agregar"><span class="glyphicon glyphicon-plus"></span></button>
						</div>
					</div>
				</div>
			</g:uploadForm>
		</div>
	</body>
</html>
