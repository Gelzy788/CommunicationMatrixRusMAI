package ru.matrix.matrix.pdf;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/** Renders an existing Map<String, Cell> without JSON serialization. */
@Service
public class MatrixPdfService {
    private static final String CSS = "pdf-template/matrix-pdf.css";
    private static final String FONT = "pdf-template/fonts/MatrixFont.ttf";
    private final TemplateEngine templateEngine;

    public MatrixPdfService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] generate(Map<String, ?> cellsById) throws Exception {
        Context context = new Context();
        context.setVariable("cellsById", cellsById);
        String html = templateEngine.process("pdf-template/matrix-pdf", context);

        String css;
        try (InputStream is = new ClassPathResource(CSS).getInputStream()) {
            css = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
        // PDF converter receives fully resolved CSS instead of a relative file link.
        html = html.replaceFirst("<link[^>]*matrix-pdf\\.css[^>]*>",
                java.util.regex.Matcher.quoteReplacement("<style>" + css + "</style>"));

        ClassPathResource fontResource = new ClassPathResource(FONT);
        if (!fontResource.exists()) {
            throw new IllegalStateException("TTF not found in classpath: " + FONT);
        }

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
        // This supplier is used by OpenHTMLtoPDF to embed the TTF in the resulting PDF.
        builder.useFont(() -> {
            try {
                return fontResource.getInputStream();
            } catch (IOException e) {
                throw new IllegalStateException("Unable to load PDF font", e);
            }
        }, "MatrixFont");
        builder.withHtmlContent(html, null);
        builder.toStream(buffer);
        builder.run();
        return buffer.toByteArray();
    }
}
