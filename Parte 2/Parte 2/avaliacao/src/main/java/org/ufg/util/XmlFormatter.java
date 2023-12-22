package org.ufg.util;

import org.ufg.dto.CursoDTO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlFormatter {
    public static List<CursoDTO> transformaXmlUniversidadeEmObjeto(String arquivo) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(arquivo);

            List<CursoDTO> universidade = new ArrayList<>();

            NodeList cursos = document.getElementsByTagName("curso");
            for (int i = 0; i < cursos.getLength(); ++i) {
                Element cur = (Element) cursos.item(i);
                int iden = Integer.parseInt(cur.getElementsByTagName("iden").item(0).getTextContent());
                int ano = Integer.parseInt(cur.getElementsByTagName("ano").item(0).getTextContent());
                String nome = cur.getElementsByTagName("nome").item(0).getTextContent();
                String disciplina = cur.getElementsByTagName("disciplina").item(0).getTextContent();
                int ch = Integer.parseInt(cur.getElementsByTagName("ch").item(0).getTextContent());

                CursoDTO cursoDTO = new CursoDTO(iden, ano, nome, disciplina, ch);
                universidade.add(cursoDTO);
            }
            return universidade;
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
