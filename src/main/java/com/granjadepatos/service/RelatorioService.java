package com.granjadepatos.service;

import com.granjadepatos.enums.ClienteElegivel;
import com.granjadepatos.enums.PatoStatus;
import com.granjadepatos.model.ClienteModel;
import com.granjadepatos.model.VendaModel;
import com.granjadepatos.model.PatoModel;
import com.granjadepatos.repository.PatoRepository;
import com.granjadepatos.repository.VendaRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.NumberFormat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Locale;

@Service
public class RelatorioService {

    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private PatoRepository patoRepository;

    private static final Locale LOCALE_BR = new Locale("pt", "BR");
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(LOCALE_BR);

    public ByteArrayInputStream gerarRelatorioPDF() {
        List<VendaModel> vendas = vendaRepository.findAll();
        List<PatoModel> patos = patoRepository.findAll();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("GERENCIAMENTO DE PATOS"));

        Table table = new Table(6);
        table.addCell("Nome Pato");
        table.addCell("Nome da Mãe do Pato");
        table.addCell("Status");
        table.addCell("Cliente");
        table.addCell("Tipo do cliente");
        table.addCell("Valor");

        for (PatoModel pato : patos) {
            if (pato.getStatus() == PatoStatus.VENDIDO || pato.getStatus() == PatoStatus.DISPONIVEL) {
                VendaModel venda = vendas.stream().filter(v -> v.getPato().getId().equals(pato.getId())).findFirst().orElse(null);

                if (venda != null || pato.getStatus() == PatoStatus.DISPONIVEL) {
                    ClienteModel cliente = venda != null ? venda.getCliente() : null;
                    double valor = pato.getValor();

                    // Verifica se o cliente tem desconto apenas se o pato estiver vendido
                    String tipoCliente = "";
                    if (venda != null && cliente != null) {
                        if (cliente.getElegivel() == ClienteElegivel.COM_DESCONTO) {
                            valor *= 0.8; // Aplica desconto de 20%
                            tipoCliente = "com Desconto";
                        } else {
                            tipoCliente = "sem Desconto";
                        }
                    }

                    table.addCell(pato.getNome());
                    table.addCell(pato.getNomeMae());
                    table.addCell(pato.getStatus().toString());
                    table.addCell(cliente != null ? cliente.getNome() : "");
                    table.addCell(tipoCliente);
                    table.addCell(CURRENCY_FORMAT.format(valor));
                }
            }
        }

        document.add(table);
        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }


    public ByteArrayInputStream gerarRelatorioExcel() {
        List<VendaModel> vendas = vendaRepository.findAll();
        List<PatoModel> patos = patoRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("GERENCIAMENTO DE PATOS");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Nome Pato");
        header.createCell(1).setCellValue("Nome da Mãe do Pato");
        header.createCell(2).setCellValue("Status");
        header.createCell(3).setCellValue("Cliente");
        header.createCell(4).setCellValue("Tipo do cliente");
        header.createCell(5).setCellValue("Valor");

        int rowNum = 1;
        for (PatoModel pato : patos) {
            if (pato.getStatus() == PatoStatus.VENDIDO || pato.getStatus() == PatoStatus.DISPONIVEL) {
                VendaModel venda = vendas.stream().filter(v -> v.getPato().getId().equals(pato.getId())).findFirst().orElse(null);

                if (venda != null || pato.getStatus() == PatoStatus.DISPONIVEL) {
                    ClienteModel cliente = venda != null ? venda.getCliente() : null;
                    double valor = pato.getValor(); // Valor padrão sem desconto

                    // Verifica se o cliente tem desconto apenas se o pato estiver vendido
                    String tipoCliente = "";
                    if (venda != null && cliente != null) {
                        if (cliente.getElegivel() == ClienteElegivel.COM_DESCONTO) {
                            valor *= 0.8; // Aplica desconto de 20%
                            tipoCliente = "com Desconto";
                        } else {
                            tipoCliente = "sem Desconto";
                        }
                    }

                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(pato.getNome());
                    row.createCell(1).setCellValue(pato.getNomeMae());
                    row.createCell(2).setCellValue(pato.getStatus().toString());
                    row.createCell(3).setCellValue(cliente != null ? cliente.getNome() : "");
                    row.createCell(4).setCellValue(tipoCliente);
                    row.createCell(5).setCellValue(CURRENCY_FORMAT.format(valor));
                }
            }
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            workbook.write(out);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}
