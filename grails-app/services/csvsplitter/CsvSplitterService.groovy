package csvsplitter

import grails.transaction.Transactional
import org.grails.plugins.csv.CSVWriter

@Transactional
class CsvSplitterService {

    def crearMapaCursos(cursos, mapa) {
        def mapaCursos = [:]
        def encabezados = mapa.keySet()
        if(!encabezados.any { it == 'Course Id' }) {
            throw new Exception("No se encontró una columna llamada 'Course Id'")
        }
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
