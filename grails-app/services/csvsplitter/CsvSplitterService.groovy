package csvsplitter

import grails.transaction.Transactional
import org.grails.plugins.csv.CSVWriter

@Transactional
class CsvSplitterService {

    def crearMapaCursos(cursos) {
        def mapaCursos = [:]
        def encabezados = ['User Id', 'Student Id', 'First Name', 'Last Name', 'Course Id', 'Course Name', 'Column Name', 'Grade',
                           'Score', 'Availability', 'Last Access Date', 'Last Attempt Date', 'Last Override Date', 'Display Title']
        for(curso in cursos) {
            CSVWriter csvWriter = new CSVWriter(new StringWriter(), {
                encabezados.each { encabezado ->
                    "${encabezado}" { it."${encabezado}" }
                }
            })
            mapaCursos["${curso}"] = csvWriter
        }
        return mapaCursos
    }

    void leerLinea(map, mapaCursos) {
        def cursoId = map['Course Id']
        if(cursoId) {
            if(mapaCursos["${cursoId}"]) {
                mapaCursos["${cursoId}"] << map
            }
        }
    }
}
