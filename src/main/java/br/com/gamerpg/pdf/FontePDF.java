package br.com.gamerpg.pdf;

import com.itextpdf.text.Font;

public enum FontePDF {
    TITULO(new Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD)),
    CABECALHO(new Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12)),
    CORPO(new Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12));

    private Font fonte;

    FontePDF(Font fonte) {
        this.fonte = fonte;
    }

    public Font getFonte() {
        return fonte;
    }
}

