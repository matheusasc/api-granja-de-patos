package com.granjadepatos.controller;

import com.granjadepatos.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {
    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> downloadRelatorioPDF() {
        ByteArrayInputStream bis = relatorioService.gerarRelatorioPDF();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=relatorio.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());
    }

    @GetMapping("/excel")
    public ResponseEntity<byte[]> downloadRelatorioExcel() {
        ByteArrayInputStream bis = relatorioService.gerarRelatorioExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=relatorio.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(bis.readAllBytes());
    }
}
