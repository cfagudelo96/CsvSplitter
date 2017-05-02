package csvsplitter

import org.grails.plugins.csv.CSVMapReader
import org.springframework.web.multipart.MultipartFile

import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class CsvSplitterController {

    def csvSplitterService

    def cargarArchivo() {
        String separador = params.separador
        def cursos = params.list('cursos')
        MultipartFile archivo = request.getFile('archivo')
        if(separador && cursos.size() > 0 && archivo) {
            def split = archivo.getOriginalFilename().split('\\.')
            if(split.size() > 1 && split[split.size() - 1] == 'csv') {
                byte[] bytes = archivo.getBytes()
                if(bytes.length != 0) {
                    try {
                        def mapaCursos = csvSplitterService.crearMapaCursos(cursos)
                        String contenido = new String(bytes)
                        def settings = [separatorChar: separador]
                        contenido.toCsvMapReader(settings).each { map ->
                            csvSplitterService.leerLinea(map, mapaCursos)
                        }
                        response.setContentType('APPLICATION/OCTET-STREAM')
                        response.setHeader('Content-Disposition', 'Attachment;Filename="Cursos csv.zip"')
                        ZipOutputStream zip = new ZipOutputStream(response.outputStream);
                        for(mapaCurso in mapaCursos) {
                            String csvCurso = mapaCurso.value.writer.toString()
                            csvCurso = csvCurso.replace("\",\"","\"${separador}\"")
                            ZipEntry zipEntry = new ZipEntry("${mapaCurso.key}.csv")
                            zip.putNextEntry(zipEntry)
                            zip.write(csvCurso.getBytes())
                        }
                        zip.close()
                        return
                    } catch (Exception e) {
                        flash.error = e.getMessage()
                    }
                } else {
                    flash.error = 'El archivo seleccionado se encuentra vacío'
                }
            } else {
                flash.error = 'No se seleccionó un archivo con extensión csv'
            }
        } else {
            flash.error = 'No se brindó toda la información requerida'
        }
        redirect(uri: '/')
    }
}
